package com.duyi.hrb.service;

import com.duyi.hrb.dao.UserDao;
import com.duyi.hrb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User findByName(User user) {
        return  userDao.findByName(user);
    }
}
