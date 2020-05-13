package com.cx.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.Inventory;
import com.cx.business.beans.PhoneInfo;
import com.cx.business.beans.PreturnOrder;
import com.cx.business.beans.Warehouse;
import com.cx.business.mapper.InventoryMapper;
import com.cx.business.mapper.PhoneInfoMapper;
import com.cx.business.mapper.WarehouseMapper;
import com.cx.business.service.IInventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.sys.beans.User;
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

    @Autowired
    private PhoneInfoMapper phoneInfoMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;


    @Override
    public List<Inventory> list(Wrapper<Inventory> queryWrapper) {
        List<Inventory> list=inventoryMapper.selectList(queryWrapper);
        if (list.size()>0){
            for (Inventory inventory :list) {
                PhoneInfo phoneInfo=phoneInfoMapper.selectById(inventory.getPhoneId());
                Warehouse warehouse=warehouseMapper.selectById(inventory.getWarehouseId());
                inventory.setPhoneInfo(phoneInfo);
                inventory.setWarehouse(warehouse);
            }
        }
        return list;
    }

    @Override
    public List<Inventory> selectTargetList(QueryWrapper<Inventory> queryWrapper) {
        return inventoryMapper.selectTargetList(queryWrapper);
    }
}
