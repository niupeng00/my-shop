package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.validator.BeanValidator;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.web.admin.abstracts.AbstractsBaseServiceImpl;
import com.funtl.my.shop.web.admin.dao.TbContentDao;
import com.funtl.my.shop.web.admin.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbContentServiceImpl extends AbstractsBaseServiceImpl<TbContent, TbContentDao> implements TbContentService {

    @Override
    public BaseResult save(TbContent tbContent) {

        BaseResult baseResult = BaseResult.success("保存内容信息成功");
        String validator = BeanValidator.validator(tbContent);
        //数据验证不懂过
        if (null != validator) {
            baseResult = BaseResult.fail(validator);
        } else {
            tbContent.setUpdated(new Date());
            //新增用户
            if (tbContent.getId() == null){
                tbContent.setCreated(new Date());
                dao.insert(tbContent);
            }else {
                //编辑用户
                update(tbContent);
            }
        }
        return baseResult;
    }



 /*   *//**
     * 有效性验证
     * @param tbContent
     *//*
    private BaseResult checkTbContent(TbContent tbContent){
        BaseResult baseResult = BaseResult.success();

        //非空验证
        if (tbContent.getCategoryId() == null){
            baseResult = BaseResult.fail("内容的所属分类不能为空");
        } else if (!RegexpUtils.checkEmail(tbContent.getContent())){
            baseResult = BaseResult.fail("内容不能为空");
        } else if (StringUtils.isBlank(tbContent.getPic())){
            baseResult = BaseResult.fail("图片绝对路径不能为空");
        } else if (StringUtils.isBlank(tbContent.getPic2())){
            baseResult = BaseResult.fail("图片2不能为空");
        } else if (StringUtils.isBlank(tbContent.getTitle())){
            baseResult = BaseResult.fail("内容标题不能为空");
        } else if (StringUtils.isBlank(tbContent.getSubTitle())){
            baseResult = BaseResult.fail("子标题不能为空");
        } else if (StringUtils.isBlank(tbContent.getUrl())){
            baseResult = BaseResult.fail("链接不能为空");
        }else if (StringUtils.isBlank(tbContent.getTitleDesc())){
            baseResult = BaseResult.fail("标题描述不能为空");
        }
        return baseResult;
    }*/
}
