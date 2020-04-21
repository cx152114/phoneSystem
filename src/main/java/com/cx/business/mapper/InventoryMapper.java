package com.cx.business.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.Inventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cx
 * @since 2020-03-18
 */
public interface InventoryMapper extends BaseMapper<Inventory> {

    List<Inventory> selectTargetList(QueryWrapper queryWrapper);

}
