package com.cx.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.business.beans.Supplier;
import com.cx.common.model.R;
import com.cx.sys.beans.Dept;
import com.cx.sys.beans.Role;
import com.cx.sys.beans.User;
import com.cx.sys.service.IDeptService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cx
 * @since 2020-03-11
 */
@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @GetMapping
    @RequiresPermissions("sys:dept:menu")
    public String list() {
        return "/sys/deptManagement";
    }

    @PostMapping("/showAllDept")
    @RequiresPermissions("sys:dept:search")
    @ResponseBody
    public R data(Integer deptStatus,String deptName,Page<Dept> page) {
        //1.构造查询条件构造器
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(deptName)) {
            queryWrapper.like("dept_name", deptName);
        }
        if (null!=deptStatus) {
            queryWrapper.eq("dept_status", deptStatus);
        }
        //2.分页查询
        deptService.page(page, queryWrapper);
        //3.返回分页数据
        return R.ok(page);
    }




    @RequestMapping(value = "/addDept",method = RequestMethod.POST)
    @RequiresPermissions("sys:dept:add")
    public String addDept(Dept dept){
        deptService.save(dept);
        return "/sys/deptManagement";
    }


    @RequestMapping(value = "/editDept",method = RequestMethod.POST)
    @RequiresPermissions("sys:dept:alter")
    public String editDept(Dept dept){
        deptService.updateById(dept);
        return "/sys/deptManagement";
    }

    @RequestMapping(value = "/removeDept",method = RequestMethod.POST)
    @RequiresPermissions("sys:dept:remove")
    @ResponseBody
    public R removeDept(Integer deptId){
        if (deptId==null){
            return R.error("请先勾选要删除的部门");
        }
        try {
            deptService.removeById(deptId);
            return R.ok();
        } catch (Exception e) {
            return R.error("未知错误，请联系管理员");
        }
    }

}
