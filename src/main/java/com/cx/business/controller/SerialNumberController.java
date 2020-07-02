package com.cx.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.PhoneInfo;
import com.cx.business.beans.SerialNumber;
import com.cx.business.service.ISerialNumberService;
import com.cx.common.model.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cx
 * @since 2020-03-19
 */
@Controller
@RequestMapping("/business/serialNumber")
public class SerialNumberController {

    @Autowired
    private ISerialNumberService serialNumberService;


    public R getTargetSNInfo(){
        QueryWrapper queryWrapper=new QueryWrapper();
        return R.ok();
    }


    @RequestMapping(value = "/getTargetSnAndPhoneInfo")
    @ResponseBody
    public R getTargetSnAndPhoneInfo(Integer supplierId,Integer warehouseId){
        QueryWrapper<PhoneInfo> queryWrapper=new QueryWrapper<>();
        List<SerialNumber> snAndPhones =serialNumberService.listTargetPhoneInfo(supplierId,warehouseId);
        return R.ok().put("snAndPhones",snAndPhones);
    }

    @RequestMapping(value = "/getCanSalesPhoneInfo")
    @ResponseBody
    public R getCanSalesPhoneInfo(){
        QueryWrapper<PhoneInfo> queryWrapper=new QueryWrapper<>();
        List<SerialNumber> canSalesPhoneInfo =serialNumberService.listCanSalePhoneInfo(queryWrapper);
        return R.ok().put("canSalesPhoneInfo",canSalesPhoneInfo);
    }


    @RequestMapping(value = "/getSalesPhoneInfo")
    @ResponseBody
    public R getSalesPhoneInfo(Integer customerId){
        QueryWrapper<PhoneInfo> queryWrapper=new QueryWrapper<>();
        List<SerialNumber> salesPhoneInfo =serialNumberService.listSalesPhoneInfo(customerId);
        return R.ok().put("salesPhoneInfo",salesPhoneInfo);
    }


    /**
     * 根据传入的仓库号获得可进行调拨的商品信息
     * @param warehouseId
     * @return
     */
    @RequestMapping(value = "/getCanMovementPhoneInfo")
    @ResponseBody
    public R getCanMovementPhoneInfo(Integer warehouseId){
        QueryWrapper<PhoneInfo> queryWrapper=new QueryWrapper<>();
        List<SerialNumber> canMovementPhones =serialNumberService.listCanMovementPhoneInfo(warehouseId);
        return R.ok().put("canMovementPhones",canMovementPhones);
    }

}
