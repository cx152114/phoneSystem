package com.cx.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.business.beans.vo.StaticForMonth;
import com.cx.business.beans.vo.StaticStock;

import java.util.List;

public interface IStaticStockService extends IService<StaticStock> {

    List<StaticStock> listTargetSalesList();

    List<StaticStock> listTargetStaticStock();

    List<StaticForMonth> staticStockAmountForMonth();

    List<StaticForMonth> staticSaleAmountForMonth();
}
