package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.commons.persistence.BaseDao;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbContentDao extends BaseDao<TbContent> {

    /**
     * 添加
     * @param tbUser
     */
    void insert(TbContent tbUser);

    /**
     * 删除
     * @param id
     */
    void delete(long id);

    /**
     * 更新
     * @param tbContent
     */
    void update(TbContent tbContent);

    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);


    /**
     * 查询数据的总数
     * @return
     */
    Integer count(TbContent tbContent);
}
