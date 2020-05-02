package com.cx.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.*;
import com.cx.business.service.ISalesBackDetailService;
import com.cx.business.service.ISalesBackOrderService;
import com.cx.common.model.R;
import com.cx.sys.beans.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
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
 * @since 2020-03-21
 */
@Controller
@RequestMapping("/business/salesBackOrder")
public class SalesBackOrderController {

    @Autowired
    private ISalesBackOrderService salesBackOrderService;

    @Autowired
    private ISalesBackDetailService salesBackDetailService;


    /**
     * 跳转到销售退货订单管理页面
     * @return
     */
    @GetMapping
    private String salesBackOrderList(){
        return "/business/sale_back_order_list";
    }

    @RequestMapping(value = "/findAllSalesBackOrder",method = RequestMethod.POST)
    @ResponseBody
    public R findAllSalesBackOrder(){
        QueryWrapper<SalesBackOrder> queryWrapper=new QueryWrapper();
        List<SalesBackOrder> salesBackOrderList=salesBackOrderService.listSalesBackOrderInfo(queryWrapper);
        return R.ok().put("rows",salesBackOrderList);
    }

//    @RequestMapping(value = "/findAllSalesBackOrder",method = RequestMethod.GET)
//    public ModelAndView findAllSalesBackOrder(ModelAndView modelAndView){
//        QueryWrapper<SalesBackOrder> queryWrapper=new QueryWrapper();
//        List<SalesBackOrder> salesBackOrderList=salesBackOrderService.listSalesBackOrderInfo(queryWrapper);
//        modelAndView.addObject("salesBackOrderList",salesBackOrderList);
//        modelAndView.setViewName("/business/sale_back_order_list");
//        return modelAndView;
//    }


    @RequestMapping(value = "/getSalesBackOrderBySboId",method = RequestMethod.POST)
    @ResponseBody
    public R getSalesBackOrderBySboId(Integer sboId){
        List<SalesBackDetail> salesBackDetails=salesBackDetailService.listTargetSalesBackDetailBySboId(sboId);
        System.out.println(salesBackDetails);
        return R.ok().put("salesBackDetails",salesBackDetails);
    }

    /**
     * 跳转到销售退货页面
     */
    @GetMapping("/productSalesBack")
    private String productSalesBack(){
        return "/business/product-sale-back";
    }

    @RequestMapping(value = "/addSalesBackOrder")
    @ResponseBody
    public R addSalesBackOrder(String salesBackDetails,Integer customerId,Integer warehouseId,String sboReason) {
        if (StringUtils.isEmpty(salesBackDetails)) {
            return R.error("请不要提交空白销售退货单");
        }
        if (customerId==null){
            return R.error("请正确选择客户之后再进行订单提交");
        }
        if (warehouseId==null){
            return R.error("请正确选择退货仓库之后在进行订单提交");
        }

        SalesBackOrder salesBackOrder=new SalesBackOrder();
        salesBackOrder.setCustomerId(customerId);
        salesBackOrder.setSboReason(sboReason);
        salesBackOrder.setWarehouseId(warehouseId);

        JSONArray orderDetails= JSONArray.fromObject(salesBackDetails);


        List<SerialNumber> serialNumbers=new ArrayList<>();
        for (int i = 0; i <orderDetails.size() ; i++) {
            SerialNumber serialNumber=new SerialNumber();
            JSONObject object=orderDetails.getJSONObject(i);
            int id=object.getInt("id");
            String sn=object.getString("sn");
            serialNumber.setId(id);
            serialNumber.setSn(sn);
            serialNumber.setWarehouseId(warehouseId);
            serialNumber.setStatus(3);
            serialNumbers.add(serialNumber);
        }



        BigDecimal totalMoney=BigDecimal.valueOf(0);
        salesBackOrder.setSboNumber(orderDetails.size());
        for (int i = 0; i <orderDetails.size() ; i++) {

            JSONObject object=orderDetails.getJSONObject(i);
            SalesBackDetail salesBackDetail=new SalesBackDetail();

            salesBackDetail.setPhoneId(object.getInt("phoneId"));
            salesBackDetail.setId(object.getInt("id"));


            salesBackDetail.setUnitPrice(BigDecimal.valueOf(object.getDouble("return_unitPrice")));
            salesBackOrder.getSalesBackDetailList().add(salesBackDetail);
            totalMoney=totalMoney.add(salesBackDetail.getUnitPrice());

        }

        salesBackOrder.setTotalMoney(totalMoney);
        User user= (User) SecurityUtils.getSubject().getPrincipal();
        salesBackOrder.setUserId(user.getUserId());

        salesBackOrderService.save(serialNumbers,salesBackOrder);

        return R.ok("销售退货成功");

    }


}
