package com.exogames.usecases;


import com.exogames.commons_services.services.CommonService;
import com.exogames.entities.User;

import java.util.List;

public interface UserService extends CommonService<User> {

    public List<User> findByUsername(String username);



}
