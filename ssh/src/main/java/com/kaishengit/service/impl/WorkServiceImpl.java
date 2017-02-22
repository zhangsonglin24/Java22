package com.kaishengit.service.impl;

import com.kaishengit.mapper.WorkMapper;
import com.kaishengit.pojo.Work;
import com.kaishengit.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    private WorkMapper workMapper;

    @Override
    public List<Work> findAllWork() {
        return workMapper.findAllWork();
    }

    @Override
    public Work findWorkById(Integer id) {
        return workMapper.findWorkById(id);
    }
}
