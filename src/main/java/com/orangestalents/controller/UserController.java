package com.orangestalents.controller;

import com.orangestalents.dto.request.UserDTO;
import com.orangestalents.dto.response.MessageResponseDTO;
import com.orangestalents.exception.web.DuplicateUserException;
import com.orangestalents.exception.web.UserNotFoundException;
import com.orangestalents.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createUser(@RequestBody @Valid UserDTO userDTO) throws DuplicateUserException {
        return userService.createUser(userDTO);
    }

    @GetMapping
    public List<UserDTO> listAll() {
        return userService.listAll();
    }

    @GetMapping("/{id}")
    public UserDTO findyIdUser(@PathVariable Long id) throws UserNotFoundException {
        return userService.findByIdUser(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIdUser(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteIdUser(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateByIdUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) throws UserNotFoundException, DuplicateUserException {
        return userService.updateByIdUser(id, userDTO);
    }
}
