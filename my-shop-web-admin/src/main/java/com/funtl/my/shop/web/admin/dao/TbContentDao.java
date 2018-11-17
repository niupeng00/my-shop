package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbContentDao {

    /**
     * 查询全部信息
     * @return List<TbUser>
     */
    List<TbContent> selectAll();

    /**
     * 添加
     * @param tbUser
     */
    void insert(TbContent tbUser);

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
     * @param params,需要两个参数,start:记录数开始的位置  length:每页记录数
     * @return
     */
    List<TbContent> page(Map<String, Object> params);

    /**
     * 查询数据的总数
     * @return
     */
    Integer count(TbContent tbContent);
}
