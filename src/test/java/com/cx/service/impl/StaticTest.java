package com.cx.service.impl;

import com.cx.business.beans.vo.StaticStock;
import com.cx.business.service.IStaticStockService;
import com.cx.common.model.TreeNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class StaticTest {

    @Autowired
    private IStaticStockService iStaticStockService;

    @Test
    public void test01() {
       List<StaticStock> list=iStaticStockService.listTargetSalesList();
        System.out.println(list);
    }

    @Test
    public void test02() {
        List<StaticStock> list=iStaticStockService.listTargetStaticStock();
        System.out.println(list);
    }
}
