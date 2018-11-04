package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.utils.RegexpUtils;
import com.funtl.my.shop.domain.TbUser;
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
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public TbUser login(String email,String password){
        TbUser tbUser = tbUserDao.getByEmail(email);
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
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public BaseResult save(TbUser tbUser) {
        BaseResult baseResult = checkTbUser(tbUser);
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            tbUser.setUpdated(new Date());
            //新增用户
            if (tbUser.getId() == null){
                tbUser.setCreated(new Date());
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUserDao.insert(tbUser);
            }else {
                //编辑用户
                tbUserDao.update(tbUser);
            }
            baseResult.setMessage("保存用户信息成功");
        }



        return baseResult;
    }

    @Override
    public void delete(long id) {
        tbUserDao.delete(id);
    }

    @Override
    public TbUser getById(long id) {
        return tbUserDao.getById(id);
    }

    @Override
    public void update(TbUser tbUser) {
        tbUserDao.update(tbUser);
    }

    @Override
    public List<TbUser> selectByUsername(String username) {
        return tbUserDao.selectByUsername(username);
    }

    @Override
    public List<TbUser> search1(String keyword) {
        TbUser tbUser = new TbUser();
        tbUser.setUsername(keyword);
        tbUser.setPhone(keyword);
        tbUser.setEmail(keyword);
        return tbUserDao.search1(tbUser);
    }
    @Override
    public List<TbUser> search(TbUser tbUser) {
        return tbUserDao.search(tbUser);
    }

    @Override
    public void deleteMulti(String[] ids) {
        tbUserDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbUser> page(int start, int length,int draw, TbUser tbUser) {

        int count = tbUserDao.count(tbUser);

        Map<String, Object> params = new HashMap<>();
        params.put("start",start);
        params.put("length", length);
        params.put("tbUser", tbUser);

        PageInfo<TbUser> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setRecordsTotal(count);
        pageInfo.setData(tbUserDao.page(params));


        return pageInfo;
    }

    @Override
    public Integer count(TbUser tbUser) {
        return tbUserDao.count(tbUser);
    }

    /**
     * 用户信息的有效性验证
     * @param tbUser
     */
    private BaseResult checkTbUser(TbUser tbUser){
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
    }
}
