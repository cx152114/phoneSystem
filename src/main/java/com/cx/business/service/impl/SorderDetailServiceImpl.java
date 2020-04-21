package com.cx.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.SorderDetail;
import com.cx.business.mapper.SorderDetailMapper;
import com.cx.business.service.ISorderDetailService;
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
 * @since 2020-03-19
 */
@Service
public class SorderDetailServiceImpl extends ServiceImpl<SorderDetailMapper, SorderDetail> implements ISorderDetailService {

    @Autowired
    private SorderDetailMapper sorderDetailMapper;


    @Override
    public List<SorderDetail> listTargetStockOrderDetailByStoId(Integer stoId) {
        return sorderDetailMapper.selectTargetStockOrderDetailById(stoId);
    }
}
