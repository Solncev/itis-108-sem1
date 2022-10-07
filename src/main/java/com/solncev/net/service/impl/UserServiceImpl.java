package com.solncev.net.service.impl;

import com.solncev.net.dao.Dao;
import com.solncev.net.dao.impl.UserDaoImpl;
import com.solncev.net.dto.UserDto;
import com.solncev.net.model.User;
import com.solncev.net.service.UserService;
import com.solncev.net.util.PasswordUtil;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final Dao<User> userDao = new UserDaoImpl();

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream().map(
                u -> new UserDto(u.getFirstName(), u.getSecondName(), u.getLogin())
        ).collect(Collectors.toList());
    }

    @Override
    public UserDto get(int id) {
        return null;
    }

    @Override
    public void save(User user) {
        user.setPassword(PasswordUtil.encrypt(user.getPassword()));
        userDao.save(user);
    }
}
