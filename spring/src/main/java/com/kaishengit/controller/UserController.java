package com.kaishengit.controller;

import com.kaishengit.pojo.Account;
import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){
        return "user/add";
    }
/*
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String save(Account account,String address){
        System.out.println(account.getUsername()+","+account.getPassword()+","+address);
        return "redirect:/home";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(int id,int type){
        System.out.println("id:" + id+","+"type:" + type);
        return "redirect:/home";
    }*/

   /* @RequestMapping(value = "/{id}/{type}/{age}",method = RequestMethod.GET)
    public String showUrl(@PathVariable Integer id,@PathVariable Integer type,@PathVariable Integer age){
        System.out.println("ID:" + id+","+"TYPE:" + type+","+"AGE" + age);
        return "redirect:/home";
    }*/

    /*@RequestMapping(value = "/{id}/{type}/{num}",method = RequestMethod.GET)
    public String showUser(@PathVariable Integer id,
                           @PathVariable Integer type,
                           @PathVariable Integer num,
                           Model model){
       model.addAttribute("id",id);
        model.addAttribute("type",type);
        model.addAttribute("num",num);

        return "user/show";

    }*/

 /*   @RequestMapping(value = "/{id}/{type}/{name}",method = RequestMethod.GET)
   public ModelAndView showUSer(@PathVariable Integer id,@PathVariable Integer type,@PathVariable String name) throws UnsupportedEncodingException {
        name = new String(name.getBytes("ISO8859-1"),"UTF-8");
        ModelAndView mov = new ModelAndView();
        mov.setViewName("user/show");
        mov.addObject("id",id);
        mov.addObject("type",type);
        mov.addObject("name",name);
        return mov;
    }
*/
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){

        Account account = new Account();
        account.setUsername("tom");
        account.setPassword("123123");
        Account account2 = new Account();
        account2.setUsername("jack");
        account2.setPassword("111113");

        List<Account> accountList = Arrays.asList(account,account2);
        model.addAttribute("accountList",accountList);
        return "user/list";
    }

    @GetMapping("/new")
    public String newUser(){
        return "user/add";
    }

    @PostMapping("/new")
    public String saveUser(Account account, RedirectAttributes redirectAttributes){
        System.out.println(account);
        redirectAttributes.addAttribute("message","添加成功");
        return "redirect:/user";
    }

    @GetMapping(value = "/check/{userName}",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String checkUserName(@PathVariable String userName){
        if(userName.equals("tom")){
            return "不能用";
        }else {
            return "可以用";
        }

    }

    @RequestMapping(value = "/json/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Account findById(@PathVariable Integer id){
        Account account = new Account();
        account.setUsername("tom");
        account.setPassword("123123");
        return account;

    }


    @GetMapping("/upload")
    public String fileUpload(){
        return "user/upload";
    }

    @PostMapping("/upload")
    public String saveFile(String name, MultipartFile file){
        System.out.println("Name:" + name);
        if(!file.isEmpty()) {
            //上传表单控件的name属性值
            System.out.println("getName:" + file.getName());
            //上传文件的原始名称
            System.out.println("getOriginalFilename:" + file.getOriginalFilename());
            //文件大小（字节）
            System.out.println("size: " + file.getSize());
            //文件的MIME类型
            System.out.println("ContentType:" + file.getContentType());


        }
        return "redirect:/user";
    }

}
