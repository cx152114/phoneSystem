package com.cx.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.vo.StaticStock;
import com.cx.business.mapper.StaticStockMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class StaticStockMapperTest {
    @Autowired
    private StaticStockMapper staticStockMapper;

    @Test
    public void test01(){

        List<StaticStock> list = staticStockMapper.selectTargetSalesList();
        //QueryWrapper queryWrapper=new QueryWrapper();
        // System.out.println(staticStockMapper.selectPage());
        System.out.println(list);
    }

    @Test
    public void test02(){
        List<StaticStock> list = staticStockMapper.selectTargetStockList();
        System.out.println(list);
    }

}
