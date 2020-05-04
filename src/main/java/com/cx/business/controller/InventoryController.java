package com.cx.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.Inventory;
import com.cx.business.service.IInventoryService;
import com.cx.common.model.R;
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
@RequestMapping("/business/inventory")
public class InventoryController {
    @Autowired
    private IInventoryService iInventoryService;

    /**
     * 跳转到库存查询页面
     * @return
     */
    @GetMapping
    private String searchProductInventory(){
        return "/business/inventory-show";
    }

    @RequestMapping(value = "/findAllInventory",method = RequestMethod.POST)
    @ResponseBody
    private R findAllInventory(Inventory inventory){
        QueryWrapper<Inventory> queryWrapper=new QueryWrapper<Inventory>();
        //List<Inventory> inventoryList =iInventoryService.list(queryWrapper);
        List<Inventory> inventoryList =iInventoryService.selectTargetList(queryWrapper);
        return R.ok().put("rows",inventoryList);
    }


}
