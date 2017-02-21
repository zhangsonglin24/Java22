package com.kaishengit.mapper;


import com.kaishengit.pojo.Disk;

import java.util.List;

public interface DiskMapper {
    List<Disk> findByFid(Integer path);

    void save(Disk disk);
}
