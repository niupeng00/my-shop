package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.validator.BeanValidator;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.web.admin.dao.TbContentDao;
import com.funtl.my.shop.web.admin.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectAll() {
        return tbContentDao.selectAll();
    }

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
                tbContentDao.insert(tbContent);
            }else {
                //编辑用户
                tbContentDao.update(tbContent);
            }
        }
        return baseResult;
    }

    @Override
    public void delete(long id) {
        tbContentDao.delete(id);
    }

    @Override
    public TbContent getById(long id) {
        return tbContentDao.getById(id);
    }

    @Override
    public void update(TbContent tbContent) {
        tbContentDao.update(tbContent);
    }

    @Override
    public void deleteMulti(String[] ids) {
        tbContentDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbContent> page(int start, int length,int draw, TbContent tbContent) {
        int count = tbContentDao.count(tbContent);

        Map<String, Object> params = new HashMap<>();
        params.put("start",start);
        params.put("length", length);
        params.put("tbContent", tbContent);

        PageInfo<TbContent> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setRecordsTotal(count);
        pageInfo.setData(tbContentDao.page(params));


        return pageInfo;
    }

    @Override
    public Integer count(TbContent tbContent) {
        return tbContentDao.count(tbContent);
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
