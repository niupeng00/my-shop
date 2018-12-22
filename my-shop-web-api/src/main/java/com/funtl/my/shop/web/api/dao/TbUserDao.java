package com.funtl.my.shop.web.api.dao;

import com.funtl.my.shop.commons.persistence.BaseDao;
import com.funtl.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 面向使用用户即会员管理
 */
@Repository
public interface TbUserDao{

    /**
     * 登录
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);
}
