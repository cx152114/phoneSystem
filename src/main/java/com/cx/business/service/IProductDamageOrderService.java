package com.cx.business.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.PreturnOrder;
import com.cx.business.beans.ProductDamageOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.business.beans.SalesOrder;
import com.cx.business.beans.SerialNumber;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cx
 * @since 2020-03-27
 */
public interface IProductDamageOrderService extends IService<ProductDamageOrder> {

    List<ProductDamageOrder> listProductDamageOrder(QueryWrapper queryWrapper);

    int save(List<SerialNumber>serialNumberList, ProductDamageOrder productDamageOrder);

}
