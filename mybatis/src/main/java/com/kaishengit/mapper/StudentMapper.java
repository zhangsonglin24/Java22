package com.kaishengit.mapper;

import com.kaishengit.pojo.Student;

import java.util.List;

public interface StudentMapper {
    Student findById(Integer id);

    List<Student> findAll();
}
