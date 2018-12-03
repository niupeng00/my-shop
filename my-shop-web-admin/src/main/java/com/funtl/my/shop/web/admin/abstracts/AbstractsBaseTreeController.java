package com.funtl.my.shop.web.admin.abstracts;

import com.funtl.my.shop.commons.persistence.BaseEntity;
import com.funtl.my.shop.commons.persistence.BaseTreeEntity;
import com.funtl.my.shop.commons.persistence.BaseTreeService;
import com.funtl.my.shop.domain.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public abstract class AbstractsBaseTreeController<T extends BaseTreeEntity, S extends BaseTreeService<T>> {

    @Autowired
    protected S service;

    /**
     * 跳转列表页
     * @param model
     * @return
     */
    public abstract String list(Model model);

    /**
     * 跳转详情页面
     * @param id
     * @return
     */
    public  abstract List<T> treeData(Long id);

    /**
     * 保存
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    public abstract String save (T entity, Model model, RedirectAttributes redirectAttributes);

    /**
     * 跳转表单页面
     * @param tbContentCategory
     * @return
     */
    public abstract String from(TbContentCategory tbContentCategory);

    /**
     * treeTable插件需要的排序功能
     * @param sourceList 数据源的集合
     * @param targetList 排序后的集合
     * @param parentId   父节点的 ID
     */
    protected void sortList(List<T> sourceList, List<T> targetList, Long parentId){
        for (T sourceEntity : sourceList) {
            if (sourceEntity.getParent().getId().equals(parentId)) {
                targetList.add(sourceEntity);

                //判断有没有子节点, 如果有则继续追加
                if (sourceEntity.getIsParent()) {
                    for (T currentEntity : sourceList) {
                        if (currentEntity.getParent().getId().equals(sourceEntity.getId())) {
                            sortList(sourceList, targetList, sourceEntity.getId());
                            break;
                        }
                    }
                }
            }
        }
    }
}
