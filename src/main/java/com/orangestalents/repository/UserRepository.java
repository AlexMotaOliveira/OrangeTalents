package com.orangestalents.repository;

import com.orangestalents.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByCpfEquals(String cpf);

    User findByEmailEquals(String email);
}
