package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.domain.TbUser;

import java.util.List;

public interface TbUserService {

    /**
     * 邮箱密码登录
     * @param email
     * @return
     */
    TbUser login(String email,String password);
    /**
     * 查询用户表全部信息
     * @return List<TbUser>
     */
    public List<TbUser> selectAll();

    /**
     * 添加新用户
     * @param tbUser
     */
    BaseResult save(TbUser tbUser);

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
     * 根据用户进行模糊匹配
     * @param username
     * @return List<TbUser>
     */
    List<TbUser> selectByUsername(String username);

    /**
     * 单独一个搜索框
     * @param keyword
     * @return
     */
    List<TbUser> search1(String keyword);
    /**
     * 高级搜索
     * @param tbUser
     * @return List
     */
    List<TbUser> search(TbUser tbUser);

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
    PageInfo<TbUser> page(int start, int length,int draw, TbUser tbUser);

    /**
     * 查询数据的总数
     * @return
     */
    Integer count(TbUser tbUser);
}
