package com.kaishengit.controller;

import com.kaishengit.pojo.Device;
import com.kaishengit.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 系统设置中的设备管理控制器
 */
@Controller
@RequestMapping("/setting/device")
public class SettingDeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public String list(){
        return "setting/device/list";
    }

    @GetMapping("/new")
    public String newDevice(){
        return "/setting/device/new";
    }

    @PostMapping("/new")
    public String newDevice(Device device, RedirectAttributes redirectAttributes){
        deviceService.saveNewDevice(device);
        redirectAttributes.addFlashAttribute("message","操作成功");
        return "redirect:/setting/device";
    }

}
