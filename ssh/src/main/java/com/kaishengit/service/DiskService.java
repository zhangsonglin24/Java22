package com.kaishengit.service;

import com.kaishengit.pojo.Disk;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DiskService {
    List<Disk> findByFid(Integer path);

    void saveNewFolder(Disk disk);

    void saveNewFile(Integer fid, MultipartFile file);
}
