package com.cx.business.service.impl;

import com.cx.business.beans.PreturnDetail;
import com.cx.business.beans.SorderDetail;
import com.cx.business.mapper.PreturnDetailMapper;
import com.cx.business.service.IPreturnDetailService;
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
 * @since 2020-03-20
 */
@Service
public class PreturnDetailServiceImpl extends ServiceImpl<PreturnDetailMapper, PreturnDetail> implements IPreturnDetailService {


    @Autowired
    private PreturnDetailMapper preturnDetailMapper;

    @Override
    public List<PreturnDetail> listTargetPreturnDetailByProId(Integer proId) {
        return preturnDetailMapper.selectTargetPreturnDetailById(proId);
    }
}
