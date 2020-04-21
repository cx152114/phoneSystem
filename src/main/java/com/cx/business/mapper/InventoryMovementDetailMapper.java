package com.cx.business.mapper;

import com.cx.business.beans.InventoryMovementDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.business.beans.SalesDetail;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cx
 * @since 2020-03-27
 */
public interface InventoryMovementDetailMapper extends BaseMapper<InventoryMovementDetail> {

    List<InventoryMovementDetail> selectTargetInventoryMovementDetailByBimOrderId(Integer bimOrderId);
}
