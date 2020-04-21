package com.cx.business.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.PreturnOrder;
import com.cx.business.beans.SalesBackOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.business.beans.SerialNumber;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cx
 * @since 2020-03-21
 */
public interface ISalesBackOrderService extends IService<SalesBackOrder> {

    List<SalesBackOrder> listSalesBackOrderInfo(QueryWrapper queryWrapper);

    int save(List<SerialNumber>serialNumberList, SalesBackOrder salesBackOrder);

}
