package com.kaishengit.service.impl;

import com.google.common.collect.Lists;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.DiskMapper;
import com.kaishengit.pojo.Disk;
import com.kaishengit.service.DiskService;
import com.kaishengit.shiro.ShiroUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
public class DiskServiceImpl implements DiskService {

    @Autowired
    private DiskMapper diskMapper;
    @Value("${upload.path}")
    private String savePath;

    @Override
    public List<Disk> findByFid(Integer path) {
        return diskMapper.findByFid(path);
    }

    /**
     * 新建文件夹
     * @param disk
     */
    @Override
    public void saveNewFolder(Disk disk) {
        disk.setCreateUser(ShiroUtil.getCurrentUserName());
        disk.setCreateTime(DateTime.now().toString("yyyy-mm-dd hh:mm"));
        disk.setType(Disk.DIRECTORY_TYPE);

        diskMapper.save(disk);
    }

    /**
     * 保存文件
     * @param fid
     * @param file
     */
    @Override
    @Transactional
    public void saveNewFile(Integer fid, MultipartFile file) {
        String sourceName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString();
        Long size = file.getSize();

        if(sourceName.lastIndexOf(".") != -1) {
            newName += sourceName.substring(sourceName.lastIndexOf("."));
        }
        //保存文件到磁盘
        try {
            File saveFile = new File(new File(savePath), newName);
            FileOutputStream outputStream = new FileOutputStream(saveFile);
            InputStream inputStream = file.getInputStream();
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }catch (IOException ex){
            throw new ServiceException("文件保存到磁盘异常",ex);
        }
        //保存记录到数据库
        Disk disk = new Disk();
        disk.setFid(fid);
        disk.setName(newName);
        disk.setSourceName(sourceName);
        disk.setCreateUser(ShiroUtil.getCurrentUserName());
        disk.setCreateTime(DateTime.now().toString("yyyy-mm-dd hh:mm"));
        disk.setType(Disk.FILE_TYPE);
        disk.setSize(FileUtils.byteCountToDisplaySize(size));

        diskMapper.save(disk);
    }
    public Disk findById(Integer id) {
        return diskMapper.findById(id);
    }

    @Override
    @Transactional
    public void delById(Integer id) {
        Disk disk = findById(id);
        if(disk != null){
            if(Disk.FILE_TYPE.equals(disk.getType())){
                //删除文件
                File file = new File(savePath,disk.getName());
                file.delete();
                //删除数据库中的记录
                diskMapper.delete(id);
            }else {
                //查出所有记录
                List<Disk> diskList = diskMapper.findAll();
                //将要删除的ID
                List<Integer> delIdList = Lists.newArrayList();
                findDelId(diskList,delIdList,id);
                delIdList.add(id);
                //批量删除
                diskMapper.batchDel(delIdList);
            }
        }
    }
    private void findDelId(List<Disk> diskList, List<Integer> delIdList, Integer id) {
        for(Disk disk : diskList){
            if(disk.getFid().equals(id)){      //如果其fid等于将要删除的id(即：其为id文件夹中的子)
                delIdList.add(disk.getId());   //将其加入要删除的id中
                if(disk.getType().equals(Disk.DIRECTORY_TYPE)){  //如果是文件夹，则重调方法(递归)
                    findDelId(diskList,delIdList,disk.getId());
                }else {
                    File file = new File(savePath,disk.getName());//如果是文件，直接删除
                    file.delete();
                    }
                }
            }
        }


    @Override
    public InputStream downloadFile(Integer id) throws FileNotFoundException {
        Disk disk = diskMapper.findById(id);
        if(disk == null || Disk.DIRECTORY_TYPE.equals(disk.getType())){
            return null;
        }else{
            FileInputStream inputStream = new FileInputStream(new File(new File(savePath),disk.getName()));
            return inputStream;
        }

    }
}



