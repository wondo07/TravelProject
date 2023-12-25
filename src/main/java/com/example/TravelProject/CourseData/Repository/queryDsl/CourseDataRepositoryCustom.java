package com.example.TravelProject.CourseData.Repository.queryDsl;

import com.example.TravelProject.CourseData.Entity.CourseData;

public interface CourseDataRepositoryCustom {

    CourseData findByIdWithQueryDsl(Long courseDataId);
}
