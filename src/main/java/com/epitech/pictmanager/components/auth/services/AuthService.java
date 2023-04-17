package com.epitech.pictmanager.components.auth.services;

import com.epitech.pictmanager.components.auth.config.jwt.JwtTokenProvider;
import com.epitech.pictmanager.components.auth.dto.RegisterDto;
import com.epitech.pictmanager.components.user.repositories.ProfilJpaRepository;
import com.epitech.pictmanager.components.user.repositories.UserJpaRepository;
import com.epitech.pictmanager.models.Profil;
import com.epitech.pictmanager.models.User;
import com.epitech.pictmanager.responses.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class AuthService {
    private UserJpaRepository userRepository;
    private ProfilJpaRepository profileRepository;

    private JwtTokenProvider jwtTokenProvider;

    private final PasswordEncryptionService passwordEncryptionService;

    @Autowired
    public AuthService(UserJpaRepository userRepository, ProfilJpaRepository profileRepository, PasswordEncryptionService passwordEncryptionService, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncryptionService = passwordEncryptionService;
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public ResponseEntity<GenericResponse> register(RegisterDto registerDto) {
        try {
            if(this.userRepository.existsUserByUsername(registerDto.getUsername()))
                throw new Error("Username already taken");
            if(this.userRepository.existsUserByEmail(registerDto.getEmail()))
                throw new Error("Email already taken");

            User user = RegisterDto.toUser(registerDto);
            user.setPassword(passwordEncryptionService.encrypt(user.getPassword()));
            this.userRepository.save(user);
            Profil profil = RegisterDto.toProfil(registerDto, user);
            this.profileRepository.save(profil);
            boolean folderExist = createFolders(user.getId());
            if (!folderExist) {
                throw new Error("Error while creating user folder, it's seems that there is a problem with your username");
            }
            return new ResponseEntity<GenericResponse>(new GenericResponse("User registered successfully", HttpStatus.CREATED.value()), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            System.out.println("Erreur de contrainte : " + e.getMostSpecificCause());
            return new ResponseEntity<GenericResponse>(new GenericResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        }
    }

    public boolean createFolders(Long id) {
        try {
            Path imageManagerPath = Paths.get("/image_manager");
            Path userFolderPath = imageManagerPath.resolve(id.toString());
            File userFile = userFolderPath.toFile();
            if (!userFile.exists()) {
                if (userFile.mkdir()) {
                    System.out.println(userFile.getPath());
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ResponseEntity<GenericResponse> login(String username, String password, HttpServletResponse response) {
        try {
            User user = this.userRepository.findUserByUsername(username);
            if (user == null)
                throw new Error("User not found");
            if (!passwordEncryptionService.check(password, user.getPassword()))
                throw new Error("Wrong password");
            String token = jwtTokenProvider.createToken(user.getId());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new GenericResponse("User logged in successfully", HttpStatus.OK.value()));
        } catch (Error e) {
            return new ResponseEntity<GenericResponse>(new GenericResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        }
    }
}
