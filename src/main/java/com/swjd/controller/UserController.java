package com.swjd.controller;

import com.swjd.bean.User;
import com.swjd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/toLogin")
    public String toLogin(Model model){
        User user=new User();
        model.addAttribute("user",user);

        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(User user, Model model, HttpSession session){
        User u=userService.login(user);
        if (u!=null){
            if (u.getFlag().equals("1")){
                session.setAttribute("activeName", u.getuName());
                return "redirect:/customerController/toMain";
            }else {
                model.addAttribute("user",user);
                model.addAttribute("errorMsg","该账号被禁用");
                return "login";
            }
        }else {
            User user1=new User();
            model.addAttribute("user",user1);
            model.addAttribute("errorMsg","账号或者密码错误");
            return "login";
        }
    }
    @RequestMapping("/toMain")
    public String toMain(){
        return "main";
    }
    //退出
    @RequestMapping("/logout")
    public String logout(HttpSession session,Model model){
        session.invalidate();
        User user=new User();
        model.addAttribute("user",user);
        return "login";
    }
    //tao
    @RequestMapping("/gotao")
    public String gotao(){
        return "taobao";
    }
    @RequestMapping("/goche")
    public String goche(){
        return "gouwuche";
    }
}
