package com.cx.business.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cx
 * @since 2020-03-27
 */
public interface IInventoryMovementOrderService extends IService<InventoryMovementOrder> {


    List<InventoryMovementOrder> listInventoryMovementOrder(QueryWrapper queryWrapper);


    boolean save(List<SerialNumber>serialNumberList, InventoryMovementOrder inventoryMovementOrder);

}
