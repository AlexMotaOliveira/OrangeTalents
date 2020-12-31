package com.talents.orange.demo.repository;

import com.talents.orange.demo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User,Long> {

    User findByCpf(String cpf);

    User findByEmail(String email);

}
