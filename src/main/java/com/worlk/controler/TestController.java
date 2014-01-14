package com.worlk.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by XSF on 13-12-18.
 */
@Controller

@RequestMapping(value="/user")
public class TestController {
    @RequestMapping(value="/test")
    public String test()
    {
        return "index";
    }

    @RequestMapping(value="/showCompanyListByXls")
    public String excelView()
    {
        return "companyListExcel";
    }
}
