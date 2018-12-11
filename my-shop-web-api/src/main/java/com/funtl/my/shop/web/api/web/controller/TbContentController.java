package com.funtl.my.shop.web.api.web.controller;

import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.domain.TbContentCategory;
import com.funtl.my.shop.web.api.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "content")
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id){

        TbContent tbContent = null;

        if (id == null){
            tbContent = new TbContent();
        }
        return tbContent;
    }

    @GetMapping(value = "findContentByCategoryId")
    public List<TbContent> findContentByCategoryId(Long categoryId){
        return tbContentService.selectByCategoryId(categoryId);
    }
}
