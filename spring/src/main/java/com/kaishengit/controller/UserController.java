package com.kaishengit.controller;

import com.kaishengit.pojo.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){
        return "user/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String save(Account account,String address){
        System.out.println(account.getUsername()+","+account.getPassword()+","+address);
        return "redirect:/home";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(int id,int type){
        System.out.println("id:" + id+","+"type:" + type);
        return "redirect:/home";
    }

   /* @RequestMapping(value = "/{id}/{type}/{age}",method = RequestMethod.GET)
    public String showUrl(@PathVariable Integer id,@PathVariable Integer type,@PathVariable Integer age){
        System.out.println("ID:" + id+","+"TYPE:" + type+","+"AGE" + age);
        return "redirect:/home";
    }*/

    @RequestMapping(value = "/{id}/{type}/{num}",method = RequestMethod.GET)
    public String showUser(@PathVariable Integer id,
                           @PathVariable Integer type,
                           @PathVariable Integer num,
                           Model model){
       model.addAttribute("id",id);
        model.addAttribute("type",type);
        model.addAttribute("num",num);

        return "user/show";

    }

    @RequestMapping(value = "/{id}/{type}",method = RequestMethod.GET)
   public ModelAndView showUSer(@PathVariable Integer id,@PathVariable Integer type){
        ModelAndView mov = new ModelAndView();
        mov.setViewName("user/show");
        mov.addObject("id",id);
        mov.addObject("type",type);
        return mov;
    }

}
