package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.persistence.BaseEntity;
import com.funtl.my.shop.commons.validator.BeanValidator;
import com.funtl.my.shop.domain.TbContentCategory;
import com.funtl.my.shop.web.admin.abstracts.AbstractsBaseTreeServiceImpl;
import com.funtl.my.shop.web.admin.dao.TbContentCategoryDao;
import com.funtl.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TbContentCategoryServiceImpl extends AbstractsBaseTreeServiceImpl<TbContentCategory, TbContentCategoryDao> implements TbContentCategoryService {

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContentCategory tbContentCategory) {
        String validator = BeanValidator.validator(tbContentCategory);
        if (validator != null) {

            return BaseResult.fail(validator);
        } else {
            TbContentCategory parent = tbContentCategory.getParent();
            //如果没有选择父级节点,则默认设置为根目录
            if (null == parent || null == parent.getId()) {
                //0代表根目录
                parent.setId(0L);

            }
            tbContentCategory.setUpdated(new Date());
            //新增
            if (null == tbContentCategory.getId()) {
                tbContentCategory.setCreated(new Date());
                tbContentCategory.setIsParent(false);
                if (parent.getId() != 0L) {
                    //判断当前新增的节点有没有父级节点
                    TbContentCategory currentCategoryParent = getById(parent.getId());
                    if (null != currentCategoryParent) {
                        //为父节点设置 isParent 为 true
                        currentCategoryParent.setIsParent(true);
                        update(currentCategoryParent);
                    }
                } else { //父级节点为 0 ; 表示为根目录
                    //根目录一定是父级目录
                    tbContentCategory.setIsParent(true);
                }
                tbContentCategory.setIsParent(false);
                baseTreeDao.insert(tbContentCategory);
            } else {
                //修改
                update(tbContentCategory);
            }
            return BaseResult.success("报错分类信息成功");
        }
    }


}
