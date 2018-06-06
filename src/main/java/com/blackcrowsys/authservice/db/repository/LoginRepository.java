package com.blackcrowsys.authservice.db.repository;

import com.blackcrowsys.authservice.db.model.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends CrudRepository<Login, String> {

    Optional<Login> findByUsername(String username);
}
