package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/content")
public class ContentController {

    @Autowired
    TbContentService tbContentService;


    /**
     * 在有GetMapping post put delete注解的方法执行前执行@ModelAttribute方法
     * @param id
     * @return tbUser
     */
    @ModelAttribute
    public TbContent getTbUser(Long id){
        TbContent tbContent = null;
        //id不为空,则从数据库获取
        if (id != null ){
            tbContent = tbContentService.getById(id);
        }else {
            tbContent = new TbContent();
        }
        return tbContent;
    }

    /**
     * 跳转到用户列表页面
     * @return
     */
    @GetMapping(value = "list")
    public String list(){
        return "content_list";
    }

    /**
     * 用户添加,查看,编辑页面
     * @return
     */
    @GetMapping(value = "form")
    public String from(){
        return "content_form";
    }

    /**
     * 保存用户
     * @param tbContent
     * @return
     */
    @PostMapping(value = "save")
    public String saveUser(TbContent tbContent, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = tbContentService.save(tbContent);

        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }else {
            //保存失败
            model.addAttribute("baseResult",baseResult);
            return "content_form";
        }

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
            baseResult = BaseResult.success("删除成功");
            String[] idArray = ids.split(",");
            tbContentService.deleteMulti(idArray);
        } else {
            baseResult = BaseResult.fail("删除失败");
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
    public PageInfo<TbContent> page(HttpServletRequest request, TbContent tbContent){
        Map<String, Object> result = new HashMap<>();

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        //封装插件 Datatables 需要的结果
        PageInfo<TbContent> pageInfo = tbContentService.page(start,length, draw, tbContent);
        return pageInfo;
        /*Enumeration<String> parameterNames = request.getParameterNames(); //测试传过来几个值
        while (parameterNames.hasMoreElements()){
            System.out.println(String.format("key: %s  value: %s",parameterNames.nextElement(), request.getParameter(parameterNames.nextElement())));
        }*/

    }

    /**
     * 显示详情
     * @param tbContent
     * @return
     */
    @GetMapping(value = "detail")
    public String detail(TbContent tbContent){
        System.out.println(tbContent.getContent());
        return "content_detail";
    }
}
