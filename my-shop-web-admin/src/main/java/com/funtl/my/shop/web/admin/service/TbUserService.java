package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.persistence.BaseService;
import com.funtl.my.shop.domain.TbUser;

import java.util.List;

public interface TbUserService extends BaseService<TbUser> {

    /**
     * 邮箱密码登录
     * @param email
     * @return
     */
    TbUser login(String email,String password);

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
}
