package com.kaishengit.service.impl;

import com.kaishengit.dto.WorkOutDto;
import com.kaishengit.mapper.WorkMapper;
import com.kaishengit.pojo.Work;
import com.kaishengit.pojo.WorkOut;
import com.kaishengit.service.WorkService;
import com.kaishengit.shiro.ShiroUtil;
import com.kaishengit.util.SerialNumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    private WorkMapper workMapper;

    @Override
    public List<Work> findAllWork() {
        return workMapper.findAllWork();
    }

    @Override
    public Work findWorkById(Integer id) {
        return workMapper.findWorkById(id);
    }

    @Override
    @Transactional
    public String saveWork(WorkOutDto workOutDto) {
        //保存合同
        WorkOut workOut = new WorkOut();
        workOut.setAddress(workOutDto.getAddress());
        workOut.setCardNum(workOutDto.getCardNum());
        workOut.setCompanyName(workOutDto.getCompanyName());
        workOut.setCreateUser(ShiroUtil.getCurrentUserName());
        workOut.setLastCost(0F);
        workOut.setTel(workOutDto.getTel());
        workOut.setCompanyDel(workOut.getCompanyDel());
        workOut.setLinkMan(workOutDto.getLinkMan());
        workOut.setPreCost(0F);
        workOut.setWageNumber(0F);
        workOut.setSerialNumber(SerialNumberUtil.getSerialNumber());

        workMapper.save(workOut);
        return null;
    }
}
