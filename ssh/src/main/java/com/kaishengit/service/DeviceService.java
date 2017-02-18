package com.kaishengit.service;

import com.kaishengit.pojo.Device;

import java.util.List;
import java.util.Map;

public interface DeviceService {
    void saveNewDevice(Device device);


    List<Device> findAllDevice();

    List<Device> findDeviceBySearchParam(Map<String, Object> searchParam);

    Long count();

    Long countBySearchParam(Map<String, Object> searchParam);

    void delDevice(Integer id);

    Device findDeviceById(Integer id);
}
