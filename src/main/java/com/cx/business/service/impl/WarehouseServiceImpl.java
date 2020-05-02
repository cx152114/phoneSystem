package com.cx.business.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cx.business.beans.Warehouse;
import com.cx.business.mapper.WarehouseMapper;
import com.cx.business.service.IWarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.sys.beans.User;
import com.cx.sys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cx
 * @since 2020-03-18
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements IWarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Warehouse> list() {
        List<Warehouse> list=warehouseMapper.selectList(Wrappers.emptyWrapper());
        if (list.size()>0){
            for (Warehouse warehouse :list) {
                User user=userMapper.selectById(warehouse.getUserId());
                warehouse.setUser(user);
            }
        }
        return list;
    }
}
