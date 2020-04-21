package com.cx.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.PhoneInfo;
import com.cx.business.mapper.PhoneInfoMapper;
import com.cx.business.service.IPhoneInfoService;
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
 * @since 2020-03-17
 */
@Service
public class PhoneInfoServiceImpl extends ServiceImpl<PhoneInfoMapper, PhoneInfo> implements IPhoneInfoService {

    @Autowired
    private PhoneInfoMapper phoneInfoMapper;


    @Override
    public List<PhoneInfo> listPhoneInfo(PhoneInfo phoneInfo) {
        return phoneInfoMapper.selectPhoneInfo(phoneInfo);
    }
}
