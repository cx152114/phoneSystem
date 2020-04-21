package com.cx.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.Inventory;
import com.cx.business.mapper.InventoryMapper;
import com.cx.sys.beans.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class InventoryMapperTest {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Test
    public void test02(){
        QueryWrapper queryWrapper=new QueryWrapper();
        List<Inventory> list = inventoryMapper.selectTargetList(queryWrapper);
        System.out.println(list);
    }
}
