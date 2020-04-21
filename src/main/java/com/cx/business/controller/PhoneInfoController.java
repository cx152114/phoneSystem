package com.cx.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.business.beans.Customer;
import com.cx.business.beans.PhoneInfo;
import com.cx.business.service.IPhoneInfoService;
import com.cx.common.model.R;
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
@RequestMapping("/business/phoneInfo")
public class PhoneInfoController {

    @Autowired
    private IPhoneInfoService phoneInfoService;

    @GetMapping
    public String list() {
        return "/business/product-list";
    }


    @PostMapping("/findAllPhoneInfo")
    @ResponseBody
    public R data(Integer phoneId ,String phoneName,String phoneType,String phoneColor,String phoneRam,String phoneStorage,String phoneNetwork, Integer phoneState,Integer supplierId, Page<PhoneInfo> page) {
        PhoneInfo phoneInfo=new PhoneInfo(phoneId,phoneName,supplierId,phoneType,phoneColor,phoneRam,phoneStorage,phoneNetwork,phoneState);

        //1.构造查询条件构造器
        QueryWrapper queryWrapper = new QueryWrapper();



        if (!StringUtils.isEmpty(phoneType)) {
            queryWrapper.eq("phone_type", phoneType);
        }
        if (!StringUtils.isEmpty(phoneColor)) {
            System.out.println(phoneColor);
            queryWrapper.eq("phone_color", phoneColor);
        }
        if (!StringUtils.isEmpty(phoneRam)) {
            queryWrapper.eq("phone_RAM", phoneRam);
        }
        if (!StringUtils.isEmpty(phoneStorage)) {
            queryWrapper.eq("phone_storage", phoneStorage);
        }
        if (!StringUtils.isEmpty(phoneNetwork)) {
            queryWrapper.eq("phone_network", phoneNetwork);
        }
        if (null!=phoneState){
            queryWrapper.eq("phone_state", phoneState);
        }
        if (null!=supplierId){
            queryWrapper.eq("supplier_id",supplierId);
        }

        if (null!=phoneId) {
            queryWrapper.like("phone_id", phoneId);
        }
        if (!StringUtils.isEmpty(phoneName)){
            queryWrapper.like("phone_name",phoneName);
        }

        phoneInfoService.list(queryWrapper);

        //2.分页查询
        phoneInfoService.page(page, queryWrapper);
        page.setRecords(phoneInfoService.listPhoneInfo(phoneInfo));
        //3.返回分页数据
        return R.ok(page);
    }


    @RequestMapping(value = "/findAllPhoneInfo",method = RequestMethod.GET)
    public ModelAndView findAllPhoneInfo(ModelAndView modelAndView){
        List<PhoneInfo> phoneList =phoneInfoService.list();
        modelAndView.addObject("phoneList",phoneList);
        modelAndView.setViewName("/business/product-list");
        return modelAndView;
    }




    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public String addProduct(PhoneInfo phoneInfo){
        phoneInfoService.save(phoneInfo);
        return "/business/product-list";
    }

    @RequestMapping(value = "/editProduct",method = RequestMethod.POST)
    public String editProduct(PhoneInfo phoneInfo){
        phoneInfoService.updateById(phoneInfo);
        return "/business/product-list";
    }

    @RequestMapping(value = "/removeProduct",method = RequestMethod.POST)
    @ResponseBody
    public R removeProduct(Integer phoneId){
        if (phoneId==null){
            return R.error("请先勾选要删除的商品");
        }
        try {
            phoneInfoService.removeById(phoneId);
            return R.ok();
        } catch (Exception e) {
            return R.error("该商品下存在商品订单信息，不能删除！");
        }
    }


    @RequestMapping(value = "/findSomePhones")
    @ResponseBody
    public R findAllPhoneInfo(){
        QueryWrapper<PhoneInfo> queryWrapper=new QueryWrapper<>();
        List<PhoneInfo> phoneLists =phoneInfoService.list();
        return R.ok().put("phoneLists",phoneLists);
    }


    @RequestMapping(value = "/findTargetPhones")
    @ResponseBody
    public R findTargetPhones(Integer supplierId){
        if (null==supplierId){
            return R.error("请先选择供应商");
        }
        QueryWrapper<PhoneInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("supplier_id",supplierId);
        List<PhoneInfo> phoneLists =phoneInfoService.list(queryWrapper);
        return R.ok().put("phoneLists",phoneLists);
    }






}
