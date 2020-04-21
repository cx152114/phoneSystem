package com.cx.mapper;

import com.cx.business.beans.InventoryMovementDetail;
import com.cx.business.mapper.InventoryMovementDetailMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class InventoryMovementDetailMapperTest {

    @Autowired
    private InventoryMovementDetailMapper inventoryMovementDetailMapper;

    @Test
    public void test01(){
        List<InventoryMovementDetail> list=inventoryMovementDetailMapper.selectTargetInventoryMovementDetailByBimOrderId(1);
        System.out.println(list);
    }
}
