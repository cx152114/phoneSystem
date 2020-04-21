package com.cx.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.SerialNumber;
import com.cx.business.beans.SorderDetail;
import com.cx.business.mapper.SerialNumberMapper;
import com.cx.business.mapper.SorderDetailMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SerialNumberMapperTest {

    @Autowired
    private SerialNumberMapper serialNumberMapper;

    @Autowired
    private SorderDetailMapper sorderDetailMapper;

    @Test
    public void test01(){

//        queryWrapper.eq("warehouse_id",1);
        List<SerialNumber> list=serialNumberMapper.selectTargetPhoneInfo(1,1);
        System.out.println(list);
    }


    @Test
    public void test02(){
        QueryWrapper queryWrapper=new QueryWrapper();
        System.out.println(sorderDetailMapper.selectList(queryWrapper));
        //sorderDetailMapper.s
    }


    @Test
    public void test03(){
        QueryWrapper queryWrapper=new QueryWrapper();
        System.out.println(serialNumberMapper.selectCanSalePhoneInfo(queryWrapper));
        //sorderDetailMapper.s
    }



}
