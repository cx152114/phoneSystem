package com.cx.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.business.beans.vo.StaticForMonth;
import com.cx.business.beans.vo.StaticStock;
import com.cx.business.mapper.StaticStockMapper;
import com.cx.business.service.IStaticStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StaticStockServiceImpl extends ServiceImpl<StaticStockMapper, StaticStock> implements IStaticStockService {

    @Autowired
    private StaticStockMapper staticStockMapper;


    @Override
    public List<StaticStock> listTargetSalesList() {
        return staticStockMapper.selectTargetSalesList();
    }

    @Override
    public List<StaticStock> listTargetStaticStock() {
        return staticStockMapper.selectTargetStockList();
    }

    @Override
    public List<StaticForMonth> staticStockAmountForMonth() {

        return staticStockMapper.staticStockAmountForMonth();
    }

    @Override
    public List<StaticForMonth> staticSaleAmountForMonth() {
        return staticStockMapper.staticSaleAmountForMonth();
    }
}
