package com.cx.business.service;

import com.cx.business.beans.PreturnDetail;
import com.cx.business.beans.SalesDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cx
 * @since 2020-03-21
 */
public interface ISalesDetailService extends IService<SalesDetail> {
    List<SalesDetail> listTargetSalesDetailByOrderId(Integer orderId);
}
