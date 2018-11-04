package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.domain.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TbContentCategoryService {

    /**
     * 查询所有信息
     * 排序:parent_id ASC, sort_order ASC, is_parent DESC
     * @return
     */
    List<TbContentCategory> selectAll();

}
