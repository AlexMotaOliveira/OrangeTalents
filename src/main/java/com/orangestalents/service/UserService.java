package com.orangestalents.service;

import com.orangestalents.dto.request.UserDTO;
import com.orangestalents.dto.response.MessageResponseDTO;
import com.orangestalents.entity.User;
import com.orangestalents.exception.UserNotFoundException;
import com.orangestalents.mapper.UserMapper;
import com.orangestalents.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private UserRepository userRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

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
