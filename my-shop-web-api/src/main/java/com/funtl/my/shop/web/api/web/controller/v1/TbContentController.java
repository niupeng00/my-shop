package com.funtl.my.shop.web.api.web.controller.v1;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.web.api.service.TbContentService;
import com.funtl.my.shop.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "${api.path.v1}/contents")
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

    @GetMapping(value = "{category_id}")
    public BaseResult findContentByCategoryId1(@PathVariable(value = "category_id") Long categoryId){
        List<TbContentDTO> tbContentDTOS = null;
        List<TbContent> tbContents = tbContentService.selectByCategoryId(categoryId);
        if (null != tbContents && tbContents.size() > 0){
            tbContentDTOS = new ArrayList<>();
            for (TbContent tbContent : tbContents) {
                TbContentDTO tbContentDTO = new TbContentDTO();
                BeanUtils.copyProperties(tbContent, tbContentDTO);
                tbContentDTOS.add(tbContentDTO);
            }
        }
        return BaseResult.success("成功", tbContentDTOS);
    }
}
