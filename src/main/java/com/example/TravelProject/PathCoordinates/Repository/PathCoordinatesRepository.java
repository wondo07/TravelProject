package com.example.TravelProject.PathCoordinates.Repository;

import com.example.TravelProject.PathCoordinates.Entity.PathCoordinates;
import com.example.TravelProject.PathCoordinates.Repository.queryDsl.PathCoordinatesRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PathCoordinatesRepository extends JpaRepository<PathCoordinates, Long>, PathCoordinatesRepositoryCustom {
}
