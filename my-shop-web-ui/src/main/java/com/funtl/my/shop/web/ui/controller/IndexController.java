package com.funtl.my.shop.web.ui.controller;

import com.funtl.my.shop.web.ui.api.ContentsApi;
import com.funtl.my.shop.web.ui.dto.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @GetMapping(value = {"","/index"})
    public String index(Model model){
        //请求幻灯片
        requestContentsPPT(model);
        return "index";
    }

    /**
     * 请求幻灯片
     * @param model
     */
    private void requestContentsPPT(Model model) {
        List<TbContent> tbContents = ContentsApi.ppt();
        model.addAttribute("ppt", tbContents);
    }
}
