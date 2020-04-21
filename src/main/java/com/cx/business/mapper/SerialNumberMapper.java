package com.cx.business.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.business.beans.SerialNumber;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cx
 * @since 2020-03-19
 */
public interface SerialNumberMapper extends BaseMapper<SerialNumber> {

    List<SerialNumber> selectTargetPhoneInfo(@Param("supplierId") Integer supplierId,@Param("warehouseId") Integer warehouseId);


    List<SerialNumber> selectCanSalePhoneInfo(QueryWrapper queryWrapper);


    List<SerialNumber> selectSalesPhoneInfo(Integer customerId);


    List<SerialNumber> selectCanMovementPhoneInfo(Integer warehouseId);

}
