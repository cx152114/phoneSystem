package com.cx.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.SalesDetail;
import com.cx.business.beans.SalesOrder;
import com.cx.business.beans.SerialNumber;
import com.cx.business.mapper.SalesOrderMapper;
import com.cx.business.service.ISalesDetailService;
import com.cx.business.service.ISalesOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.business.service.ISerialNumberService;
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
    private ISerialNumberService serialNumberService;

    @Autowired
    private ISalesDetailService salesDetailService;

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
