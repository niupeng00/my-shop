package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.utils.RegexpUtils;
import com.funtl.my.shop.commons.validator.BeanValidator;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.abstracts.AbstractsBaseServiceImpl;
import com.funtl.my.shop.web.admin.dao.TbUserDao;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbUserServiceImpl extends AbstractsBaseServiceImpl<TbUser, TbUserDao> implements TbUserService{

    @Override
    public TbUser login(String email,String password){
        TbUser tbUser = dao.getByEmail(email);
        if (tbUser != null){
            //明文密码加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

            //判断加密后的密码和数据库中存放的密码是否匹配
            if (StringUtils.equals(md5Password,tbUser.getPassword())){
                //匹配则表示允许登录
                return tbUser;
            }
        }
        return null;
    }

    @Override
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);

        if (validator != null ){
            //验证不通过
            return BaseResult.fail(validator);
        } else {
            //通过
            tbUser.setUpdated(new Date());
            //新增用户
            if (tbUser.getId() == null){
                tbUser.setCreated(new Date());
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                dao.insert(tbUser);
            }else {
                //编辑用户
                update(tbUser);
            }
            return BaseResult.success("保存用户信息成功");
        }
    }

    @Override
    public List<TbUser> selectByUsername(String username) {
        return dao.selectByUsername(username);
    }

    @Override
    public List<TbUser> search1(String keyword) {
        TbUser tbUser = new TbUser();
        tbUser.setUsername(keyword);
        tbUser.setPhone(keyword);
        tbUser.setEmail(keyword);
        return dao.search1(tbUser);
    }
    @Override
    public List<TbUser> search(TbUser tbUser) {
        return dao.search(tbUser);
    }

    /**
     * 用户信息的有效性验证
     * @param tbUser
     */
    /*private BaseResult checkTbUser(TbUser tbUser){
        BaseResult baseResult = BaseResult.success();

        //非空验证
        if (StringUtils.isBlank(tbUser.getEmail())){
            baseResult = BaseResult.fail("邮箱不能为空,请重新输入");
        } else if (!RegexpUtils.checkEmail(tbUser.getEmail())){
            baseResult = BaseResult.fail("邮箱格式不正确,请重新输入");
        } else if (StringUtils.isBlank(tbUser.getPassword())){
            baseResult = BaseResult.fail("密码不能为空,请重新输入");
        } else if (StringUtils.isBlank(tbUser.getUsername())){
            baseResult = BaseResult.fail("姓名不能为空,请重新输入");
        } else if (StringUtils.isBlank(tbUser.getPhone())){
            baseResult = BaseResult.fail("手机号码不能为空,请重新输入");
        } else if (!RegexpUtils.checkPhone(tbUser.getPhone())){
            baseResult = BaseResult.fail("手机号码格式不正确,请重新输入");
        }
        return baseResult;
    }*/
}
