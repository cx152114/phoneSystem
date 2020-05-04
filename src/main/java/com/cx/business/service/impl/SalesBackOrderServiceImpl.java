package com.cx.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.*;
import com.cx.business.mapper.CustomerMapper;
import com.cx.business.mapper.SalesBackOrderMapper;
import com.cx.business.service.ISalesBackDetailService;
import com.cx.business.service.ISalesBackOrderService;
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
public class SalesBackOrderServiceImpl extends ServiceImpl<SalesBackOrderMapper, SalesBackOrder> implements ISalesBackOrderService {

    @Autowired
    private SalesBackOrderMapper salesBackOrderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ISerialNumberService serialNumberService;

    @Autowired
    private ISalesBackDetailService salesBackDetailService;


    @Override
    public List<SalesBackOrder> list(Wrapper<SalesBackOrder> queryWrapper) {
        List<SalesBackOrder> list=salesBackOrderMapper.selectList(queryWrapper);
        if (list.size()>0){
            for (SalesBackOrder salesBackOrder :list) {
                User user=userMapper.selectById(salesBackOrder.getUserId());
                Customer customer=customerMapper.selectById(salesBackOrder.getCustomerId());
                salesBackOrder.setUser(user);
                salesBackOrder.setCustomer(customer);
            }
        }
        return list;
    }

    @Override
    public List<SalesBackOrder> listSalesBackOrderInfo(QueryWrapper queryWrapper) {
        return salesBackOrderMapper.selectSalesBackOrderList(queryWrapper);
    }

    @Override
    public int save(List<SerialNumber> serialNumberList, SalesBackOrder salesBackOrder) {
        serialNumberService.updateBatchById(serialNumberList);

        salesBackOrderMapper.insert(salesBackOrder);

        List<SalesBackDetail> list=salesBackOrder.getSalesBackDetailList();

        for(SalesBackDetail salesBackDetail:list){
            salesBackDetail.setSboId(salesBackOrder.getSboId());
        }

        salesBackDetailService.saveBatch(list);

        return 0;
    }
}
