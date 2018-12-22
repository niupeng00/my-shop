package com.funtl.my.shop.web.api.service.impl;

import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.api.dao.TbUserDao;
import com.funtl.my.shop.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class TbUserServiceImpl  implements TbUserService {

    @Autowired
    TbUserDao tbUserDao;

    @Override
    public TbUser login(TbUser tbUser) {
        TbUser user = tbUserDao.login(tbUser);
        if (null != user) {
            String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
