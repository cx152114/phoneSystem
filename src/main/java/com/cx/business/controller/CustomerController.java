package com.cx.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.business.beans.Customer;
import com.cx.business.service.ICustomerService;
import com.cx.common.model.R;
import com.cx.sys.beans.Dept;
import org.apache.commons.lang.StringUtils;
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
 * @since 2020-03-17
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;


    @GetMapping
    public String list() {
        return "/business/customer-list";
    }

    @PostMapping("/findAllCustomer")
    @ResponseBody
    public R data(String customerName, Integer available, Page<Customer> page) {
        //1.构造查询条件构造器
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(customerName)) {
            queryWrapper.like("customer_name", customerName);
        }
        if (null!=available) {
            queryWrapper.eq("available", available);
        }
        //2.分页查询
        customerService.page(page, queryWrapper);
        //3.返回分页数据
        return R.ok(page);
    }




    @RequestMapping(value = "/addCustomer",method = RequestMethod.POST)
    public String addCustomer(Customer customer){
        customerService.save(customer);
        return "/business/customer-list";
    }

    @RequestMapping(value = "/editCustomer",method = RequestMethod.POST)
    public String editCustomer(Customer customer){
        System.out.println();
        customerService.updateById(customer);
        return "/business/customer-list";
    }

    @RequestMapping(value = "/removeTargetCustomer",method = RequestMethod.POST)
    @ResponseBody
    public R removeTargetCustomer(Integer customerId){
        if (customerId==null){
            return R.error("请勾选要删除的用户信息！");
        }
        try {
            customerService.removeById(customerId);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("未知错误，请联系管理员！");
        }
    }

    @RequestMapping(value = "/findCustomerInfo")
    @ResponseBody
    public R findCustomerInfo(){
        List<Customer> customerList=customerService.list();
        return R.ok().put("customerList",customerList);
    }

}
