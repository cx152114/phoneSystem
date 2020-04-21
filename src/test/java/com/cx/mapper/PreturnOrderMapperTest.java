package com.cx.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.PreturnOrder;
import com.cx.business.mapper.PreturnOrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PreturnOrderMapperTest {

    @Autowired
    private PreturnOrderMapper preturnOrderMapper;

    @Test
    public void test(){
        QueryWrapper queryWrapper=new QueryWrapper();
        List<PreturnOrder> preturnOrderList=preturnOrderMapper.selectPreturnOrderList(queryWrapper);
        System.out.println(preturnOrderList);
    }
}
