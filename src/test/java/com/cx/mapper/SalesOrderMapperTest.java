package com.cx.mapper;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.SalesOrder;
import com.cx.business.mapper.SalesOrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SalesOrderMapperTest {

    @Autowired
    private SalesOrderMapper salesOrderMapper;

    @Test
    public  void test01(){
        QueryWrapper queryWrapper=new QueryWrapper();
        List<SalesOrder> list=salesOrderMapper.selectSalesOrderList(queryWrapper);
        System.out.println(list);

    }
}
