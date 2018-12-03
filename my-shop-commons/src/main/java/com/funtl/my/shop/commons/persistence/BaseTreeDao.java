package com.funtl.my.shop.commons.persistence;

import java.util.List;

/**
 * 通用的树形结构接口
 */
public interface BaseTreeDao<T extends BaseEntity> {

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
     * 根据父级节点 ID 查询所有的子节点
     * @param pid
     * @return
     */
    List<T> selectByPid(Long pid);
}
