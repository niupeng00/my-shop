package com.funtl.my.shop.commons.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 文件发送工具类
 */
public class EmailSendUtils {

    /**
     * 自己配置的把email写入到xml在注入bean
     */
    @Autowired
    private Email email;

    public void emailSend(String subject, String msg, String... to) throws EmailException {
        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(to);
        email.send();
    }

    //测试方法
    public void emailSend() throws EmailException {
        //发送服务器
        email.setHostName("smtp.qq.com");
        //使用专属端口465或587
        email.setSmtpPort(465);
        //使用加密传输
        email.setSSLOnConnect(true);
        //发送人的账号和授权码
        email.setAuthenticator(new DefaultAuthenticator("topsale@vip.qq.com", "koxvvyvekekecahc"));
        //发件人
        email.setFrom("topsale@vip.qq.com");
        //标题
        email.setSubject("TestMail");
        //正文
        email.setMsg("This is a test mail ...:-)");
        //收件人
        email.addTo("foo@bar.com");
        //发送
        email.send();
    }
}
