package com.talents.orange.demo.service;

import com.talents.orange.demo.dto.request.UserDTO;
import com.talents.orange.demo.dto.response.MessageResponseDTO;
import com.talents.orange.demo.entity.User;
import com.talents.orange.demo.exception.UserNotFoundException;
import com.talents.orange.demo.mapper.UserMapper;
import com.talents.orange.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public MessageResponseDTO createUser(@Valid UserDTO userDTO) {

        User userToSave = userMapper.toModel(userDTO);

        User saveUser = userRepository.save(userToSave);
        return createMessageResponse(saveUser.getId(), "Usuário criado com sucesso ");
    }

    public List<UserDTO> listAll() {
        List<User> allUser = userRepository.findAll();
        return allUser.stream()
                .map(userMapper::toDTO).collect(Collectors.toList());
    }

    public UserDTO findById(Long id) throws UserNotFoundException {
        User user = verifyIfExists(id);

        return userMapper.toDTO(user);
    }

    public void delete(Long id) throws UserNotFoundException {
        verifyIfExists(id);

        userRepository.deleteById(id);
    }


    public MessageResponseDTO updateById(Long id, UserDTO userDTO) throws UserNotFoundException {
        verifyIfExists(id);

        User userToUpdate = userMapper.toModel(userDTO);

        User updateUser = userRepository.save(userToUpdate);
        return createMessageResponse(updateUser.getId(), "Usuário atualizado com sucesso ");
    }

    private User verifyIfExists(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String s) {
        return MessageResponseDTO
                .builder()
                .message(s + id)
                .build();
    }
}
