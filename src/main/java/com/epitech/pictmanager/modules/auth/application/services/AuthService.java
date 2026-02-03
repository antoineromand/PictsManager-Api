package com.epitech.pictmanager.modules.auth.application.services;

import com.epitech.pictmanager.modules.auth.application.command.RegisterCommand;
import com.epitech.pictmanager.modules.auth.application.exceptions.UsernameOrEmailAlreadyTakenException;
import com.epitech.pictmanager.modules.auth.domain.UserDomain;
import com.epitech.pictmanager.modules.auth.infrastructure.jwt.JwtTokenProvider;
import com.epitech.pictmanager.modules.auth.web.dto.RegisterDto;
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

    public UserDomain register(RegisterCommand command) {
        UserDomain user = new UserDomain(
           null,
           command.username(),
           command.password(),
           command.email(),
           command.birthDate(),
           command.isBanned(),
           command.isPublic()
        );
        user.setPassword(passwordEncryptionService.encrypt(user.getPassword()));

        try {
            return userRepository.createUser(user);
        } catch (DataIntegrityViolationException e) {
            throw new UsernameOrEmailAlreadyTakenException();
        }
    }

    public String login(String username, String password) {
        UserDomain user = this.userRepository.getUserByUsername(username);
        if (user == null)
            throw new Error("Invalid username or password");
        if (!passwordEncryptionService.check(password, user.getPassword()))
            throw new Error("Invalid username or password");

        return jwtTokenProvider.createToken(user.getUserId());
    }
}
