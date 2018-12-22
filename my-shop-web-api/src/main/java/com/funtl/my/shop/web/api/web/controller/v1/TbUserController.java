package com.funtl.my.shop.web.api.web.controller.v1;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 会员管理
 */
@Controller
@RequestMapping(value = "${api.path.v1}/users")
public class TbUserController {

    @Autowired
    TbUserService tbUserService;

    @GetMapping(value = "login")
    public BaseResult login(TbUser tbUser){
        TbUser user = tbUserService.login(tbUser);
        if (null == user) {
            return BaseResult.fail("账号或密码错误");
        }
        else {
            return BaseResult.success("登录成功", user);
        }
    }
}
