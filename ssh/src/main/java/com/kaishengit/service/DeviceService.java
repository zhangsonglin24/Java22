package com.kaishengit.service;

import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.DeviceRent;
import com.kaishengit.pojo.DeviceRentDetail;
import com.kaishengit.pojo.DeviceRentDocs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

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

    InputStream downloadFile(Integer id) throws IOException;

    DeviceRentDocs findDeviceRentDocsById(Integer id);

    DeviceRent findDeviceRentById(Integer id);

    void downloadZipFile(DeviceRent rent, ZipOutputStream zipOutputStream) throws IOException;
}
