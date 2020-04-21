package com.cx.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.sys.beans.Menu;
import com.cx.sys.beans.User;
import com.cx.sys.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test02(){
        List<Menu> list = userMapper.selectMenuList(1);
        System.out.println(list);
    }

    @Test
    public void test01(){
        // 3.根据用户名去DB查询对应的用户信息
//        QueryWrapper<User> param = new QueryWrapper<>();
//        param.eq("username","jack");
//        User user = userMapper.selectOne(param);


        User user1 = new User();
        user1.setUsername("aaaaaaaaaaa");
        user1.setPassword("22222222222222");
        user1.setSalt("11111111111111111111");
        user1.setCreateTime(new Date());

        userMapper.insert(user1);
    }

}