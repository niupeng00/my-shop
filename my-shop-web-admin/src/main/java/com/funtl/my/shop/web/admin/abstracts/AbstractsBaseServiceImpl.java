package com.funtl.my.shop.web.admin.abstracts;

import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.persistence.BaseDao;
import com.funtl.my.shop.commons.persistence.BaseEntity;
import com.funtl.my.shop.commons.persistence.BaseService;
import com.funtl.my.shop.domain.TbContent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractsBaseServiceImpl<T extends BaseEntity, D extends BaseDao<T>> implements BaseService<T> {

    @Autowired
    protected D dao;

    @Override
    public List<T> selectAll(){
        return dao.selectAll();
    }

    @Override
    public void delete(long id){
        dao.delete(id);
    }

    @Override
    public T getById(long id){
        return dao.getById(id);
    }

    @Override
    public void update(T entity){
        dao.update(entity);
    }

    @Override
    public void deleteMulti(String[] ids){
        dao.deleteMulti(ids);
    }

    @Override
    public Integer count(T entity){
        return dao.count(entity);
    }

    /**
     * 分页查询
     * @param start
     * @param length
     * @param draw
     * @param entity
     * @return
     */
    @Override
    public PageInfo<T> page(int start, int length, int draw, T entity) {
        int count = count(entity);

        Map<String, Object> params = new HashMap<>();
        params.put("start",start);
        params.put("length", length);
        params.put("pageParams", entity);

        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setRecordsTotal(count);
        pageInfo.setData(dao.page(params));


        return pageInfo;
    }
}
