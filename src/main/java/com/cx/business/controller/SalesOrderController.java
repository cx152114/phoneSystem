package com.cx.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.*;
import com.cx.business.service.ISalesDetailService;
import com.cx.business.service.ISalesOrderService;
import com.cx.business.service.IStockOrderService;
import com.cx.common.model.R;
import com.cx.sys.beans.User;
import lombok.Data;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2020-03-21
 */
@Controller
@RequestMapping("/business/salesOrder")
public class SalesOrderController {

    @Autowired
    private ISalesOrderService salesOrderService;

    @Autowired
    private ISalesDetailService salesDetailService;


    /**
     * 跳转到销售订单管理页面
     * @return
     */
    @GetMapping
    private String salesOrderList(){
        return "/business/sale-order-list";
    }


    @RequestMapping(value = "/findAllSalesOrder",method = RequestMethod.POST)
    @ResponseBody
    public R findAllSalesOrder(SalesOrder salesOrder,Integer minNumber,Integer maxNumber,String startTime,String endTime,Double minAccount,Double maxAccount){
        QueryWrapper<SalesOrder> queryWrapper=new QueryWrapper<SalesOrder>();
        if (null!=salesOrder.getOrderId()){
            queryWrapper.eq("order_id",salesOrder.getOrderId());
        }
        if (null!=salesOrder.getOrderStatus()){
            queryWrapper.eq("order_status",salesOrder.getOrderStatus());
        }
        if (null!=salesOrder.getUserId()){
            queryWrapper.eq("user_id",salesOrder.getUserId());
        }
        if (null!=salesOrder.getCustomerId()){
            queryWrapper.eq("customer_id",salesOrder.getCustomerId());
        }
        if(null!=salesOrder.getPayType()){
            queryWrapper.eq("pay_type",salesOrder.getPayType());
        }
        if (null!=minNumber){
            queryWrapper.ge("sales_number",minNumber);
        }
        if (null!=maxNumber){
            queryWrapper.le("sales_number",maxNumber);
        }
        if (!StringUtils.isEmpty(startTime)){
            queryWrapper.ge("order_time",startTime);
        }
        if (!StringUtils.isEmpty(endTime)){
            queryWrapper.le("order_time",endTime);
        }
        if (null!=minAccount){
            queryWrapper.ge("total_money",minAccount);
        }
        if (null!=maxAccount){
            queryWrapper.le("total_money",maxAccount);
        }
        List<SalesOrder> salesOrderList=salesOrderService.list(queryWrapper);
        //List<SalesOrder> salesOrderList=salesOrderService.listSalesOrderInfo(queryWrapper);
        return R.ok().put("rows",salesOrderList);
    }

    /**
     * 获得指定订单的订单详情
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/getSalesOrderByOrderId",method = RequestMethod.POST)
    @ResponseBody
    public R getSalesOrderByOrderId(Integer orderId){
        List<SalesDetail> salesDetails=salesDetailService.listTargetSalesDetailByOrderId(orderId);
        return R.ok().put("salesDetails",salesDetails);
    }

    @GetMapping("/saleProduct")
    private String stockProduct(){
        return "/business/product-sale";
    }

    @RequestMapping(value = "/addSalesOrder",method = RequestMethod.POST)
    @ResponseBody
    public R addSalesOrder(String salesOrderDetails,Integer customerId,String orderRemark) {


        if (StringUtils.isEmpty(salesOrderDetails)) {
            return R.error("请不要提交没有添加商品的销售订单");
        }
        if (customerId==null){
            return R.error("请正确选择客户之后再进行订单提交");
        }

        SalesOrder salesOrder=new SalesOrder();
        salesOrder.setCustomerId(customerId);
        salesOrder.setOrderRemark(orderRemark);
        JSONArray orderDetails= JSONArray.fromObject(salesOrderDetails);

        List<SerialNumber> serialNumberList=new ArrayList<>();

        for (int i = 0; i < orderDetails.size() ; i++){
            JSONObject object=orderDetails.getJSONObject(i);
            SerialNumber serialNumber=new SerialNumber();
            serialNumber.setId(object.getInt("id"));
            serialNumber.setSn(object.getString("sn"));
            serialNumber.setStatus(2);
            serialNumberList.add(serialNumber);
        }

        System.out.println(serialNumberList);




        BigDecimal totalMoney=BigDecimal.valueOf(0);
        int salesNumber=0;

        for (int i = 0; i < orderDetails.size() ; i++) {
            JSONObject object=orderDetails.getJSONObject(i);
            SalesDetail salesDetail=new SalesDetail();
            salesDetail.setId(object.getInt("id"));
            salesDetail.setPhoneId(object.getInt("phoneId"));
            salesDetail.setWarehouseId(object.getInt("warehouseId"));
            salesDetail.setUnitPrice(BigDecimal.valueOf(object.getDouble("unitPrice")));

            salesOrder.getSalesDetailList().add(salesDetail);
            totalMoney=totalMoney.add(salesDetail.getUnitPrice());
        }
        salesOrder.setSalesNumber(orderDetails.size());
        salesOrder.setTotalMoney(totalMoney);

        User user=(User) SecurityUtils.getSubject().getPrincipal();
        salesOrder.setUserId(user.getUserId());


        salesOrderService.save(serialNumberList,salesOrder);
        //salesOrderService.addSalesOrder(salesOrder);

        return R.ok("订单添加成功");
    }




}
