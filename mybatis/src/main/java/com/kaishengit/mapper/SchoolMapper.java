package com.kaishengit.mapper;

import com.kaishengit.pojo.School;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SchoolMapper {

    School findById(Integer id);

    @Select("select * from t_school")
    List<School> findAll();
}
