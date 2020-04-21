package com.cx.business.mapper;

import com.cx.business.beans.ProductDamageDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.business.beans.SalesBackDetail;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cx
 * @since 2020-03-27
 */
public interface ProductDamageDetailMapper extends BaseMapper<ProductDamageDetail> {
    List<ProductDamageDetail> selectTargetProductDamageDetailByPdoId(Integer pdoId);
}
