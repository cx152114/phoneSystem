package com.cx.business.service;

import com.cx.business.beans.ProductDamageDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.business.beans.SalesBackDetail;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cx
 * @since 2020-03-27
 */
public interface IProductDamageDetailService extends IService<ProductDamageDetail> {


    List<ProductDamageDetail> listTargetProductDamageDetailByPdoId(Integer pdoId);
}
