package com.cx.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.Customer;
import com.cx.business.beans.Warehouse;
import com.cx.business.service.IWarehouseService;
import com.cx.common.model.R;
import com.cx.sys.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cx
 * @since 2020-03-18
 */
@Controller
@RequestMapping("/business/warehouse")
public class WarehouseController {

    @Autowired
    private IWarehouseService warehouseService;


    @GetMapping
    public String warehouseManagement() {
        return "/business/warehouse-list";
    }

    @RequestMapping(value = "/findTargetWarehouse")
    @ResponseBody
    public R findTargetWarehouse(){
        List<Warehouse> warehouseList=warehouseService.list();
        return R.ok().put("rows",warehouseList);
    }





    @RequestMapping(value = "/addWarehouse",method = RequestMethod.POST)
    public String addWarehouse(Warehouse warehouse){
        warehouseService.save(warehouse);
        return "redirect:/business/warehouse/findAllWarehouse";
    }

    @RequestMapping(value = "/editWarehouse",method = RequestMethod.POST)
    public String editWarehouse(Warehouse warehouse){
        warehouseService.updateById(warehouse);
        return "redirect:/business/warehouse/findAllWarehouse";
    }

    @RequestMapping(value = "/removeTargetWarehouse",method = RequestMethod.POST)
    @ResponseBody
    public R removeTargetWarehouse(Integer warehouseId){
        if (warehouseId==null){
            return R.error("请勾选要删除的仓库！");
        }
        try {
           warehouseService.removeById(warehouseId);
           return R.ok();
        } catch (Exception e) {
            return R.error("该仓库下存在权限或者商品信息，不能删除！");
        }
    }


    @RequestMapping(value = "/getTargetWarehouses" )
    @ResponseBody
    public R getTargetWarehouses(){
        List<Warehouse> warehouseList=warehouseService.list();
        return R.ok().put("warehouseList",warehouseList);
    }



}
