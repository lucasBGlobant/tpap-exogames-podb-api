package com.exogames.persistence_component.repository;


import com.exogames.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByUsername(String username);

}
