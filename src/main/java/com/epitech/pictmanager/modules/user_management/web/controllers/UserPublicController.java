package com.epitech.pictmanager.modules.user_management.web.controllers;

import com.epitech.pictmanager.modules.user_management.application.services.UserPublicService;
import com.epitech.pictmanager.modules.user_management.application.usecases.search.GetUserFromSearchUseCase;
import com.epitech.pictmanager.modules.user_management.application.usecases.search.GetUsersFromSearchUseCase;
import com.epitech.pictmanager.modules.user_management.web.dto.response.SearchListResponseDTO;
import com.epitech.pictmanager.modules.user_management.web.dto.view.UserProfileDetailView;
import com.epitech.pictmanager.shared.responses.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
// Make route private ..
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

    @GetMapping("/search/profile/{username}")
    public GenericResponse<UserProfileDetailView> get(@PathVariable String username) {
        return new GenericResponse<>(null, HttpStatus.FOUND.value(), this.getUserUseCase.execute(username));
    }

}
