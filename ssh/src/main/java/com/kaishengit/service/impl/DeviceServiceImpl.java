package com.kaishengit.service.impl;

import com.kaishengit.mapper.DeviceMapper;
import com.kaishengit.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
}
