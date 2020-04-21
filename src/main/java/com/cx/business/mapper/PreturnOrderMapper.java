package com.cx.business.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.PreturnOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cx
 * @since 2020-03-20
 */
public interface PreturnOrderMapper extends BaseMapper<PreturnOrder> {


    List<PreturnOrder> selectPreturnOrderList(QueryWrapper queryWrapper);

}
