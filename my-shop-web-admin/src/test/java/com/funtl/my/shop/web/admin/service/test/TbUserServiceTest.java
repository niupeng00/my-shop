package com.funtl.my.shop.web.admin.service.test;


import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {

    @Autowired
    private TbUserService tbUseService;

    @Test
    public void testSelectAll() {
        List<TbUser> tbUsers = tbUseService.selectAll();
        for (TbUser tbUser : tbUsers) {
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public void testSetTbUser(){
        TbUser tbUser = new TbUser();
        tbUser.setUsername("niupeng");
        tbUser.setEmail("niu@QQ.com");
        tbUser.setPhone("13231191983");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUseService.save(tbUser);
    }
    @Test
    public void testMD5(){
        System.err.println(DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex("123456".getBytes()).getBytes()));
    }
    @Test
    public void testDelete(){
        tbUseService.delete(38L);
    }
    @Test
    public void testGetById(){
        TbUser tbUser = tbUseService.getById(36L);
        System.out.println("tbUser.getUsername = " + tbUser.getUsername());
    }
    @Test
    public void testUpdate(){
        TbUser tbUser = tbUseService.getById(36L);
        tbUser.setUpdated(new Date());
        tbUseService.update(tbUser);
    }
    @Test
    public void testselectByUsername(){
        List<TbUser> tbUsers = tbUseService.selectByUsername("niu");
        tbUsers.stream().map(tbUser -> tbUser.getUsername()).forEach(System.out::println);
        tbUsers.stream().map(TbUser::getUsername).forEachOrdered(System.out::println);
        for (TbUser tbUser : tbUsers) {
            String username = tbUser.getUsername();
            System.out.println(username);
        }
    }

}
