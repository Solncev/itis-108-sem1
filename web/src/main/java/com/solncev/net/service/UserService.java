package com.solncev.net.service;

import com.solncev.net.dto.UserDto;
import com.solncev.net.model.User;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();
    UserDto get(int id);
    void save(User user);
}
