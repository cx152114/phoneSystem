package com.cx.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.business.beans.Customer;
import com.cx.business.beans.SorderDetail;
import com.cx.business.beans.vo.StaticForMonth;
import com.cx.business.beans.vo.StaticStock;

import java.util.List;

public interface StaticStockMapper extends BaseMapper<StaticStock> {

    List<StaticStock> selectTargetSalesList();

    List<StaticStock> selectTargetStockList();

    List<StaticForMonth> staticStockAmountForMonth();

    List<StaticForMonth> staticSaleAmountForMonth();
}
