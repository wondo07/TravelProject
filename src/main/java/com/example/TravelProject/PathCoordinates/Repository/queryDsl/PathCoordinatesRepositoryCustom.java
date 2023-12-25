package com.example.TravelProject.PathCoordinates.Repository.queryDsl;

import com.example.TravelProject.PathCoordinates.Entity.PathCoordinates;

public interface PathCoordinatesRepositoryCustom {

    PathCoordinates findByIdWithQueryDsl(Long pathCoordinatesId);
}
