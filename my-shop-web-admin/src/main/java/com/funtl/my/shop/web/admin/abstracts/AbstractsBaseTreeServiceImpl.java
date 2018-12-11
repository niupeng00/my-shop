package com.funtl.my.shop.web.admin.abstracts;

import com.funtl.my.shop.commons.persistence.*;
import com.funtl.my.shop.domain.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//泛型依赖注入
public abstract class AbstractsBaseTreeServiceImpl<T extends BaseEntity, D extends BaseTreeDao<T>> implements BaseTreeService<T> {

    @Autowired //经测试此方法可行,但是按理说不可以,保险起见使用 D 注入
    protected BaseTreeDao<T> baseTreeDao;

    /*@Autowired //此方法测试不可用
    D dao;*/

    @Override
    public List<T> selectAll(){
        return baseTreeDao.selectAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(long id) {
        baseTreeDao.delete(id);
    }

    @Override
    public T getById(long id) {
        return baseTreeDao.getById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(T tbContentCategory){
        baseTreeDao.update(tbContentCategory);
    }

    @Override
    public List<T> selectByPid(Long pid) {
        return baseTreeDao.selectByPid(pid);
    }
}
