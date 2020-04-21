package com.cx.business.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.SorderDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cx
 * @since 2020-03-19
 */
public interface ISorderDetailService extends IService<SorderDetail> {

    List<SorderDetail> listTargetStockOrderDetailByStoId(Integer stoId);

}
