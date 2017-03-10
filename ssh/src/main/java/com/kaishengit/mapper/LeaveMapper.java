package com.kaishengit.mapper;

import com.kaishengit.pojo.Leave;


public interface LeaveMapper {
    void save(Leave leave);
    void delete(long id);
    Leave getLeaveById(long id);
    void update(Leave leave);
}
