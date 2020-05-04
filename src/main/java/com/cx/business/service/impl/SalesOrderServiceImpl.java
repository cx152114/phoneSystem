package com.cx.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.*;
import com.cx.business.mapper.CustomerMapper;
import com.cx.business.mapper.SalesOrderMapper;
import com.cx.business.service.ISalesDetailService;
import com.cx.business.service.ISalesOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.business.service.ISerialNumberService;
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
 * @since 2020-03-21
 */
@Service
public class SalesOrderServiceImpl extends ServiceImpl<SalesOrderMapper, SalesOrder> implements ISalesOrderService {

    @Autowired
    private SalesOrderMapper salesOrderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CustomerMapper customerMapper;


    @Autowired
    private ISerialNumberService serialNumberService;

    @Autowired
    private ISalesDetailService salesDetailService;


    @Override
    public List<SalesOrder> list(Wrapper<SalesOrder> queryWrapper) {
        List<SalesOrder> list=salesOrderMapper.selectList(queryWrapper);
        if (list.size()>0){
            for (SalesOrder salesOrder :list) {
                User user=userMapper.selectById(salesOrder.getUserId());
                Customer customer=customerMapper.selectById(salesOrder.getCustomerId());
                salesOrder.setUser(user);
               salesOrder.setCustomer(customer);
            }
        }
        return list;
    }

    @Override
    public List<SalesOrder> listSalesOrderInfo(QueryWrapper queryWrapper) {
        return salesOrderMapper.selectSalesOrderList(queryWrapper);
    }

    @Override
    public int save(List<SerialNumber> serialNumberList, SalesOrder salesOrder) {
        //对销售后的商品序列号进行改变
        serialNumberService.updateBatchById(serialNumberList);

        salesOrderMapper.insert(salesOrder);

        List<SalesDetail> salesDetailList=salesOrder.getSalesDetailList();

        for (int i = 0; i < salesDetailList.size(); i++) {
            salesDetailList.get(i).setOrderId(salesOrder.getOrderId());
        }

        salesDetailService.saveBatch(salesDetailList);
        return 0;
    }
}
