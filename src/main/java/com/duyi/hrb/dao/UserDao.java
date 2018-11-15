package com.duyi.hrb.dao;

import com.duyi.hrb.domain.User;

public interface UserDao {
    User findByName(User user);

}
