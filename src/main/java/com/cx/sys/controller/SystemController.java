package com.cx.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/sys")
public class SystemController {


    @GetMapping("/lockScreen")
    public String index(){
        return "/sys/lock_screen";
    }
}
