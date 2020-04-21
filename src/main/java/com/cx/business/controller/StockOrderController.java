package com.cx.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.SerialNumber;
import com.cx.business.beans.SorderDetail;
import com.cx.business.beans.StockOrder;
import com.cx.business.service.ISerialNumberService;
import com.cx.business.service.ISorderDetailService;
import com.cx.business.service.IStockOrderService;
import com.cx.common.model.R;
import com.cx.sys.beans.User;
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
 * @since 2020-03-18
 */
@Controller
@RequestMapping("/business/stockOrder")
public class StockOrderController {

    @Autowired
    private IStockOrderService stockOrderService;

    @Autowired
    private ISorderDetailService sorderDetailService;

    @Autowired
    private ISerialNumberService serialNumberService;

    /**
     * 显示所有订单
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/findAllStockOrder",method = RequestMethod.GET)

    public ModelAndView findAllStockOrder(ModelAndView modelAndView){
        QueryWrapper queryWrapper=new QueryWrapper();
        List<StockOrder> stockOrderList=stockOrderService.listStockOrderInfo(queryWrapper);
        modelAndView.addObject("stockOrderList",stockOrderList);
        modelAndView.setViewName("/business/stock-order-list");
        return modelAndView;
    }





    /**
     * 获得指定订单的订单详情
     * @param modelAndView
     * @param stoId
     * @return
     */
    @RequestMapping(value = "/getOrderDetailById",method = RequestMethod.POST)
    @ResponseBody
    public R getOrderDetailById(ModelAndView modelAndView, Integer stoId){
        List<SorderDetail> orderDetails=sorderDetailService.listTargetStockOrderDetailByStoId(stoId);
        return R.ok().put("orderDetails",orderDetails);
    }

    /**
     * 跳转到采购商品页面
     * @return
     */
    @GetMapping("/stockProduct")
    private String stockProduct(){
        return "/business/product-stock";
    }


    /**
     * 记录本次采购所产生的数据
     * @param stockOrderDetails
     * @return
     */
    @RequestMapping(value = "/addStockOrder",method = RequestMethod.POST)
    @ResponseBody
    public R addStockOrder(String stockOrderDetails,Integer warehouseId,Integer payType,Integer stoStatus) {

        if (StringUtils.isEmpty(stockOrderDetails)) {
            return R.error("请不要提交空白进货单");
        }

        StockOrder stockOrder=new StockOrder();
        stockOrder.setWarehouseId(warehouseId);
        stockOrder.setPayType(payType);
        stockOrder.setStoStatus(stoStatus);
        stockOrder.setStoRemark("测试数据");

        JSONArray orderDetail= JSONArray.fromObject(stockOrderDetails);
        BigDecimal totalMoney=BigDecimal.valueOf(0);
        int stoNumber=0;


        List<SerialNumber> serialNumberList=new ArrayList<>();
        for (int i = 0; i < orderDetail.size() ; i++){
            JSONObject object=orderDetail.getJSONObject(i);
            SerialNumber serialNumber=new SerialNumber();
            serialNumber.setSn(object.getString("SN"));
            serialNumber.setWarehouseId(warehouseId);
            System.out.println(serialNumber);
            serialNumberList.add(serialNumber);
        }


        for (int i = 0; i < orderDetail.size() ; i++) {
            JSONObject object=orderDetail.getJSONObject(i);
            SorderDetail sorderDetail =new SorderDetail();
            sorderDetail.setPhoneId(object.getInt("phoneId"));
            sorderDetail.setProductNumber(object.getInt("phoneNumber"));
            sorderDetail.setUnitPrice(BigDecimal.valueOf(object.getDouble("unitPrice")));
            stockOrder.getSorderDetails().add(sorderDetail);
            totalMoney=totalMoney.add(sorderDetail.getMoney());
            stoNumber=orderDetail.size();
        }
        stockOrder.setStoNumber(stoNumber);
        stockOrder.setTotalMoney(totalMoney);
//
        User user=(User)SecurityUtils.getSubject().getPrincipal();
        stockOrder.setUserId(user.getUserId());
//
//
        stockOrderService.save(serialNumberList,stockOrder);
//        //System.out.println(stoId);
//
//        ret.put("type","success");
//        ret.put("msg", "订单添加成功");
        return R.ok("订单添加成功");

   }

}
