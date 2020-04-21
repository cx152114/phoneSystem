package com.cx.business.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.PreturnOrder;
import com.cx.business.beans.SalesBackOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cx
 * @since 2020-03-21
 */
public interface SalesBackOrderMapper extends BaseMapper<SalesBackOrder> {

    List<SalesBackOrder> selectSalesBackOrderList(QueryWrapper queryWrapper);

}
