package com.talents.orange.demo.repository;

import com.talents.orange.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByCpf(String cpf);

    User findByEmail(String email);

}
