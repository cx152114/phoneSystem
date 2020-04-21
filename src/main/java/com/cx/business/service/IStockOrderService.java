package com.cx.business.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.SerialNumber;
import com.cx.business.beans.StockOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cx
 * @since 2020-03-18
 */
public interface IStockOrderService extends IService<StockOrder> {


    List<StockOrder> listStockOrderInfo(QueryWrapper queryWrapper);




    int save(List<SerialNumber>serialNumberList, StockOrder stockOrder);

}
