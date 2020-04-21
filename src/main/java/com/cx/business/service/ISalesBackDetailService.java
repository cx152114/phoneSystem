package com.cx.business.service;

import com.cx.business.beans.PreturnDetail;
import com.cx.business.beans.SalesBackDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cx
 * @since 2020-03-22
 */
public interface ISalesBackDetailService extends IService<SalesBackDetail> {

    List<SalesBackDetail> listTargetSalesBackDetailBySboId(Integer sboId);

}
