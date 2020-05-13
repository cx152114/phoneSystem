package com.cx.business.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.Inventory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.business.mapper.InventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cx
 * @since 2020-03-18
 */
public interface IInventoryService extends IService<Inventory> {

    //List<Inventory> selectTargetList(QueryWrapper<Inventory> queryWrapper);

    List<Inventory> selectTargetList(QueryWrapper<Inventory> queryWrapper);
}
