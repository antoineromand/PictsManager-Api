package com.epitech.pictmanager.modules.auth.application.services;

import com.epitech.pictmanager.modules.auth.domain.UserDomain;
import com.epitech.pictmanager.modules.auth.infrastructure.jwt.JwtTokenProvider;
import com.epitech.pictmanager.modules.auth.application.dto.RegisterDto;
import com.epitech.pictmanager.modules.auth.infrastructure.repositories.ports.UserRepositoryPort;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.jpa.ProfilJpaRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final UserRepositoryPort userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncryptionService passwordEncryptionService;


    public AuthService(UserRepositoryPort userRepository, ProfilJpaRepository profileRepository, PasswordEncryptionService passwordEncryptionService, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncryptionService = passwordEncryptionService;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public UserDomain register(RegisterDto registerDto) {
        UserDomain user = RegisterDto.toUserDomain(registerDto, null);
        user.setPassword(passwordEncryptionService.encrypt(user.getPassword()));

        try {
            return userRepository.createUser(user);
        } catch (ResponseStatusException e) {
            throw e; // déjà un code HTTP clair
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already taken", e);
        }
    }

    public String login(String username, String password, HttpServletResponse response) {
        try {
            UserDomain user = this.userRepository.getUserByUsername(username);
            if (user == null)
                throw new Error("Invalid username or password");
            if (!passwordEncryptionService.check(password, user.getPassword()))
                throw new Error("Invalid username or password");

            return jwtTokenProvider.createToken(user.getUserId());
        } catch (Error e) {
            throw e;
        }
    }
}
