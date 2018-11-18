package com.funtl.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.funtl.my.shop.commons.persistence.BaseEntity;
import com.funtl.my.shop.commons.utils.RegexpUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class TbUser extends BaseEntity {

    @Length(min = 6, max = 20, message = "姓名长度必须介于6 - 20位之间")
    private String username;
    @JsonIgnore //此注解:永远不封装到json格式的数据中
    private String password;

    @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确") //正在工具类
    private String phone;

    @Pattern(regexp = RegexpUtils.EMAIL, message = "手机号格式不正确")
    private String email;
}

