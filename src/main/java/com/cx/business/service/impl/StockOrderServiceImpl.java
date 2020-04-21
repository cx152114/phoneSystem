package com.cx.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.Inventory;
import com.cx.business.beans.SerialNumber;
import com.cx.business.beans.SorderDetail;
import com.cx.business.beans.StockOrder;
import com.cx.business.mapper.StockOrderMapper;
import com.cx.business.service.IInventoryService;
import com.cx.business.service.ISerialNumberService;
import com.cx.business.service.ISorderDetailService;
import com.cx.business.service.IStockOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cx
 * @since 2020-03-18
 */
@Service
public class StockOrderServiceImpl extends ServiceImpl<StockOrderMapper, StockOrder> implements IStockOrderService {

    @Autowired
    private StockOrderMapper stockOrderMapper;

    @Autowired
    private ISerialNumberService serialNumberService;

    @Autowired
    private ISorderDetailService sorderDetailService;

    @Autowired
    private IInventoryService inventoryService;


    @Override
    public List<StockOrder> listStockOrderInfo(QueryWrapper queryWrapper) {
        return stockOrderMapper.selectStockOrderList(queryWrapper);
    }

    @Override
    @Transactional
    public int save(List<SerialNumber> serialNumberList, StockOrder stockOrder) {
        //批量插入新增的串号
        serialNumberService.saveBatch(serialNumberList);
        //获得相应的订单信息记录
        List<SorderDetail> sorderDetailList=stockOrder.getSorderDetails();
        //插入新增的采购订单
        stockOrderMapper.insert(stockOrder);
        //设置新增的采购订单详情的主订单号
        for (int i = 0; i < sorderDetailList.size(); i++) {
            sorderDetailList.get(i).setId(serialNumberList.get(i).getId());
            sorderDetailList.get(i).setStoId(stockOrder.getStoId());
        }
        //批量插入相应的订单详情
        sorderDetailService.saveBatch(sorderDetailList);

        //实现库存数目变更
        //1、记录相应的商品数目
        HashMap<Integer ,Integer> map=new HashMap<>();
        for (int i = 0; i < sorderDetailList.size(); i++) {
            Integer phoneId=sorderDetailList.get(i).getPhoneId();
            int count=0;
            for (int j = 0; j < sorderDetailList.size(); j++) {
                if (phoneId==sorderDetailList.get(j).getPhoneId()){
                    count++;
                }
            }
            map.put(phoneId,count);
        }
        //2、取出key进行对比，判断是否存在指定商品的库存记录，
        for (Integer key : map.keySet()) {
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("phone_id",key);
            queryWrapper.eq("warehouse_id",stockOrder.getWarehouseId());
            //List<Inventory> inventories=  inventoryService.list(queryWrapper);
            Inventory inventory=inventoryService.getOne(queryWrapper);
            if (inventory==null){
                // 2.1假如不存在相应的库存记录，直接进行插入
                Inventory inventory1=new Inventory();
                inventory1.setPhoneId(key);
                inventory1.setWarehouseId(stockOrder.getWarehouseId());
                inventory1.setProductNumber(map.get(key));
                inventoryService.save(inventory1);
            }else {
                //2.2存在相应的库存记录，进行库存变更
               int  productNumber=inventory.getProductNumber()+map.get(key);
               inventory.setProductNumber(productNumber);
               inventoryService.updateById(inventory);
            }
        }
        //完成
        return 0;
    }


}
