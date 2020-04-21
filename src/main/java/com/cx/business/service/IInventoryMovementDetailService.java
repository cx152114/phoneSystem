package com.cx.business.service;

import com.cx.business.beans.InventoryMovementDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.business.beans.SalesDetail;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cx
 * @since 2020-03-27
 */
public interface IInventoryMovementDetailService extends IService<InventoryMovementDetail> {

    List<InventoryMovementDetail> listTargetInventoryMovementDetailByBimOrderId(Integer bimOrderId);
}
