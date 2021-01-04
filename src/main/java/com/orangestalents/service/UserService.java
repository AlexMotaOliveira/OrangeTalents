package com.orangestalents.service;

import com.orangestalents.dto.request.UserDTO;
import com.orangestalents.dto.response.MessageResponseDTO;
import com.orangestalents.entity.User;
import com.orangestalents.exception.web.DuplicateUserException;
import com.orangestalents.exception.web.UserNotFoundException;
import com.orangestalents.mapper.UserMapper;
import com.orangestalents.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private UserRepository userRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public MessageResponseDTO createUser(UserDTO userDTO) throws DuplicateUserException {
        User userToSave = userMapper.toModel(userDTO);
        verifyIfExistsCpforEmailCreate(userToSave);
        User saveUser = userRepository.save(userToSave);
        return createMessageResponse(saveUser.getId(), "Usuário criado com sucesso ");
    }

    public UserDTO findByIdUser(Long id) throws UserNotFoundException {
        User user = verifyIfExists(id);
        return userMapper.toDTO(user);
    }

    public void deleteIdUser(Long id) throws UserNotFoundException {
        verifyIfExists(id);
        userRepository.deleteById(id);
    }

    public MessageResponseDTO updateByIdUser(Long id, UserDTO userDTO) throws UserNotFoundException, DuplicateUserException {
        verifyIfExists(id);
        User userToUpdate = userMapper.toModel(userDTO);
        verifyIfExistsCpforEmailUpdate(id, userToUpdate);
        User updateUser = userRepository.save(userToUpdate);
        return createMessageResponse(updateUser.getId(), "Usuário atualizado com sucesso ");
    }

    private User verifyIfExists(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário " + id + " não localizado"));
    }

    private MessageResponseDTO createMessageResponse(Long id, String s) {
        return MessageResponseDTO
                .builder()
                .message(s + id)
                .build();
    }

    private void verifyIfExistsCpforEmailCreate(User user) throws DuplicateUserException {
        User userdbcpf = userRepository.findByCpfEquals(user.getCpf());
        User userdbemail = userRepository.findByEmailEquals(user.getEmail());

        if (userdbcpf != null) {
            if (user.getCpf().equals(userdbcpf.getCpf())) {
                throw new DuplicateUserException("CPF já cadastrado");
            }
        }

        if (userdbemail != null) {
            if (user.getEmail().equals(userdbemail.getEmail())) {
                throw new DuplicateUserException("Email já cadastrado");
            }
        }
    }

    private void verifyIfExistsCpforEmailUpdate(Long id, User user) throws DuplicateUserException {
        User userdbcpf = userRepository.findByCpfEquals(user.getCpf());
        User userdbemail = userRepository.findByEmailEquals(user.getEmail());


        if (userdbcpf != null) {
            if (userdbcpf.getCpf().equals(user.getCpf()) && !userdbcpf.getId().equals(id)) {
                throw new DuplicateUserException("CPF já cadastrado");
            }
        }

        if (userdbemail != null) {
            if (userdbemail.getEmail().equals(user.getEmail()) && !userdbcpf.getId().equals(id)) {
                throw new DuplicateUserException("CPF já cadastrado");
            }
        }
    }
}
