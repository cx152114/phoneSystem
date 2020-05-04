package com.cx.business.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.business.beans.*;
import com.cx.business.mapper.ProductDamageOrderMapper;
import com.cx.business.mapper.WarehouseMapper;
import com.cx.business.service.IInventoryService;
import com.cx.business.service.IProductDamageDetailService;
import com.cx.business.service.IProductDamageOrderService;
import com.cx.business.service.ISerialNumberService;
import com.cx.common.exception.BizException;
import com.cx.sys.beans.User;
import com.cx.sys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ProductDamageOrderServiceImpl extends ServiceImpl<ProductDamageOrderMapper, ProductDamageOrder> implements IProductDamageOrderService {

    @Autowired
    private ProductDamageOrderMapper productDamageOrderMapper;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private ISerialNumberService serialNumberService;

    @Autowired
    private IProductDamageDetailService productDamageDetailService;

    @Autowired
    private IInventoryService inventoryService;

    @Override
    public List<ProductDamageOrder> list(Wrapper<ProductDamageOrder> queryWrapper) {
        List<ProductDamageOrder> list=productDamageOrderMapper.selectList(queryWrapper);
        if (list.size()>0){
            for (ProductDamageOrder productDamageOrder :list) {
                User user=userMapper.selectById(productDamageOrder.getUserId());
                Warehouse warehouse=warehouseMapper.selectById(productDamageOrder.getWarehouseId());
                productDamageOrder.setUser(user);
                productDamageOrder.setWarehouse(warehouse);
            }
        }
        return list;
    }

    @Override
    public List<ProductDamageOrder> listProductDamageOrder(QueryWrapper queryWrapper) {
        List<ProductDamageOrder> list=productDamageOrderMapper.selectList(queryWrapper);
        return productDamageOrderMapper.selectProductDamageOrderList(queryWrapper);
    }

    @Override
    public int save(List<SerialNumber> serialNumberList, ProductDamageOrder productDamageOrder) {
        serialNumberService.updateBatchById(serialNumberList);
        productDamageOrderMapper.insert(productDamageOrder);

        List<ProductDamageDetail> list=productDamageOrder.getProductDamageDetails();

        for(ProductDamageDetail productDamageDetail:list){
            productDamageDetail.setPdoId(productDamageOrder.getPdoId());
        }
        productDamageDetailService.saveBatch(list);


        for (ProductDamageDetail productDamageDetail:list){
            QueryWrapper<Inventory> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("phone_id",productDamageDetail.getPhoneId());
            queryWrapper.eq("warehouse_id",productDamageOrder.getWarehouseId());
            Inventory inventory=inventoryService.getOne(queryWrapper);
            Integer number=inventory.getProductNumber()-1;
            if (number!=null){
                inventory.setProductNumber(number);
                inventoryService.updateById(inventory);
            }else {
                throw new BizException("未知异常，请联系管理员");
            }
        }
        return 0;
    }


}
