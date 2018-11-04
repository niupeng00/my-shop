package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.constant.ConstantUtils;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    TbUserService tbUserService;

    @GetMapping(value = {"","login"})
    public String login(){
        return "login";
    }

    @PostMapping(value = "login")
    public String login(@RequestParam(required = true)String email, @RequestParam(required = true)String password, HttpServletRequest request, Model model){
        TbUser tbUser = tbUserService.login(email,password);
        if (tbUser == null) {
            //登录失败
            model.addAttribute("message","用户名密码错误,请重新输入");
            return login();
        }else {
            //登录成功,同时将用户信息放入session中
            request.getSession().setAttribute(ConstantUtils.SESSION_USER,tbUser);
            return "redirect:/main";
        }
    }
    @GetMapping(value = "logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return login();
    }
}
