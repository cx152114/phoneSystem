package com.cx.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.*;
import com.cx.business.mapper.PreturnOrderMapper;
import com.cx.business.mapper.SerialNumberMapper;
import com.cx.business.service.IInventoryService;
import com.cx.business.service.IPreturnDetailService;
import com.cx.business.service.IPreturnOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.business.service.ISerialNumberService;
import com.cx.common.exception.BizException;
import com.cx.sys.beans.User;
import com.cx.sys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cx
 * @since 2020-03-20
 */
@Service
public class PreturnOrderServiceImpl extends ServiceImpl<PreturnOrderMapper, PreturnOrder> implements IPreturnOrderService {

    @Autowired
    private PreturnOrderMapper preturnOrderMapper;

    @Autowired
    private SerialNumberMapper serialNumberMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IPreturnDetailService preturnDetailService;

    @Autowired
    private IInventoryService inventoryService;

    @Override
    public List<PreturnOrder> list(Wrapper<PreturnOrder> queryWrapper) {
        List<PreturnOrder> list=preturnOrderMapper.selectList(queryWrapper);
        if (list.size()>0){
            for (PreturnOrder preturnOrder :list) {
                User user=userMapper.selectById(preturnOrder.getUserId());
                preturnOrder.setUser(user);
            }
        }
        return super.list(queryWrapper);
    }

    @Override
    public List<PreturnOrder> listPreturnOrderInfo(QueryWrapper queryWrapper) {
        return preturnOrderMapper.selectPreturnOrderList(queryWrapper);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor= Exception.class)
    public int save(List<SerialNumber> serialNumberList, PreturnOrder preturnOrder) {
        //修改相应串号状态
        for (SerialNumber serialNumber:serialNumberList) {
            serialNumberMapper.updateById(serialNumber);
        }
        //获取相应的退货详情
        List<PreturnDetail> list=preturnOrder.getPreturnDetails();
        //插入退货表
        preturnOrderMapper.insert(preturnOrder);

        //绑定退货详情信息的母单号
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setProId(preturnOrder.getProId());
            list.get(i).setId(serialNumberList.get(i).getId());
        }
        //批量插入退货详情信息
        preturnDetailService.saveBatch(list);


        //实现库存数目变更
        //1、记录相应的商品数目
        HashMap<Integer ,Integer> map=new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            Integer phoneId=list.get(i).getPhoneId();
            int count=0;
            for (int j = 0; j < list.size(); j++) {
                if (phoneId==list.get(j).getPhoneId()){
                    count++;
                }
            }
            map.put(phoneId,count);
        }
        //2、取出key进行对比，判断是否存在指定商品的库存记录，
        for (Integer key : map.keySet()) {
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("phone_id",key);
            queryWrapper.eq("warehouse_id",preturnOrder.getWarehouseId());
            //List<Inventory> inventories=  inventoryService.list(queryWrapper);
            Inventory inventory=inventoryService.getOne(queryWrapper);
            if (inventory==null){
                // 2.1假如不存在相应的库存记录，直接进行插入
                throw  new BizException("商品存在调拨状况");
            }else {
                //2.2存在相应的库存记录，进行库存变更
                int  productNumber=inventory.getProductNumber()-map.get(key);
                inventory.setProductNumber(productNumber);
                inventoryService.updateById(inventory);
            }
        }
        //完成
        return 0;
    }
}
