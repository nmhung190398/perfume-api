package com.perfume.repository.custom.impl;

import com.perfume.entity.User;
import com.perfume.repository.custom.BaseRepository;
import com.perfume.repository.custom.UserRepositoryCustom;

public class UserRepositoryCustomImpl extends BaseRepositoryCustom<User> implements UserRepositoryCustom {
    public UserRepositoryCustomImpl() {
        super("U");
    }
}
