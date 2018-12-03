package com.funtl.my.shop.commons.persistence;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;

import java.util.List;

/**
 * 所有业务逻辑层的基类
 */
public interface BaseService<T extends BaseEntity> {

    /**
     * 查询全部信息
     * @return List<TbUser>
     */
    public List<T> selectAll();

    /**
     * 添加
     * @param entity
     */
    BaseResult save(T entity);

    /**
     * 删除
     * @param id
     */
    void delete(long id);

    /**
     * 根据ID查询
     * @param id
     * @return T
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
     * @param start 记录数开始的位置
     * @param length 每页记录数
     * @return
     */
    PageInfo<T> page(int start, int length, int draw, T entity);

    /**
     * 查询数据的总数
     * @return
     */
    Integer count(T tbUser);
}
