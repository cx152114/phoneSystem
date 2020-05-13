package com.cx.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.*;
import com.cx.business.service.IInventoryMovementDetailService;
import com.cx.business.service.IInventoryMovementOrderService;
import com.cx.common.model.R;
import com.cx.sys.beans.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cx
 * @since 2020-03-27
 */
@Controller
@RequestMapping("/business/inventoryMovementOrder")
public class InventoryMovementOrderController {

    @Autowired
    private IInventoryMovementOrderService inventoryMovementOrderService;

    @Autowired
    private IInventoryMovementDetailService inventoryMovementDetailService;

    /**
     * 跳转到历史库存调拨单管理页面
     * @return
     */
    @GetMapping
    @RequiresPermissions("business:inventoryMovementOrder:search")
    private String inventoryMovementManagement(){
        return "/business/InventoryMovementOrder_list";
    }


    @RequestMapping(value = "/findAllInventoryMovementOrder",method = RequestMethod.POST)
    @RequiresPermissions("business:inventoryMovementOrder:search")
    @ResponseBody
    public R findAllInventoryMovementOrder(InventoryMovementOrder order,Integer minNumber,Integer maxNumber,String startTime,String endTime){
        QueryWrapper<InventoryMovementOrder> queryWrapper=new QueryWrapper<InventoryMovementOrder>();
        if (null!=order.getBimorderId()){
            queryWrapper.eq("bimorder_id",order.getBimorderId());
        }
        if (null!=order.getWarehouseOutid()){
            queryWrapper.eq("warehouse_outid",order.getWarehouseOutid());
        }
        if (null!=order.getWarehouseInid()){
            queryWrapper.eq("warehouse_inid",order.getWarehouseInid());
        }
        if (null!=order.getUserId()){
            queryWrapper.eq("user_id",order.getUserId());
        }
        if (null!=minNumber){
            queryWrapper.ge("bimo_number",minNumber);
        }
        if (null!=maxNumber){
            queryWrapper.le("bimo_number",maxNumber);
        }
        if (!StringUtils.isEmpty(startTime)){
            queryWrapper.ge("movement_time",startTime);
        }
        if (!StringUtils.isEmpty(endTime)){
            queryWrapper.le("movement_time",endTime);
        }
        List<InventoryMovementOrder> inventoryMovementOrders=inventoryMovementOrderService.list(queryWrapper);
        //List<InventoryMovementOrder> inventoryMovementOrders=inventoryMovementOrderService.listInventoryMovementOrder(queryWrapper);
        return R.ok().put("rows",inventoryMovementOrders);
    }


    /**
     * 获得指定订单的订单详情
     * @param bimOrderId
     * @return
     */
    @RequestMapping(value = "/getInventoryMovementOrderByBimOrderId",method = RequestMethod.POST)
    @RequiresPermissions("business:inventoryMovementOrder:search")
    @ResponseBody
    public R getInventoryMovementOrderByBimOrderId(Integer bimOrderId){
       // System.out.println(bimOrderId);
        List<InventoryMovementDetail> inventoryMovementDetails=inventoryMovementDetailService.listTargetInventoryMovementDetailByBimOrderId(bimOrderId);
        //System.out.println(inventoryMovementDetails);
        return R.ok().put("inventoryMovementDetails",inventoryMovementDetails);
    }

    /**
     * 跳转到库存调拨页面
     * @return
     */
    @GetMapping("/inventoryMovement")
    @RequiresPermissions("business:inventoryMovement:menu")
    private String stockProductBack(){
        return "/business/inventory_movement";
    }


    @RequestMapping(value = "/addInventoryMovementOrder")
    @RequiresPermissions("business:inventoryMovement:menu")
    @ResponseBody
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor= Exception.class)
    public R addInventoryMovementOrder(String inventoryMovementOrder,Integer warehouseOutId,Integer warehouseInId,String bimoReason) {
        if (StringUtils.isEmpty(inventoryMovementOrder)) {
            return R.error("请不要提交空白调拨单");
        }

        JSONArray movementDetails= JSONArray.fromObject(inventoryMovementOrder);

        List<SerialNumber> serialNumbers=new ArrayList<>();
        for (int i = 0; i <movementDetails.size() ; i++) {
            JSONObject object=movementDetails.getJSONObject(i);
            SerialNumber serialNumber=new SerialNumber();
            int id=object.getInt("id");
            String sn=object.getString("sn");
            serialNumber.setId(id);
            serialNumber.setSn(sn);
            //serialNumber.setStatus(1);
            serialNumber.setWarehouseId(warehouseInId);
            serialNumbers.add(serialNumber);
        }



        InventoryMovementOrder order=new InventoryMovementOrder();

        order.setBimoNumber(movementDetails.size());
        for (int i = 0; i <movementDetails.size() ; i++) {
            JSONObject object=movementDetails.getJSONObject(i);
           InventoryMovementDetail detail=new InventoryMovementDetail();
            detail.setPhoneId(object.getInt("phoneId"));
            detail.setId(object.getInt("id"));
            order.getInventoryMovementDetails().add(detail);
        }

        User user= (User) SecurityUtils.getSubject().getPrincipal();
        order.setUserId(user.getUserId());
        order.setWarehouseOutid(warehouseOutId);
        order.setWarehouseInid(warehouseInId);
        order.setBimoReason(bimoReason);


        inventoryMovementOrderService.save(serialNumbers,order);
//
//        ret.put("type","success");
//        ret.put("msg", "订单添加成功");
        return R.ok("订单添加成功");

    }




}
