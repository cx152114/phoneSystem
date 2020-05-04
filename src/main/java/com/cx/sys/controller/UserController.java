package com.cx.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.common.model.R;
import com.cx.common.util.MD5Util;
import com.cx.sys.beans.Dept;
import com.cx.sys.beans.User;
import com.cx.sys.beans.UserRole;
import com.cx.sys.service.IUserRoleService;
import com.cx.sys.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import cn.hutool.core.util.IdUtil;

/**
 * <p>
 * 员工 前端控制器
 * </p>
 *
 * @author cx
 * @since 2020-03-11
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;


    @GetMapping(value = "/userManagement")
    private String userManagement(){
        return "/sys/employee-list";
    }

    @RequestMapping(value = "/showAllUser")
    @ResponseBody
    public R showAllUser(User user,String startTime,String endTime){
        QueryWrapper<User> param = new QueryWrapper<>();
        param.eq("deleted",0);
        if (null!=user.getUserId()){
            param.like("user_id",user.getUserId());
        }
        if (!StringUtils.isEmpty(user.getUsername())){
            param.like("username",user.getUsername());
        }
        if (null!=user.getUserStatus()){
            param.eq("user_status",user.getUserStatus());
        }
        if (null!=user.getDeptId()){
            param.eq("dept_id",user.getDeptId());
        }
        if (!StringUtils.isEmpty(startTime)){
            param.ge("create_time",startTime);
        }
        if (!StringUtils.isEmpty(endTime)){
            param.le("create_time",endTime);
        }
        List<User> userList=userService.list(param);
        return R.ok().put("rows",userList);
    }


//    @RequestMapping(value = "/showAllUser")
//    public ModelAndView showAllUser(ModelAndView modelAndView){
//        QueryWrapper<User> param = new QueryWrapper<>();
//        param.eq("deleted",0);
//        List<User> userList=userService.list(param);
//        //System.out.println(userList);
//        modelAndView.addObject("userList",userList);
//        modelAndView.setViewName("/sys/employee-list");
//        return modelAndView;
//    }


    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public String addCustomer(User user){
        userService.save(user);
        return "redirect:/user/userManagement";
    }


    @RequestMapping(value = "/editUser",method = RequestMethod.POST)
    public String editUser(User user){
        //String salt=IdUtil.simpleUUID().toUpperCase();
        QueryWrapper<String> pram=new QueryWrapper<>();
        User oldUser=userService.getById(user.getUserId());
        String salt=oldUser.getSalt();
        String password=MD5Util.md5_private_salt(user.getPassword(),salt);
        user.setSalt(salt);
        user.setPassword(password);
        userService.updateById(user);
        return "redirect:/user/userManagement";
    }

    @RequestMapping(value = "/removeUser",method = RequestMethod.POST)
    @ResponseBody
    public R removeUser(Integer userId){
        if (userId==null){
            return R.error("请先勾选要移除的用户");
        }
        try {
            User user=userService.getById(userId);
            user.setDeleted(1);
            userService.updateById(user);
            return R.ok();
        } catch (Exception e) {
           e.printStackTrace();
            return R.error("出现未知错误，请联系管理员");
        }

    }

    /**
     * 跳转到给用户分配角色的页面
     *
     * @param userId
     * @return
     */
    @PostMapping("/getRoleList")
    @ResponseBody
    public R getRoleList(Integer userId) {
        List<UserRole> userRoleList = userRoleService.getByUserId(userId);
        System.out.println(userRoleList);
        return R.ok().put("userRoleList",userRoleList).put("userId",userId);
    }

    /**
     * 给用户分配角色
     *
     * @param userId
     * @return
     */
    @PostMapping("/assignRole")
    @ResponseBody
    public R assignRole(Integer userId,
            @RequestParam(name = "roleId",required = false) List<Integer> roleIdList) {
        System.out.println(userId);
        System.out.println(roleIdList);
        userRoleService.save(userId,roleIdList);
        return R.ok();
    }


    @RequestMapping(value = "/getTargetUsers" )
    @ResponseBody
    public R getTargetUsers(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_status",0);
        queryWrapper.eq("deleted",0);
        List<User> userList=userService.list(queryWrapper);
        return R.ok().put("userList",userList);
    }
}

