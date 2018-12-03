package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbContentCategory;
import com.funtl.my.shop.web.admin.abstracts.AbstractsBaseTreeController;
import com.funtl.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容分类管理
 */
@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController extends AbstractsBaseTreeController<TbContentCategory, TbContentCategoryService> {

    @ModelAttribute
    public TbContentCategory getTbContentCategory(Long id){
        TbContentCategory tbContentCategory = null;

        if (null != id) {
            tbContentCategory = service.getById(id);
        } else {
            tbContentCategory = new TbContentCategory();
        }
        return tbContentCategory;
    }

    @Override
    @GetMapping(value = "/list")
    public String list(Model model){
        List<TbContentCategory> sourceList = service.selectAll();
        List<TbContentCategory> targetList = new ArrayList<>();
        //排序
        sortList(sourceList, targetList, 0L);
        model.addAttribute("list", targetList);
        return "content_category_list";
    }

    /**
     * 属性结构
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "/tree/data", method = RequestMethod.POST)
    public List<TbContentCategory> treeData(Long id){
        if (id == null) id = 0L;
        return service.selectByPid(id);
    }

    @Override
    @PostMapping(value = "save")
    public String save (TbContentCategory tbContentCategory, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = service.save(tbContentCategory);
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "content_category_list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return from(tbContentCategory);
        }
    }
    /**
     * 添加,查看,编辑页面
     * @return
     */
    @Override
    @GetMapping(value = "form")
    public String from(TbContentCategory tbContentCategory){
        return "content_category_form";
    }

   /* *//**
     * treeTable插件需要的排序功能
     * @param sourceList 数据源的集合
     * @param targetList 排序后的集合
     * @param parentId   父节点的 ID
     *//*
    protected void sortList(List<TbContentCategory> sourceList, List<TbContentCategory> targetList, Long parentId){
        for (TbContentCategory tbContentCategory : sourceList) {
            if (tbContentCategory.getParentId().equals(parentId)) {
                targetList.add(tbContentCategory);

                //判断有没有子节点, 如果有则继续追加
                if (tbContentCategory.getIsParent()) {
                    for (TbContentCategory contentCategory : sourceList) {
                        if (contentCategory.getParentId().equals(tbContentCategory.getId())) {
                            sortList(sourceList, targetList, tbContentCategory.getId());
                            break;
                        }
                    }
                }
            }
        }
    }*/
}
