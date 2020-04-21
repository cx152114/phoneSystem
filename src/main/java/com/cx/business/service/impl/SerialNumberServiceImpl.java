package com.cx.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.SerialNumber;
import com.cx.business.mapper.SerialNumberMapper;
import com.cx.business.service.ISerialNumberService;
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
public class SerialNumberServiceImpl extends ServiceImpl<SerialNumberMapper, SerialNumber> implements ISerialNumberService {

    @Autowired
    private SerialNumberMapper serialNumberMapper;


    @Override
    public List<SerialNumber> listTargetPhoneInfo(Integer supplierId,Integer warehouseId) {
        return serialNumberMapper.selectTargetPhoneInfo(supplierId,warehouseId);
    }

    @Override
    public List<SerialNumber> listCanSalePhoneInfo(QueryWrapper queryWrapper) {
        return serialNumberMapper.selectCanSalePhoneInfo(queryWrapper);
    }

    @Override
    public List<SerialNumber> listSalesPhoneInfo(Integer customerId) {
        return serialNumberMapper.selectSalesPhoneInfo(customerId);
    }

    @Override
    public List<SerialNumber> listCanMovementPhoneInfo(Integer warehouseId) {
        return serialNumberMapper.selectCanMovementPhoneInfo(warehouseId);
    }
}
