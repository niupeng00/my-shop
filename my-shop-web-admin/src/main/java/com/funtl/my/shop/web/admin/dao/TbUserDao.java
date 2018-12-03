package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.commons.persistence.BaseDao;
import com.funtl.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbUserDao extends BaseDao<TbUser> {

    /**
     * 登录
     * @param email
     * @return TbUser
     */
    TbUser getByEmail(String email);

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

}
