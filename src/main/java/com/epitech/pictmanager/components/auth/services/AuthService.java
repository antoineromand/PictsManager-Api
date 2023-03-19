package com.epitech.pictmanager.components.auth.services;

import com.epitech.pictmanager.components.auth.config.jwt.JwtTokenProvider;
import com.epitech.pictmanager.components.auth.dto.RegisterDto;
import com.epitech.pictmanager.components.auth.repositories.ProfileJpaRepository;
import com.epitech.pictmanager.components.auth.repositories.UserJpaRepository;
import com.epitech.pictmanager.components.auth.responses.AuthResponse;
import com.epitech.pictmanager.models.Profile;
import com.epitech.pictmanager.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service()
public class AuthService {
    private UserJpaRepository userRepository;
    private ProfileJpaRepository profileRepository;

    private JwtTokenProvider jwtTokenProvider;

    private final PasswordEncryptionService passwordEncryptionService;

    @Autowired
    public AuthService(UserJpaRepository userRepository, ProfileJpaRepository profileRepository, PasswordEncryptionService passwordEncryptionService, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncryptionService = passwordEncryptionService;
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
    public ResponseEntity<AuthResponse> register(RegisterDto registerDto) {
        try {
            if(this.userRepository.existsUserByUsername(registerDto.getUsername()))
                throw new Error("Username already taken");
            if(this.userRepository.existsUserByEmail(registerDto.getEmail()))
                throw new Error("Email already taken");
            User user = new User();
            user.setUsername(registerDto.getUsername());
            user.setPassword(passwordEncryptionService.encrypt(registerDto.getPassword()));
            user.setEmail(registerDto.getEmail());
            user.setDateOfBirth(new Date(registerDto.getDateOfBirth()));
            user.setBanned(false);
            user.setPublic(registerDto.isPublic());
            this.userRepository.save(user);

            Profile profile = new Profile();
            profile.setUser(user);
            profile.setDescription(registerDto.getDescription());
            profile.setProfilePicture(registerDto.getProfilePicture());
            profile.setCoverPicture(registerDto.getCoverPicture());
            this.profileRepository.save(profile);
            return new ResponseEntity<AuthResponse>(new AuthResponse("User registered successfully", HttpStatus.CREATED.value()), HttpStatus.CREATED);
        } catch (Error e) {
            return new ResponseEntity<AuthResponse>(new AuthResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<AuthResponse> login(String username, String password, HttpServletResponse response) {
        try {
            User user = this.userRepository.findUserByUsername(username);
            if (user == null)
                throw new Error("User not found");
            if (!passwordEncryptionService.check(password, user.getPassword()))
                throw new Error("Wrong password");
            String token = jwtTokenProvider.createToken(username);

            Cookie jwtCookie = new Cookie("jwt", token);
            jwtCookie.setHttpOnly(true); // This makes the cookie inaccessible to JavaScript
            jwtCookie.setMaxAge((int) (jwtTokenProvider.getExpiration() / 1000));
            jwtCookie.setPath("/");
            response.addCookie(jwtCookie);
            return new ResponseEntity<AuthResponse>(new AuthResponse("User logged in successfully", HttpStatus.OK.value()), HttpStatus.OK);
        } catch (Error e) {
            return new ResponseEntity<AuthResponse>(new AuthResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        }
    }
}
