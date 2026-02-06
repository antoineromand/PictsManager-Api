package com.epitech.pictmanager.modules.user_management.web.controllers;

import com.epitech.pictmanager.modules.user_management.application.services.UserPublicService;
import com.epitech.pictmanager.modules.user_management.application.usecases.search.GetUserFromSearchUseCase;
import com.epitech.pictmanager.modules.user_management.application.usecases.search.GetUsersFromSearchUseCase;
import com.epitech.pictmanager.modules.user_management.web.dto.response.SearchListResponseDTO;
import com.epitech.pictmanager.shared.responses.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("public/api/user")
public class UserPublicController {

    UserPublicService userPublicService;
    final GetUsersFromSearchUseCase getUsersUseCase;
    final GetUserFromSearchUseCase getUserUseCase;

    public UserPublicController(UserPublicService userPublicService, GetUsersFromSearchUseCase getUsersUseCase, GetUserFromSearchUseCase getUserUseCase) {
        this.userPublicService = userPublicService;
        this.getUsersUseCase = getUsersUseCase;
        this.getUserUseCase = getUserUseCase;
    }

    @GetMapping("/search")
    public GenericResponse<SearchListResponseDTO> search(
            @RequestParam("input") String input,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset
    ) {
        SearchListResponseDTO result = this.getUsersUseCase.execute(input, limit, offset);
        return new GenericResponse<>(null, HttpStatus.OK.value(), result);
    }

    @GetMapping("/search/profil")
    public ResponseEntity<Object> get(@RequestParam("username") String username) {
        return userPublicService.getUser(username);
    }

}
