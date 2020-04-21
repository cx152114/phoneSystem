package com.cx.business.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.ProductDamageOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.business.beans.SalesBackOrder;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cx
 * @since 2020-03-27
 */
public interface ProductDamageOrderMapper extends BaseMapper<ProductDamageOrder> {

    List<ProductDamageOrder> selectProductDamageOrderList(QueryWrapper queryWrapper);

}
