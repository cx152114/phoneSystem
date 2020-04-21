package com.cx.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.Inventory;
import com.cx.business.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/business/inventory")
public class InventoryController {
    @Autowired
    private IInventoryService iInventoryService;

    @RequestMapping(value = "/findAllInventory",method = RequestMethod.GET)
    private ModelAndView findAllInventory(ModelAndView modelAndView){
        QueryWrapper<Inventory> queryWrapper=new QueryWrapper<>();
        List<Inventory> inventoryList =iInventoryService.selectTargetList(queryWrapper);
        modelAndView.addObject("inventoryList",inventoryList);
        modelAndView.setViewName("/business/inventory-show");
        return modelAndView;
    }

}
