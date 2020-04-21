package com.cx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.ProductDamageOrder;
import com.cx.business.beans.SerialNumber;
import com.cx.business.service.IProductDamageOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ProductDamageOrderServiceImplTest {

    @Autowired
    private IProductDamageOrderService productDamageOrderService;

    @Test
    public void test01(){
        QueryWrapper queryWrapper=new QueryWrapper();
        List<ProductDamageOrder> list=productDamageOrderService.listProductDamageOrder(queryWrapper);
        System.out.println(list);
    }
}
