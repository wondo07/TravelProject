package com.example.TravelProject.Eat.Repository;

import com.example.TravelProject.Eat.Entity.Eat;
import com.example.TravelProject.Eat.Repository.queryDsl.EatRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EatRepository extends JpaRepository<Eat, Long>, EatRepositoryCustom {
}
