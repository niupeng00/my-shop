package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.domain.TbContentCategory;
import com.funtl.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容分类管理
 */
@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController {

    @Autowired
    private TbContentCategoryService tbcontentcategoryservice;

    @GetMapping(value = "/list")
    public String list(Model model){
        List<TbContentCategory> sourceList = tbcontentcategoryservice.selectAll();
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
    @ResponseBody
    @RequestMapping(value = "/tree/data", method = RequestMethod.POST)
    public List<TbContentCategory> treeData(Long id){
        if (id == null) id = 0L;
        return tbcontentcategoryservice.selectByPid(id);
    }

    /**
     * 添加,查看,编辑页面
     * @return
     */
    @GetMapping(value = "form")
    public String from(Model model){
        TbContentCategory tbContentCategory = new TbContentCategory();
        model.addAttribute("tbContentCategory", tbContentCategory);
        return "content_category_form";
    }

    /**
     * treeTable插件需要的排序功能
     * @param sourceList 数据源的集合
     * @param targetList 排序后的集合
     * @param parentId   父节点的 ID
     */
    private void sortList(List<TbContentCategory> sourceList, List<TbContentCategory> targetList, Long parentId){
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
    }
}
