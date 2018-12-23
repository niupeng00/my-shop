package com.funtl.my.shop.web.ui.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.funtl.my.shop.commons.utils.RegexpUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class TbUser implements Serializable {

    private Long id;
    private String username;
    @JsonIgnore //此注解:永远不封装到json格式的数据中
    private String password;
    private String phone;
    private String email;
    private String verification;
}
