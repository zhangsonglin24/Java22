package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.pojo.Finance;
import com.kaishengit.service.FinanceService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private FinanceService financeService;

    @GetMapping("/year")
    public String year(){
        return "report/year";
    }

    @GetMapping("/day")
    public String day(){
        return "report/day";
    }

    @GetMapping("/day/load")
    @ResponseBody
    public DataTablesResult dayLoad(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String day = request.getParameter("day");

        Map<String,Object> queryParam = Maps.newHashMap();
        queryParam.put("start",start);
        queryParam.put("length",length);
        queryParam.put("day",day);

        List<Finance> financeList = financeService.findByQueryParam(queryParam);
        Long count = financeService.count();
        Long filterCount = financeService.filterCount(queryParam);

        return new DataTablesResult(draw,count,filterCount,financeList);

    }

    /**
     * 确认财务流水
     * @param id
     * @return
     */
    @PostMapping("/confirm/{id:\\d+}")
    @ResponseBody
    private AjaxResult confirmFinance(@PathVariable Integer id){
        financeService.confirmById(id);
        return new AjaxResult(AjaxResult.SUCCESS);

    }

    /**
     * 将数据导出为Excel文件
     */
    @GetMapping("/day/{today}/data.xls")
    public void exportXlsFile(@PathVariable String today, HttpServletResponse response) throws IOException {
        List<Finance> financeList = financeService.findByCreateDate(today);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment;filename=\""+today+".xls\"");

        //创建工作表
        Workbook workbook = new HSSFWorkbook();
        //创建sheet页
        Sheet sheet = workbook.createSheet("2017-02-23财务流水");
        //创建行 从0开始
        Row row = sheet.createRow(0);
        //创建单元格
        Cell c1 = row.createCell(0);
        c1.setCellValue("业务流水号");
        Cell c2 = row.createCell(1);
        c2.setCellValue("创建日期");
        row.createCell(2).setCellValue("类型");
        row.createCell(3).setCellValue("金额");
        row.createCell(4).setCellValue("业务模块");
        row.createCell(5).setCellValue("业务流水号");
        row.createCell(6).setCellValue("状态");
        row.createCell(7).setCellValue("备注");
        row.createCell(8).setCellValue("创建人");
        row.createCell(9).setCellValue("确认人");
        row.createCell(10).setCellValue("确认日期");

        for(int i = 0;i < financeList.size();i++){
            Finance finance = financeList.get(i);

            Row dataRow = sheet.createRow(i+1);
            dataRow.createCell(0).setCellValue(finance.getSerialNumber());
            dataRow.createCell(1).setCellValue(finance.getCreateDate());
            dataRow.createCell(2).setCellValue(finance.getType());
            dataRow.createCell(3).setCellValue(finance.getMoney());
            dataRow.createCell(4).setCellValue(finance.getModule());
            dataRow.createCell(5).setCellValue(finance.getModuleSerialNumber());
            dataRow.createCell(6).setCellValue(finance.getState());
            dataRow.createCell(7).setCellValue(finance.getRemark());
            dataRow.createCell(8).setCellValue(finance.getCreateUser());
            dataRow.createCell(9).setCellValue(finance.getConfirmUser());
            dataRow.createCell(10).setCellValue(finance.getConfirmDate());
        }
        //单元格随数据大小自动扩大或缩小
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(10);

        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();


    }

}
