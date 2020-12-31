package com.talents.orange.demo.controller;

import com.talents.orange.demo.dto.request.UserDTO;
import com.talents.orange.demo.dto.response.MessageResponseDTO;
import com.talents.orange.demo.entity.User;
import com.talents.orange.demo.exception.UserNotFoundException;
import com.talents.orange.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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
    public MessageResponseDTO createUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping
    public List<UserDTO> listAll() {
        return userService.listAll();
    }

    @GetMapping("/{id}")
    public UserDTO findyId(@PathVariable Long id) throws UserNotFoundException {
        return userService.finfById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  delete (@PathVariable Long id) throws UserNotFoundException{
        userService.delete(id);
    }


}
