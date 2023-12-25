package com.example.TravelProject.Travel.Repository;

import com.example.TravelProject.Travel.Entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Long> {
}
