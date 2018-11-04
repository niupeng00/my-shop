package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbContentCategoryDao {

    /**
     * 查询所有信息
     * 排序:parent_id ASC, sort_order ASC, is_parent DESC
     * @return
     */
    List<TbContentCategory> selectAll();
}
