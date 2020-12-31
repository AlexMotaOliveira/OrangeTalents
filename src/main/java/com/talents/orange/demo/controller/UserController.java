package com.talents.orange.demo.controller;

import com.talents.orange.demo.dto.response.MessageResponseDTO;
import com.talents.orange.demo.entity.User;
import com.talents.orange.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createUser(
            @RequestBody
            @Valid User userDTO){
        return userService.createUser(userDTO);
    }

    @GetMapping("/{id}")
    public User findyId(@PathVariable Long id) throws Exception {
        return userService.finfById(id);
    }
}
