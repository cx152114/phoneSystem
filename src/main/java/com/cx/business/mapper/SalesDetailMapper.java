package com.cx.business.mapper;

import com.cx.business.beans.SalesDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.business.beans.SorderDetail;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cx
 * @since 2020-03-21
 */
public interface SalesDetailMapper extends BaseMapper<SalesDetail> {

    List<SalesDetail> selectTargetSalesDetailByOrderId(Integer orderId);

}
