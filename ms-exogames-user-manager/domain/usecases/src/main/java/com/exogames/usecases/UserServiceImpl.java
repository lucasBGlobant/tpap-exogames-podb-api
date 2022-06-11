package com.exogames.usecases;

import com.exogames.commons_services.services.CommonServiceImpl;
import com.exogames.entities.User;
import com.exogames.persistence_component.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl extends CommonServiceImpl<User, UserRepository> implements UserService {

    @Override
    @Transactional
    public List<User> findByUsername(String username){
        return repository.findByUsername(username);

    }


}
