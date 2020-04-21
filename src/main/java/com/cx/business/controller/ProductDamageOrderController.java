package com.cx.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.*;
import com.cx.business.service.IProductDamageDetailService;
import com.cx.business.service.IProductDamageOrderService;
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
 * @since 2020-03-27
 */
@Controller
@RequestMapping("/business/productDamageOrder")
public class ProductDamageOrderController {

    @Autowired
    private IProductDamageOrderService productDamageOrderService;

    @Autowired
    private IProductDamageDetailService productDamageDetailService;



    @RequestMapping(value = "/findAllProductDamageOrder",method = RequestMethod.GET)
    public ModelAndView findAllProductDamageOrder(ModelAndView modelAndView){
        QueryWrapper queryWrapper=new QueryWrapper();
        List<ProductDamageOrder> productDamageOrders=productDamageOrderService.listProductDamageOrder(queryWrapper);
        modelAndView.addObject("productDamageOrders",productDamageOrders);
        modelAndView.setViewName("/business/product_damage_list");
        return modelAndView;
    }


    @RequestMapping(value = "/getProductDamageDetailsByPdoId",method = RequestMethod.POST)
    @ResponseBody
    public R getProductDamageDetailsByPdoId(Integer pdoId){
        List<ProductDamageDetail> productDamageDetails=productDamageDetailService.listTargetProductDamageDetailByPdoId(pdoId);
        return R.ok().put("productDamageDetails",productDamageDetails);
    }

    @GetMapping("/productDamage")
    private String productSalesBack(){
        return "/business/product_damage";
    }

    @RequestMapping(value = "/addProductDamageOrder")
    @ResponseBody
    public R addProductDamageOrder(String productDamageOrder,Integer warehouseId,String pdoRemark) {
        if (StringUtils.isEmpty(productDamageOrder)) {
            return R.error("请不要提交空白商品报损单");
        }
        if (warehouseId==null){
            return R.error("报损仓库为必填项，请认真填写");
        }

        ProductDamageOrder order=new ProductDamageOrder();

        order.setWarehouseId(warehouseId);
        order.setPdoRemark(pdoRemark);
        User user= (User) SecurityUtils.getSubject().getPrincipal();
        order.setUserId(user.getUserId());

        JSONArray orderDetails= JSONArray.fromObject(productDamageOrder);


        List<SerialNumber> serialNumbers=new ArrayList<>();
        for (int i = 0; i <orderDetails.size() ; i++) {
            SerialNumber serialNumber=new SerialNumber();
            JSONObject object=orderDetails.getJSONObject(i);
            int id=object.getInt("id");
            String sn=object.getString("sn");
            serialNumber.setId(id);
            serialNumber.setSn(sn);
            serialNumber.setStatus(5);
            serialNumbers.add(serialNumber);
        }

        order.setPdoNumber(orderDetails.size());
        BigDecimal totalMoney=BigDecimal.valueOf(0);
        for (int i = 0; i <orderDetails.size() ; i++) {
            JSONObject object=orderDetails.getJSONObject(i);
            ProductDamageDetail productDamageDetail=new ProductDamageDetail();

            productDamageDetail.setPhoneId(object.getInt("phoneId"));
            productDamageDetail.setId(object.getInt("id"));


            productDamageDetail.setUnitPrice(BigDecimal.valueOf(object.getDouble("unitPrice")));
            order.getProductDamageDetails().add(productDamageDetail);
            totalMoney=totalMoney.add(productDamageDetail.getUnitPrice());

        }

        order.setTotalMoney(totalMoney);
        productDamageOrderService.save(serialNumbers,order);
        return R.ok();

    }

}
