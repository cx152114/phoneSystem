package com.cx.business.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.SorderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cx
 * @since 2020-03-19
 */
public interface SorderDetailMapper extends BaseMapper<SorderDetail> {

    List<SorderDetail> selectTargetStockOrderDetailById(Integer stoId);

}
