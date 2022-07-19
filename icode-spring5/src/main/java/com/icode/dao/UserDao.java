package com.icode.dao;

import com.icode.entity.User;

import java.util.Collections;
import java.util.List;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/7/14 23:03
 */
public class UserDao {

    public List<User> getUserList() {
        User user = new User("jay", 33);
        return Collections.singletonList(user);
    }
}
