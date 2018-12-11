package com.funtl.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "main")
    public String main(){
        return "main";
    }

    /**
     *  JS 中的可选参数
     *  delete(id, name) {
     *      if (!name) name = null;
     *  }
     *  调用
     *  delete(1, "某某");
     *  和,name是可选参数,可传,可不传
     *  delete(1);
     *
     *
     *  JAVA 中的可变参数<注意:可变参数只能放到参数的最后一个,其实他是一个数组>
     *  delete(int id, String... name);
     *  调用
     *  delete(1,"某某");
     *  和,name是可变参数,可传,可不传
     *  delete(1);
     *
     *
     *
     *
     *
     *
     */
}
