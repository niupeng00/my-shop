package com.funtl.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.funtl.my.shop.commons.persistence.BaseEntity;
import lombok.Data;

@Data
public class TbContentCategory extends BaseEntity {
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;

    //@JsonProperty(value = "isParent")  //起别名
    private Boolean isParent;

}
