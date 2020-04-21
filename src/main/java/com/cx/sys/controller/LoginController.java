package com.cx.sys.controller;

import com.cx.common.model.R;
import com.cx.common.model.TreeNode;
import com.cx.sys.beans.User;
import com.cx.sys.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value = "/login")
public class LoginController {


    @Autowired
    private IUserService userService;

    /**
     * 跳转到系统登录页面
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "sys/login";
    }


    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public R login(String username, String password) {
        UsernamePasswordToken token =
                new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return R.ok();
    }

    /**
     * 跳转到系统初始化页面
     * @return
     */
    @GetMapping("/index")
    public String index(){
//        return "index";
        return "index";
    }

    /**
     * 跳转到系统主页
     * @return
     */
    @GetMapping("/main")
    public String main(){
        return "main";
    }

    /**
     * 获取资源菜单树
     * @return
     */
    @GetMapping("/menu")
    @ResponseBody
    public R menu(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        List<TreeNode> treeNodeList
                = userService.getMenuTreeByUserId(user.getUserId());
        return R.ok("请求成功",treeNodeList);
    }


    @GetMapping("/logout")
    public String logout(){
        // 销毁会话
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/sys/login";
    }
}
