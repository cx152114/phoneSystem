package com.cx.service.impl;

import com.cx.business.beans.SerialNumber;
import com.cx.business.service.IPreturnOrderService;
import com.cx.business.service.ISerialNumberService;
import com.cx.business.service.impl.PreturnOrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class preturnOrderServiceImplTest {

    @Autowired
    private IPreturnOrderService preturnOrderService;

    @Autowired
    private ISerialNumberService serialNumberService;

    @Test
    public void test01(){
        SerialNumber serialNumber=serialNumberService.getById(9);

        List<SerialNumber> list=new ArrayList<>();
        list.add(serialNumber);











    }

}
