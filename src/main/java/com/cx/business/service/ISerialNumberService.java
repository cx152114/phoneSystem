package com.cx.business.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.business.beans.SerialNumber;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cx
 * @since 2020-03-19
 */
public interface ISerialNumberService extends IService<SerialNumber> {

    List<SerialNumber> listTargetPhoneInfo(Integer supplierId,Integer warehouseId);


    List<SerialNumber> listCanSalePhoneInfo(QueryWrapper queryWrapper);

    List<SerialNumber> listSalesPhoneInfo(Integer customerId);

    List<SerialNumber> listCanMovementPhoneInfo(Integer warehouseId);

}
