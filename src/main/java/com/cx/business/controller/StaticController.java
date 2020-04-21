package com.cx.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.business.beans.Customer;
import com.cx.business.beans.vo.StaticStock;
import com.cx.business.service.IStaticStockService;
import com.cx.common.model.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/business/static")
public class StaticController {

    @Autowired
    private IStaticStockService staticStockService;

    @GetMapping
    public String list() {
        return "/business/static-list";
    }

    @PostMapping("/staticSalesSort")
    @ResponseBody
    public R staticSalesSort() {
        List<StaticStock> StaticList=staticStockService.listTargetSalesList();
        return R.ok().put("rows",StaticList);
    }

    @GetMapping("/staticStockSort")
    public String stockList() {
        return "/business/stock_static_list";
    }


    @PostMapping("/staticStockSort")
    @ResponseBody
    public R staticStockSort() {
        List<StaticStock> staticStock=staticStockService.listTargetStaticStock();
        return R.ok().put("rows",staticStock);
    }


}
