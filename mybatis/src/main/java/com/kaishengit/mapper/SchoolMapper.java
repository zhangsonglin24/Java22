package com.kaishengit.mapper;

import com.kaishengit.pojo.School;

import java.util.List;

public interface SchoolMapper {

    School findById(Integer id);
    List<School> findAll();
}
