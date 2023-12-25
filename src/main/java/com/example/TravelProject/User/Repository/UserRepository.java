package com.example.TravelProject.User.Repository;

import com.example.TravelProject.User.Entity.User;
import com.example.TravelProject.User.Repository.queryDsl.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

}
