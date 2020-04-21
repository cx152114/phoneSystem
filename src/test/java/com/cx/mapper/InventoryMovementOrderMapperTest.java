package com.cx.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.InventoryMovementOrder;
import com.cx.business.mapper.InventoryMovementOrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class InventoryMovementOrderMapperTest {

    @Autowired
    private InventoryMovementOrderMapper inventoryMovementOrderMapper;

    @Test
    public void test01(){
        QueryWrapper queryWrapper=new QueryWrapper();
        List<InventoryMovementOrder> inventoryMovementOrders=inventoryMovementOrderMapper.selectInventoryMovementOrderList(queryWrapper);
        System.out.println(inventoryMovementOrders);
    }
}
