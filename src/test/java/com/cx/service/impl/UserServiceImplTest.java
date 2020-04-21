package com.cx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.common.model.TreeNode;
import com.cx.sys.beans.User;
import com.cx.sys.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceImplTest {

    @Autowired
    private IUserService userService;


    @Test
    public void test02() {
        List<TreeNode> list = userService.getMenuTreeByUserId(1);
        System.out.println(list);

    }

    @Test
    public void test01() {
        IPage<User> pageInfo = new Page<>();
        pageInfo.setCurrent(1);
        pageInfo.setSize(5);

        pageInfo = userService.page(pageInfo);

    }
}