package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbUserDao {

    /**
     * 登录
     * @param email
     * @return TbUser
     */
    TbUser getByEmail(String email);
    /**
     * 查询用户表全部信息
     * @return List<TbUser>
     */
    public List<TbUser> selectAll();

    /**
     * 添加新用户
     * @param tbUser
     */
    void insert(TbUser tbUser);

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
    TbUser getById(long id);

    /**
     * 更新
     * @param tbUser
     */
    void update(TbUser tbUser);

    /**
     * 根据用户名模糊匹配
     * @param username
     * @return
     */
    List<TbUser> selectByUsername(String username);

    /**
     * 单独一个搜索框
     * @param tbUser
     * @return
     */
    List<TbUser> search1(TbUser tbUser);

    /**
     * 高级搜索
     * @param tbUser
     * @return
     */
    List<TbUser> search(TbUser tbUser);

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
    List<TbUser> page(Map<String, Object> params);

    /**
     * 查询数据的总数
     * @return
     */
    Integer count(TbUser tbUser);
}
