package com.kaishengit.service;


import com.kaishengit.pojo.Work;

import java.util.List;

public interface WorkService {
    List<Work> findAllWork();

    Work findWorkById(Integer id);
}
