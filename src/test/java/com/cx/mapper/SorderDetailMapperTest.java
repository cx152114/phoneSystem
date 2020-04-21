package com.cx.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.Inventory;
import com.cx.business.beans.SorderDetail;
import com.cx.business.mapper.SorderDetailMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SorderDetailMapperTest {

    @Autowired
    private SorderDetailMapper sorderDetailMapper;

    @Test
    public void test02(){
        List<SorderDetail> list = sorderDetailMapper.selectTargetStockOrderDetailById(1);
        System.out.println(list);
    }
}
