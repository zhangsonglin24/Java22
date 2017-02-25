package com.kaishengit.service.impl;

import com.google.common.collect.Lists;
import com.kaishengit.dto.WorkOutDto;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.FinanceMapper;
import com.kaishengit.mapper.WorkMapper;
import com.kaishengit.mapper.WorkOutDetailMapper;
import com.kaishengit.mapper.WorkOutDocsMapper;
import com.kaishengit.pojo.*;
import com.kaishengit.service.WorkService;
import com.kaishengit.shiro.ShiroUtil;
import com.kaishengit.util.SerialNumberUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    private WorkMapper workMapper;
    @Autowired
    private WorkOutDetailMapper workOutDetailMapper;
    @Autowired
    private WorkOutDocsMapper workOutDocsMapper;
    @Autowired
    private FinanceMapper financeMapper;

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
        workOut.setLastCost(workOut.getLastCost());
        workOut.setTel(workOutDto.getTel());
        workOut.setCompanyDel(workOut.getCompanyDel());
        workOut.setLinkMan(workOutDto.getLinkMan());
        workOut.setPreCost(workOut.getPreCost());
        workOut.setWageNumber(workOut.getWageNumber());
        workOut.setSerialNumber(SerialNumberUtil.getSerialNumber());

        workMapper.save(workOut);


        //2. 保存合同详情
        List<WorkOutDto.WorkOutBean> workArray  = workOutDto.getWorkArray();
        List<WorkOutDetail> detailList = Lists.newArrayList();
        float total = 0F;
        for(WorkOutDto.WorkOutBean bean : workArray) {
            //查询当前员工是否剩余
            Work work = workMapper.findWorkById(bean.getId());
            if(work.getCurrentNum() < bean.getOutNum()){
                throw new ServiceException(work.getName()+"员工不足");
            }else{
                work.setCurrentNum(work.getCurrentNum() - bean.getOutNum());
                workMapper.updateCurrentNum(work);
            }

            WorkOutDetail workOutDetail = new WorkOutDetail();
            workOutDetail.setWorkName(bean.getWorkName());
            workOutDetail.setTotalPrice(bean.getTotal());
            workOutDetail.setWorkWage(bean.getWage());
            workOutDetail.setWorkUnit(bean.getUnit());
            workOutDetail.setNum(bean.getOutNum());
            workOutDetail.setWorkId(work.getId());

            detailList.add(workOutDetail);

            total += bean.getTotal();
        }
        if(!detailList.isEmpty()) {
            workOutDetailMapper.batchSave(detailList);
        }

        //3. 保存文件
        List<WorkOutDto.DocBean> docBeanList = workOutDto.getFileArray();
        List<WorkOutDocs> workOutDocsList = Lists.newArrayList();
        for(WorkOutDto.DocBean doc : docBeanList) {
            WorkOutDocs outDocs = new WorkOutDocs();
            outDocs.setId(workOut.getId());
            outDocs.setNewName(doc.getNewName());
            outDocs.setSourceName(doc.getSourceName());

            workOutDocsList.add(outDocs);
        }
        if(!docBeanList.isEmpty()) {
           workOutDocsMapper.batchSave(workOutDocsList);
        }

        //4. 写入财务流水
        Finance finance = new Finance();
        finance.setCreateUser(ShiroUtil.getCurrentUserName());
        finance.setType(Finance.TYPE_IN);
        finance.setCreateDate(DateTime.now().toString("yyyy-MM-dd"));
        finance.setModule("劳务派遣");
        finance.setMoney(workOut.getPreCost());
        finance.setSerialNumber(SerialNumberUtil.getSerialNumber());
        finance.setState(Finance.STATE_NO);
        finance.setRemark("预付款");
        finance.setModuleSerialNumber(workOut.getSerialNumber());

        financeMapper.save(finance);

        return workOut.getSerialNumber();
    }
}
