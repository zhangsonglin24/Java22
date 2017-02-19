package com.kaishengit.service;

import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.DeviceRent;
import com.kaishengit.pojo.DeviceRentDetail;
import com.kaishengit.pojo.DeviceRentDocs;

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

    String saveRent(DeviceRentDto deviceRentDto);

    DeviceRent findDeviceRentBySerialNumber(String serialNumber);

    List<DeviceRentDetail> findDeviceRentDetailListByRentId(Integer id);

    List<DeviceRentDocs> findDeviceRentDocsListByRentId(Integer id);
}
