package com.cx.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.*;
import com.cx.business.mapper.InventoryMovementDetailMapper;
import com.cx.business.mapper.InventoryMovementOrderMapper;
import com.cx.business.mapper.WarehouseMapper;
import com.cx.business.service.IInventoryMovementDetailService;
import com.cx.business.service.IInventoryMovementOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.business.service.IInventoryService;
import com.cx.business.service.ISerialNumberService;
import com.cx.sys.beans.User;
import com.cx.sys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cx
 * @since 2020-03-27
 */
@Service
public class InventoryMovementOrderServiceImpl extends ServiceImpl<InventoryMovementOrderMapper, InventoryMovementOrder> implements IInventoryMovementOrderService {

    @Autowired
    private InventoryMovementOrderMapper inventoryMovementOrderMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IInventoryMovementDetailService inventoryMovementDetailService;


    @Autowired
    private ISerialNumberService serialNumberService;


    @Autowired
    private IInventoryService inventoryService;

    @Override
    public List<InventoryMovementOrder> list(Wrapper<InventoryMovementOrder> queryWrapper) {
        List<InventoryMovementOrder> list=inventoryMovementOrderMapper.selectList(queryWrapper);
        if (list.size()>0){
            for (InventoryMovementOrder inventoryMovementOrder :list) {
                User user=userMapper.selectById(inventoryMovementOrder.getUserId());
                Warehouse warehouse=warehouseMapper.selectById(inventoryMovementOrder.getWarehouseInid());
                Warehouse warehouse1=warehouseMapper.selectById(inventoryMovementOrder.getWarehouseOutid());
                inventoryMovementOrder.setUser(user);
                inventoryMovementOrder.setInWarehouse(warehouse);
                inventoryMovementOrder.setOutWarehouse(warehouse1);
                //preturnOrder.setUser(user);
            }
        }
        return list;
    }

    @Override
    public List<InventoryMovementOrder> listInventoryMovementOrder(QueryWrapper queryWrapper) {
        return inventoryMovementOrderMapper.selectInventoryMovementOrderList(queryWrapper);
    }

    @Override
    public boolean save(List<SerialNumber> serialNumberList, InventoryMovementOrder inventoryMovementOrder) {
        List<SerialNumber> serialNumbers=new ArrayList<>();
        for (SerialNumber serialNumber:serialNumberList){
            SerialNumber serialNumber1=serialNumberService.getById(serialNumber.getId());
            serialNumber1.setWarehouseId(serialNumber.getWarehouseId());
            serialNumbers.add(serialNumber1);
        }
        serialNumberService.updateBatchById(serialNumbers);

        inventoryMovementOrderMapper.insert(inventoryMovementOrder);

        List<InventoryMovementDetail> list=inventoryMovementOrder.getInventoryMovementDetails();
        for (InventoryMovementDetail inventoryMovementDetail:list) {
            inventoryMovementDetail.setBimOrderId(inventoryMovementOrder.getBimorderId());
        }

        inventoryMovementDetailService.saveBatch(list);


        //库存变更
        //查询出相应的数据
        for (InventoryMovementDetail inventoryMovementDetail:list){
            QueryWrapper<Inventory> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("phone_id",inventoryMovementDetail.getPhoneId());
            queryWrapper.eq("warehouse_id",inventoryMovementOrder.getWarehouseInid());
            Inventory inventory=inventoryService.getOne(queryWrapper);

            QueryWrapper<Inventory> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("phone_id",inventoryMovementDetail.getPhoneId());
            queryWrapper1.eq("warehouse_id",inventoryMovementOrder.getWarehouseOutid());
            Inventory inventory1=inventoryService.getOne(queryWrapper1);
            inventory1.setProductNumber(inventory1.getProductNumber()-1);
            //判断该仓库中是否存在该商品
            if (inventory==null){
                //不存在，插入新的库存单
                Inventory inventory2=new Inventory();
                inventory2.setPhoneId(inventoryMovementDetail.getPhoneId());
                inventory2.setWarehouseId(inventoryMovementOrder.getWarehouseInid());
                inventory2.setProductNumber(1);


                inventoryService.save(inventory2);
                inventoryService.updateById(inventory1);
            }else {
                //存在，直接在原来的基础上进行变更
                inventory.setProductNumber(inventory.getProductNumber()+1);
                inventoryService.updateById(inventory);
                inventoryService.updateById(inventory1);
            }
        }








        return true;
    }

}
