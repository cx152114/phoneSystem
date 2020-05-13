package com.cx.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.PreturnDetail;
import com.cx.business.beans.PreturnOrder;
import com.cx.business.beans.SerialNumber;
import com.cx.business.service.IPreturnDetailService;
import com.cx.business.service.IPreturnOrderService;
import com.cx.common.model.R;
import com.cx.sys.beans.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
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
 * @since 2020-03-20
 */
@Controller
@RequestMapping("/business/preturnOrder")
public class PreturnOrderController {

    @Autowired
    private IPreturnOrderService preturnOrderService;


    @Autowired
    private IPreturnDetailService preturnDetailService;


    @GetMapping
    @RequiresPermissions("business:preturnOrderList:search")
    public String pReturnOrderList() {
        return "/business/stock_return_order_list";
    }


    /**
     * 显示所有进货退货单
     * @return
     */
    @RequestMapping(value = "/findAllPreturnOrder",method = RequestMethod.POST)
    @RequiresPermissions("business:preturnOrderList:search")
    @ResponseBody
    public R findAllPreturnOrder(PreturnOrder preturnOrder,Integer minNumber,Integer maxNumber,String startTime,String endTime,Double minAccount,Double maxAccount){
        QueryWrapper<PreturnOrder> queryWrapper=new QueryWrapper<PreturnOrder>();
        if (null!=preturnOrder.getProId()){
            queryWrapper.eq("pro_id",preturnOrder.getProId());
        }
        if (null!=preturnOrder.getProStatus()){
            queryWrapper.eq("pro_status",preturnOrder.getProStatus());
        }
        if (null!=preturnOrder.getUserId()){
            queryWrapper.eq("user_id",preturnOrder.getUserId());
        }
        if(null!=preturnOrder.getPayType()){
            queryWrapper.eq("pay_type",preturnOrder.getPayType());
        }
        if (null!=minNumber){
            queryWrapper.ge("pro_number",minNumber);
        }
        if (null!=maxNumber){
            queryWrapper.le("pro_number",maxNumber);
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
        List<PreturnOrder> list=preturnOrderService.list(queryWrapper);
        //List<PreturnOrder> list=preturnOrderService.listPreturnOrderInfo(queryWrapper);
        return R.ok().put("rows",list);
    }


    /**
     * 获得指定订单的订单详情
     * @param proId
     * @return
     */
    @RequestMapping(value = "/getPreturnOrderById",method = RequestMethod.POST)
    @RequiresPermissions("business:preturnOrderList:search")
    @ResponseBody
    public R getPreturnOrderById(Integer proId){
        List<PreturnDetail> orderDetails=preturnDetailService.listTargetPreturnDetailByProId(proId);
        return R.ok().put("orderDetails",orderDetails);
    }


    /**
     * 跳转到采购退货页面
     * @return
     */
    @GetMapping("/stockProductBack")
    @RequiresPermissions("business:stockProductBack:menu")
    private String stockProductBack(){
        return "/business/product_stock_back";
    }


    @RequestMapping(value = "/addPreturnOrder")
    @RequiresPermissions("business:stockProductBack:menu")
    @ResponseBody
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor= Exception.class)
    public R addPreturnOrder(String pstockOrderDetails,Integer warehouseId,String proReason) {
        if (StringUtils.isEmpty(pstockOrderDetails)) {
            return R.error("请不要提交空白进货退货");
        }
        JSONArray preturnDetails= JSONArray.fromObject(pstockOrderDetails);
        List<SerialNumber> serialNumbers=new ArrayList<>();
        for (int i = 0; i <preturnDetails.size() ; i++) {
            SerialNumber serialNumber=new SerialNumber();
            JSONObject object=preturnDetails.getJSONObject(i);
            int id=object.getInt("id");
            String sn=object.getString("sn");
            serialNumber.setId(id);
            serialNumber.setSn(sn);
            serialNumber.setStatus(1);
            serialNumbers.add(serialNumber);
        }

        PreturnOrder preturnOrder=new PreturnOrder();
        BigDecimal totalMoney=BigDecimal.valueOf(0);
        preturnOrder.setProNumber(preturnDetails.size());
        preturnOrder.setWarehouseId(warehouseId);
        for (int i = 0; i <preturnDetails.size() ; i++) {
            JSONObject object=preturnDetails.getJSONObject(i);
            PreturnDetail preturnDetail=new PreturnDetail();
            preturnDetail.setPhoneId(object.getInt("phoneId"));
            preturnDetail.setUnitPrice(BigDecimal.valueOf(object.getDouble("return_unitPrice")));
            preturnOrder.getPreturnDetails().add(preturnDetail);
            totalMoney=totalMoney.add(preturnDetail.getUnitPrice());
        }

        preturnOrder.setTotalMoney(totalMoney);
        User user= (User) SecurityUtils.getSubject().getPrincipal();
        preturnOrder.setUserId(user.getUserId());
        preturnOrder.setProStatus(0);
        preturnOrder.setProReason(proReason);


        preturnOrderService.save(serialNumbers,preturnOrder);
        return R.ok();

    }

}
