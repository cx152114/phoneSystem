package com.cx.business.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.PhoneInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.business.beans.SalesOrder;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cx
 * @since 2020-03-17
 */
public interface PhoneInfoMapper extends BaseMapper<PhoneInfo> {

    List<PhoneInfo> selectPhoneInfo(PhoneInfo phoneInfo);

}
