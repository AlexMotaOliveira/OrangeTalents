package com.talents.orange.demo.service;

import com.talents.orange.demo.dto.response.MessageResponseDTO;
import com.talents.orange.demo.entity.User;
import com.talents.orange.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public MessageResponseDTO createUser(User userDTO){
        User saveUserDTO = userRepository.save(userDTO);
        return MessageResponseDTO
                .builder()
                .message("Usuario criado com sucesso " + saveUserDTO.getNome())
                .build();
    }


    public User finfById(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception(String.valueOf(id)));

        return user;
    }
}
