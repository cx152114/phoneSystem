package com.cx.business.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.PreturnOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.business.beans.SerialNumber;
import com.cx.business.beans.StockOrder;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cx
 * @since 2020-03-20
 */
public interface IPreturnOrderService extends IService<PreturnOrder> {

    List<PreturnOrder> listPreturnOrderInfo(QueryWrapper queryWrapper);

    int save(List<SerialNumber>serialNumberList, PreturnOrder preturnOrder);

}
