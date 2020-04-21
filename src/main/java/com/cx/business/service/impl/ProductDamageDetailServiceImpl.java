package com.cx.business.service.impl;

import com.cx.business.beans.ProductDamageDetail;
import com.cx.business.mapper.ProductDamageDetailMapper;
import com.cx.business.service.IProductDamageDetailService;
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
 * @since 2020-03-27
 */
@Service
public class ProductDamageDetailServiceImpl extends ServiceImpl<ProductDamageDetailMapper, ProductDamageDetail> implements IProductDamageDetailService {

    @Autowired
    private ProductDamageDetailMapper productDamageDetailMapper;


    @Override
    public List<ProductDamageDetail> listTargetProductDamageDetailByPdoId(Integer pdoId) {
        return productDamageDetailMapper.selectTargetProductDamageDetailByPdoId(pdoId);
    }
}
