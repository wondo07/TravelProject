package com.example.TravelProject.Eat.Repository.queryDsl;

import com.example.TravelProject.Eat.Entity.Eat;

public interface EatRepositoryCustom {

    Eat findByIdWithQueryDsl(Long eatId);
}
