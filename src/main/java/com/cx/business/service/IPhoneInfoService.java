package com.cx.business.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.PhoneInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.business.beans.SalesOrder;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cx
 * @since 2020-03-17
 */
public interface IPhoneInfoService extends IService<PhoneInfo> {

    List<PhoneInfo> listPhoneInfo(PhoneInfo phoneInfo);

}
