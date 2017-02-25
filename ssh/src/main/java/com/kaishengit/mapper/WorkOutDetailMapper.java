package com.kaishengit.mapper;


import com.kaishengit.pojo.WorkOutDetail;

import java.util.List;

public interface WorkOutDetailMapper {
    void batchSave(List<WorkOutDetail> detailList);
}
