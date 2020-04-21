package com.cx.business.service.impl;

import com.cx.business.beans.InventoryMovementDetail;
import com.cx.business.mapper.InventoryMovementDetailMapper;
import com.cx.business.service.IInventoryMovementDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cx
 * @since 2020-03-27
 */
@Service
public class InventoryMovementDetailServiceImpl extends ServiceImpl<InventoryMovementDetailMapper, InventoryMovementDetail> implements IInventoryMovementDetailService {

    @Autowired
    private InventoryMovementDetailMapper inventoryMovementDetailMapper;


    @Override
    public List<InventoryMovementDetail> listTargetInventoryMovementDetailByBimOrderId(Integer bimOrderId) {
        return inventoryMovementDetailMapper.selectTargetInventoryMovementDetailByBimOrderId(bimOrderId);
    }
}
