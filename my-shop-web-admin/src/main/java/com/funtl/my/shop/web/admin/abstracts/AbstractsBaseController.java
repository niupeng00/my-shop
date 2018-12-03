package com.funtl.my.shop.web.admin.abstracts;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.persistence.BaseEntity;
import com.funtl.my.shop.commons.persistence.BaseService;
import com.funtl.my.shop.domain.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractsBaseController<T extends BaseEntity, S extends BaseService<T>> {

    @Autowired
    protected S service;

    /**
     * 跳转列表页面
     * @return
     */
    public abstract String list();

    /**&
     * 跳转表单页面
     * @return
     */
    public abstract String from();

    /**
     * 保存
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    public abstract String save(T entity, Model model, RedirectAttributes redirectAttributes);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public abstract BaseResult delete(String ids);

    /**
     * 分页
     * @param request
     * @param entity
     * @return
     */
    public abstract PageInfo<T> page(HttpServletRequest request, T entity);

    /**
     * 详情页面
     * @param entity
     * @return
     */
    public abstract String detail(T entity);
}
