package com.example.TravelProject.Sleep.Repository;

import com.example.TravelProject.Sleep.Entity.Sleep;
import com.example.TravelProject.Sleep.Repository.queryDsl.SleepRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SleepRepository extends JpaRepository<Sleep, Long>, SleepRepositoryCustom {
}
