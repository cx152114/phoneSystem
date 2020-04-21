package com.cx.business.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.InventoryMovementOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.business.beans.SalesOrder;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cx
 * @since 2020-03-27
 */
public interface InventoryMovementOrderMapper extends BaseMapper<InventoryMovementOrder> {

    List<InventoryMovementOrder> selectInventoryMovementOrderList(QueryWrapper queryWrapper);
}
