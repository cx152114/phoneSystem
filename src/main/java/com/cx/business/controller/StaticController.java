package com.cx.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.business.beans.Customer;
import com.cx.business.beans.vo.StaticForMonth;
import com.cx.business.beans.vo.StaticStock;
import com.cx.business.service.IStaticStockService;
import com.cx.common.model.R;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/business/static")
public class StaticController {

    @Autowired
    private IStaticStockService staticStockService;

    @GetMapping
    @RequiresPermissions("business:staticSalesSort:menu")
    public String list() {
        return "/business/static-list";
    }

    @PostMapping("/staticSalesSort")
    @RequiresPermissions("business:staticSalesSort:menu")
    @ResponseBody
    public R staticSalesSort() {
        List<StaticStock> StaticList=staticStockService.listTargetSalesList();
        return R.ok().put("rows",StaticList);
    }

    @GetMapping("/staticStockSort")
    @RequiresPermissions("business:staticStockSort:menu")
    public String stockList() {
        return "/business/stock_static_list";
    }


    @PostMapping("/staticStockSort")
    @RequiresPermissions("business:staticStockSort:menu")
    @ResponseBody
    public R staticStockSort() {
        List<StaticStock> staticStock=staticStockService.listTargetStaticStock();
        return R.ok().put("rows",staticStock);
    }


    @GetMapping("/staticStockMonth")
    //@RequiresPermissions("business:staticStockSort:menu")
    public String staticStockMonth() {
        return "/business/staticStackForMonth";
    }

    @PostMapping("/staticStockForMonth")
    //@RequiresPermissions("business:staticStockSort:menu")
    @ResponseBody
    public R staticStockForMonth() {
        List<StaticForMonth> staticStock=staticStockService.staticStockAmountForMonth();
        ArrayList<Integer> staticStockForMonth=new ArrayList<>(12);

        for (int i = 0; i < 12; i++) {
            staticStockForMonth.add(0);
        }
        for (StaticForMonth staticForMonth:staticStock) {
            staticStockForMonth.set(staticForMonth.getMonth()-1,staticForMonth.getNumber());
        }
        return R.ok().put("static",staticStockForMonth);
    }


    @GetMapping("/staticSalesForMonth")
    //@RequiresPermissions("business:staticStockSort:menu")
    public String staticSalesForMonth() {
        return "/business/staticSalesForMonth";
    }



    @PostMapping("/staticSalesForMonth")
    //@RequiresPermissions("business:staticStockSort:menu")
    @ResponseBody
    public R staticSaleForMonth() {
        List<StaticForMonth> staticSales=staticStockService.staticSaleAmountForMonth();
        ArrayList<Integer> staticSalesForMonth=new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            staticSalesForMonth.add(0);
        }
        for (StaticForMonth staticForMonth:staticSales) {
            staticSalesForMonth.set(staticForMonth.getMonth()-1,staticForMonth.getNumber());
        }
        return R.ok().put("static",staticSalesForMonth);
    }


}
