package com.cx.business.service.impl;

import com.cx.business.beans.PreturnDetail;
import com.cx.business.beans.SalesBackDetail;
import com.cx.business.mapper.SalesBackDetailMapper;
import com.cx.business.service.ISalesBackDetailService;
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
 * @since 2020-03-22
 */
@Service
public class SalesBackDetailServiceImpl extends ServiceImpl<SalesBackDetailMapper, SalesBackDetail> implements ISalesBackDetailService {

    @Autowired
    private SalesBackDetailMapper salesBackDetailMapper;


    @Override
    public List<SalesBackDetail> listTargetSalesBackDetailBySboId(Integer sboId) {
        return salesBackDetailMapper.selectTargetSalesBackDetailBySboId(sboId);
    }
}
