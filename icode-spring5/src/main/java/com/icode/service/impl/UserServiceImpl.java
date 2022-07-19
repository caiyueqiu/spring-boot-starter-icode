package com.icode.service.impl;

import com.icode.dao.UserDao;
import com.icode.entity.User;
import com.icode.service.UserService;

import java.util.List;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/7/14 23:06
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUserList() {
        return this.userDao.getUserList();
    }
}
