package com.cx.business.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.SalesOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.business.beans.StockOrder;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cx
 * @since 2020-03-21
 */
public interface SalesOrderMapper extends BaseMapper<SalesOrder> {

    List<SalesOrder> selectSalesOrderList(QueryWrapper queryWrapper);

}
