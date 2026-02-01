package com.epitech.pictmanager.modules.user_management.controllers;

import com.epitech.pictmanager.modules.user_management.services.UserPublicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("public/api/user")
public class UserPublicController {

    UserPublicService userPublicService;

    public UserPublicController(UserPublicService userPublicService) {
        this.userPublicService = userPublicService;
    }

    @GetMapping("/search")
    public ResponseEntity<Object> search(@RequestParam("username") String username) {
        return userPublicService.search(username);
    }

    @GetMapping()
    public ResponseEntity<Object> get(@RequestParam("username") String username) {
        return userPublicService.getUser(username);
    }

}
