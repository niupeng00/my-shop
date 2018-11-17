package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.domain.TbUser;

import java.util.List;
import java.util.Map;

public interface TbContentService {

    /**
     * 查询全部信息
     * @return List<TbUser>
     */
    List<TbContent> selectAll();

    /**
     * 添加
     * @param tbUser
     */
    BaseResult save(TbContent tbUser);

    /**
     * 删除
     * @param id
     */
    void delete(long id);

    /**
     * 根据ID查询
     * @param id
     * @return TbUser
     */
    TbContent getById(long id);

    /**
     * 更新
     * @param tbContent
     */
    void update(TbContent tbContent);

    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param tbContent,需要两个参数,start:记录数开始的位置  length:每页记录数
     * @return
     */
    PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent);

    /**
     * 查询数据的总数
     * @return
     */
    Integer count(TbContent tbContent);
}
