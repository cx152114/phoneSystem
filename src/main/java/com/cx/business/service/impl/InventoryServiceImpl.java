package com.cx.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.Inventory;
import com.cx.business.mapper.InventoryMapper;
import com.cx.business.service.IInventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cx
 * @since 2020-03-18
 */
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements IInventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;


    @Override
    public List<Inventory> selectTargetList(QueryWrapper queryWrapper) {
        return inventoryMapper.selectTargetList(queryWrapper);
    }
}
