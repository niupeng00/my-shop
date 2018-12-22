package com.funtl.my.shop.web.ui.api;

import com.funtl.my.shop.commons.utils.HttpClientUtils;
import com.funtl.my.shop.commons.utils.MapperUtils;
import com.funtl.my.shop.web.ui.dto.TbContent;
import org.springframework.ui.Model;

import java.util.List;

/**
 * 内容管理接口
 */
public class ContentsApi {

    public static List<TbContent> findContentByCategoryId(String id){
        String result = HttpClientUtils.doGet(API.API_CONTENTS + id);

        List<TbContent> tbContents = null;
        try {
            tbContents = MapperUtils.json2listByTree(result, "data", TbContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbContents;
    }

    public static List<TbContent> ppt() {
        return findContentByCategoryId("89");
    }
}
