package com.example.TravelProject.User.Repository.queryDsl;

import com.example.TravelProject.User.Entity.User;

public interface UserRepositoryCustom {

    User findByIdWithQueryDsl(Long userId);
}
