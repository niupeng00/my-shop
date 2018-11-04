package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    TbUserService tbUserService;

    /**
     * 在有GetMapping post put delete注解的方法执行前执行@ModelAttribute方法
     * @param id
     * @return tbUser
     */
    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser = null;
        //id不为空,则从数据库获取
        if (id != null ){
            tbUser = tbUserService.getById(id);
        }else {
            tbUser = new TbUser();
        }
        return tbUser;
    }

    /**
     * 跳转到用户列表页面
     * @return
     */
    @GetMapping(value = "list")
    public String list(){
        return "user_list";
    }

    /**
     * 用户添加,查看,编辑页面
     * @return
     */
    @GetMapping(value = "form")
    public String from(){
        return "user_form";
    }

    /**
     * 保存用户
     * @param tbUser
     * @return
     */
    @PostMapping(value = "save")
    public String saveUser(TbUser tbUser, Model model,RedirectAttributes redirectAttributes){
        BaseResult baseResult = tbUserService.save(tbUser);

        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }else {
            //保存失败
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }

    }

   /**
     * 搜索
     * @param keyword
     * @param model
     * @return
     */
    @PostMapping(value = "search1")
    public String search1(String keyword,Model model){
        List<TbUser> tbUsers = tbUserService.search1(keyword);
        model.addAttribute("tbUsers",tbUsers);
        return "user_list";
    }

    /**
     * 搜索
     * @param tbUser
     * @param model
     * @return
     */
    @PostMapping(value = "search")
    public String search(TbUser tbUser,Model model){
        List<TbUser> tbUsers = tbUserService.search(tbUser);
        model.addAttribute("tbUsers",tbUsers);
        return "user_list";
    }

    /**
     * 删除用户信息
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult = null;
        if (StringUtils.isNotBlank(ids)){
            baseResult = BaseResult.success("删除用户成功");
            String[] idArray = ids.split(",");
            tbUserService.deleteMulti(idArray);
        } else {
            baseResult = BaseResult.fail("删除用户失败");
        }
        return baseResult;
    }

    /**
     * 分页查询
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page", method = {RequestMethod.GET})
    public PageInfo<TbUser> page(HttpServletRequest request, TbUser tbUser){
        Map<String, Object> result = new HashMap<>();

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        //封装插件 Datatables 需要的结果
        PageInfo<TbUser> pageInfo = tbUserService.page(start,length, draw, tbUser);
        return pageInfo;
        /*Enumeration<String> parameterNames = request.getParameterNames(); //测试传过来几个值
        while (parameterNames.hasMoreElements()){
            System.out.println(String.format("key: %s  value: %s",parameterNames.nextElement(), request.getParameter(parameterNames.nextElement())));
        }*/

    }

    @GetMapping(value = "detail")
    public String detail(TbUser tbUser){
        System.out.println(tbUser.getEmail());
        return "user_detail";
    }
}
