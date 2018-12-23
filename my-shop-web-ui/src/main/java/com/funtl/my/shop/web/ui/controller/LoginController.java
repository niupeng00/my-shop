package com.funtl.my.shop.web.ui.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.utils.EmailSendUtils;
import com.funtl.my.shop.web.ui.api.UsersApi;
import com.funtl.my.shop.web.ui.constant.SystemConstants;
import com.funtl.my.shop.web.ui.dto.TbUser;
import com.google.code.kaptcha.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private EmailSendUtils emailSendUtils;

    @GetMapping(value = "login")
    public String login(){
        return "login";
    }

    @PostMapping(value = "login")
    public String login(TbUser tbUser, Model model, HttpServletRequest request) throws Exception {
        //验证码验证
        if (!checkVerification(tbUser, request)){
            model.addAttribute("baseResult", BaseResult.fail("验证码输入错误, 请重新输入"));
            return "login";
        }

        TbUser user = UsersApi.login(tbUser);

        //登录失败
        if (null == user) {
            model.addAttribute("baseResult", BaseResult.fail("用户名或密码错误, 请重新输入"));
            return "login";
        }
        //登录成功
        else {
            //将用户信息放入到 session 中
            request.getSession().setAttribute(SystemConstants.SESSION_USER_KEY, user);
            //测试发送邮件
            emailSendUtils.emailSend("用户登录成功", String.format("用户 [%s] 登录 本平台", user.getUsername()), "lusifer@yeah.net");
            return "redirect:/index";
        }
    }

    /**
     * 注销用户
     * @return
     */
    @GetMapping(value = "logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/index";
    }

    private boolean checkVerification(TbUser tbUser, HttpServletRequest request){
        String verification = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

        if (StringUtils.equals(verification, tbUser.getVerification())){
            return true;
        }
        return false;
    }
}
