package com.example.TravelProject.CourseData.Repository;

import com.example.TravelProject.CourseData.Entity.CourseData;
import com.example.TravelProject.CourseData.Repository.queryDsl.CourseDataRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDataRepository extends JpaRepository<CourseData, Long>, CourseDataRepositoryCustom {
}
