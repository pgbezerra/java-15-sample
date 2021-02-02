package com.pgbezerra.sample.service.impl;

import com.pgbezerra.sample.model.User;
import com.pgbezerra.sample.repository.UserRepository;
import com.pgbezerra.sample.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userDao;

    public UserServiceImpl(UserRepository userDao){
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public boolean insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public boolean deleteById(Integer id) {
        return userDao.deleteById(id);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }
}
