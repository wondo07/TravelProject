package com.example.TravelProject.Sleep.Repository.queryDsl;

import com.example.TravelProject.Sleep.Entity.Sleep;

public interface SleepRepositoryCustom {

    Sleep findByIdWithQueryDsl(Long sleepId);
}
