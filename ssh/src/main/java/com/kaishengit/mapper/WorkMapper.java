package com.kaishengit.mapper;

import com.kaishengit.pojo.Work;

import java.util.List;

public interface WorkMapper {
    List<Work> findAllWork();

    Work findWorkById(Integer id);
}
