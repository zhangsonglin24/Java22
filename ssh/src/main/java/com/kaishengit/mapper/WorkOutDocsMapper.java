package com.kaishengit.mapper;


import com.kaishengit.pojo.WorkOutDocs;

import java.util.List;

public interface WorkOutDocsMapper {
    void batchSave(List<WorkOutDocs> workOutDocsList);
}
