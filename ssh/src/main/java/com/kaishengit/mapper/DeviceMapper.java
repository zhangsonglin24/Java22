package com.kaishengit.mapper;


import com.kaishengit.pojo.Device;

import java.util.List;
import java.util.Map;

public interface DeviceMapper {
    void save(Device device);

    List<Device> findAll();

    List<Device> findBySeachParam(Map<String, Object> searchParam);

    Long count();

    Long countBySearchParam(Map<String, Object> searchParam);

    void del(Integer id);

    Device findById(Integer id);

}
