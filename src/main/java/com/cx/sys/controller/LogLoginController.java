package com.cx.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.common.model.R;
import com.cx.sys.beans.LogLogin;
import com.cx.sys.service.ILogLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cx
 * @since 2020-03-17
 */
@Controller
@RequestMapping("/sys/log-login")
public class LogLoginController {


    @Autowired
    private ILogLoginService logLoginService;



    @RequestMapping(value = "loadAllLoginInfo")
    public ModelAndView loadAllLoginInfo(ModelAndView modelAndView){
        List<LogLogin> loginList=logLoginService.list();
        modelAndView.addObject("loginList",loginList);
        modelAndView.setViewName("/sys/loginLog");
        return modelAndView;
    }

}
