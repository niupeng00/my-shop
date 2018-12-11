package com.funtl.my.shop.web.admin.abstracts;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.persistence.BaseEntity;
import com.funtl.my.shop.commons.persistence.BaseService;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.domain.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ResponseBody
    @RequestMapping(value = "page")
    public PageInfo<T> page(HttpServletRequest request, T entity){
        Map<String, Object> result = new HashMap<>();

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        //封装插件 Datatables 需要的结果
        PageInfo<T> pageInfo = service.page(start, length, draw, entity);
        List<T> tbContents = pageInfo.getData();
        return pageInfo;
    }

    /**
     * 详情页面
     * @param entity
     * @return
     */
    public abstract String detail(T entity);
}
