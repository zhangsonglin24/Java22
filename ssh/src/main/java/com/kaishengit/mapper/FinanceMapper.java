package com.kaishengit.mapper;

import com.kaishengit.pojo.Finance;

import java.util.List;
import java.util.Map;

public interface FinanceMapper {

    void save(Finance finance);

    List<Finance> findByQueryParam(Map<String, Object> queryParam);

    Long count();

    Long filterCount(Map<String, Object> queryParam);

    Finance findById(Integer id);

    void updateState(Finance finance);

    List<Finance> findByCreateDate(String today);
}
