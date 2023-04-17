package com.epitech.pictmanager.components.user.controllers;

import com.epitech.pictmanager.components.user.dto.UserSearchResponse;
import com.epitech.pictmanager.components.user.services.UserPublicService;
import com.epitech.pictmanager.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("public/api/user")
public class UserPublicController {
    @Autowired
    UserPublicService userPublicService;

    @GetMapping("/search")
    public ResponseEntity<Object> search(@RequestParam("username") String username) {
        return userPublicService.search(username);
    }

    @GetMapping()
    public ResponseEntity<Object> get(@RequestParam("username") String username) {
        return userPublicService.getUser(username);
    }

}
