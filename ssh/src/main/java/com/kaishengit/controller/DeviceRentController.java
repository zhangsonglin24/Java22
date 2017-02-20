package com.kaishengit.controller;

import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.DeviceRent;
import com.kaishengit.pojo.DeviceRentDetail;
import com.kaishengit.pojo.DeviceRentDocs;
import com.kaishengit.service.DeviceService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/device/rent")
public class DeviceRentController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public String list(){
        return "device/rent/list";
    }

    /**
     * 新建租赁合同
     */
    @GetMapping("/new")
    public String newRent(Model model){
        List<Device> deviceList = deviceService.findAllDevice();
        model.addAttribute("deviceList",deviceList);
        return "device/rent/new";
    }

    /**
     * 根据设备id查找设备信息
     * @param id
     * @return
     */
    @GetMapping("/device.json")
    @ResponseBody
    public AjaxResult deviceJson(Integer id){
        Device device = deviceService.findDeviceById(id);

        if(device == null){
            return new AjaxResult(AjaxResult.ERROR,"设备不存在");
        }else {
            return new AjaxResult(device);
        }
    }


    @PostMapping("/new")
    @ResponseBody
    public AjaxResult saveRent(@RequestBody DeviceRentDto deviceRentDto) {
        try {
            String serialNumber = deviceService.saveRent(deviceRentDto);
            AjaxResult result = new AjaxResult();
            result.setData(serialNumber);
            result.setStatus(AjaxResult.SUCCESS);
            return result;
        }catch (ServiceException ex){
            return new AjaxResult(AjaxResult.ERROR,ex.getMessage());
        }

    }

    /**
     * 根据流水号显示合同详情
     * @param serialNumber
     * @return
     */
    @GetMapping("/{serialNumber:\\d+}")
    public String showDeviceRent(@PathVariable String serialNumber,Model model) {
        //1.查询合同对象
        DeviceRent deviceRent = deviceService.findDeviceRentBySerialNumber(serialNumber);
        if(deviceRent == null) {
            throw new NotFoundException();
        } else {
            //2.查询合同详情列表
            List<DeviceRentDetail> detailList = deviceService.findDeviceRentDetailListByRentId(deviceRent.getId());
            //3.查询合同文件列表
            List<DeviceRentDocs> docList = deviceService.findDeviceRentDocsListByRentId(deviceRent.getId());

            model.addAttribute("rent",deviceRent);
            model.addAttribute("detailList",detailList);
            model.addAttribute("docList",docList);

            return "device/rent/show";
        }
    }

    /**
     * 合同文件的下载
     */
    @GetMapping("/doc")
    @ResponseBody
    public ResponseEntity<InputStreamSource> downloadFile(Integer id) throws IOException {
        InputStream inputStream = deviceService.downloadFile(id);
        if(inputStream == null){
            throw new NotFoundException();
        }else {
            DeviceRentDocs docs = deviceService.findDeviceRentDocsById(id);
            String fileName = docs.getSourceName();
            //将文件下载标记为二进制
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment",fileName, Charset.forName("UTF-8"));
            return new ResponseEntity<InputStreamSource>(new InputStreamResource(inputStream),headers, HttpStatus.OK);

        }
    }

    /*@GetMapping("/doc")
    public void downloadFile(Integer id, HttpServletResponse response) throws IOException {
        InputStream inputStream = deviceService.downloadFile(id);
        if(inputStream == null){
            throw new NotFoundException();
        }else{
            //将文件下载标记为二进制
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            //更改文件下载名称
            DeviceRentDocs docs = deviceService.findDeviceRentDocsById(id);
            String fileName = docs.getSourceName();
            fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");

            OutputStream outputStream = response.getOutputStream();
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }

    }*/

    /**
     * 合同文件的打包下载
     */
    @GetMapping("/doc/zip")
    public void downloadZipFile(Integer id,HttpServletResponse response) throws IOException {
        DeviceRent rent = deviceService.findDeviceRentById(id);
        if(rent == null){
            throw new NotFoundException();
        }else{
            //将文件下载标记为二进制
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            //更改文件下载的名称
            String fileName = rent.getCompanyName()+".zip";
            fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");

            OutputStream outputStream = response.getOutputStream();
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
            deviceService.downloadZipFile(rent,zipOutputStream);
        }
    }
}
