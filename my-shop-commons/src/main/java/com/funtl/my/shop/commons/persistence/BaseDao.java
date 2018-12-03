package com.funtl.my.shop.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * 所有数据访问层的基类
 */
public interface BaseDao<T extends BaseEntity> {
    /**
     * 查询全部信息
     * @return List<T>
     */
    List<T> selectAll();

    /**
     * 添加
     * @param entity
     */
    void insert(T entity);

    /**
     * 删除
     * @param id
     */
    void delete(long id);

    /**
     * 根据ID查询用户
     * @param id
     * @return TbUser
     */
    T getById(long id);

    /**
     * 更新
     * @param entity
     */
    void update(T entity);

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
    List<T> page(Map<String, Object> params);

    /**
     * 查询数据的总数
     * @return
     */
    Integer count(T entity);
}
