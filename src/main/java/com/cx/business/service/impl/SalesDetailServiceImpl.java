package com.cx.business.service.impl;

import com.cx.business.beans.PreturnDetail;
import com.cx.business.beans.SalesDetail;
import com.cx.business.mapper.SalesDetailMapper;
import com.cx.business.service.ISalesDetailService;
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
 * @since 2020-03-21
 */
@Service
public class SalesDetailServiceImpl extends ServiceImpl<SalesDetailMapper, SalesDetail> implements ISalesDetailService {

    @Autowired
    private SalesDetailMapper salesDetailMapper;


    @Override
    public List<SalesDetail> listTargetSalesDetailByOrderId(Integer orderId) {
        return salesDetailMapper.selectTargetSalesDetailByOrderId(orderId);
    }
}
