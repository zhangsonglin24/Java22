package com.kaishengit.mapper;

import com.kaishengit.dto.WorkOutDto;
import com.kaishengit.pojo.Work;
import com.kaishengit.pojo.WorkOut;
import com.kaishengit.pojo.WorkOutDetail;
import com.kaishengit.pojo.WorkOutDocs;

import java.util.List;

public interface WorkMapper {
    List<Work> findAllWork();

    Work findWorkById(Integer id);

    void save(WorkOut workOut);

    void updateCurrentNum(Work work);


}
