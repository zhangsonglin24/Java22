package com.kaishengit.mapper;

import com.kaishengit.pojo.DeviceRentDocs;

import java.util.List;

public interface DeviceRentDocsMapper {
    void batchSave(List<DeviceRentDocs> rentDocsList);

    List<DeviceRentDocs> findByRentId(Integer id);
}
