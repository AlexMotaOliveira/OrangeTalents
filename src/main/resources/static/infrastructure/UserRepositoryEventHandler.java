package com.orangestalents.orange.demo.infrastructure;

import com.orangestalents.entity.User;
import com.orangestalents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;


@Component
@RepositoryEventHandler(value = User.class)
public class UserRepositoryEventHandler {

    private UserRepository userRepository;

    @Autowired
    public UserRepositoryEventHandler(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @HandleBeforeSave
    @HandleBeforeCreate
    public void handleUser(User user) throws DuplicateUserException {

        User userDBCPF = userRepository.findByCpf(user.getCpf());
        User userDBEmail = userRepository.findByEmail(user.getEmail());

        if (userDBCPF != null) {
            if (user.getCpf().equals(userDBCPF.getCpf())) {
                throw new DuplicateUserException("CPF já existe na base de dados");
            }
        }

        if (userDBEmail != null) {
            if (user.getEmail().equals(userDBEmail.getEmail())) {
                throw new DuplicateUserException("Email já existe na base de dados");
            }
        }
    }
}
