package com.cx.business.beans;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cx.common.util.DateUtil;
import com.cx.sys.beans.User;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author cx
 * @since 2020-03-21
 */
@TableName("business_sales_order")
public class SalesOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 客户编号
     */
    private Integer customerId;

    /**
     * 销售数量
     */
    private Integer salesNumber;

    /**
     * 销售时间
     */
    private Date orderTime;





    /**
     * 订单总数量
     */
    private BigDecimal totalMoney;

    /**
     * 支付方式 ：0：现金，1 银行转账 2：支付宝 3：微信 4：支票 5：其他
     */
    private Integer payType;

    /**
     * 经手人
     */
    private Integer userId;

    /**
     * 订单状态：0 正常 1 取消 (用于确定订单未完成前的订单退货)
     */
    private Integer orderStatus;

    /**
     * 备注
     */
    private String orderRemark;


    @TableField(exist = false)
    private String orderTimeStr;

    @TableField(exist = false)
    private String payTypeStr;

    @TableField(exist = false)
    private String orderStatusStr;


    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Customer customer;

    @TableField(exist = false)
    private List<SalesDetail> salesDetailList=new ArrayList<>();



    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(Integer salesNumber) {
        this.salesNumber = salesNumber;
    }
    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



    public String getOrderTimeStr() {
        if (orderTime!=null){
            orderTimeStr= DateUtil.date2String(orderTime,"yyyy-MM-dd HH:mm:ss");
        }
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public String getPayTypeStr() {
        if (payType!=null){
            if (payType==0){
                payTypeStr="现金";
            }else if (payType==1){
                payTypeStr="银行转账";
            }else if (payType==2){
                payTypeStr="支付宝";
            }else if (payType==3){
                payTypeStr="微信";
            }else if (payType==4){
                payTypeStr="支票";
            }else {
                payTypeStr="其它";
            }
        }
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }



    public String getOrderStatusStr() {
        if (orderStatus!=null){
            if (orderStatus==0){
                orderStatusStr="未完成";
            }else if (orderStatus==1){
                orderStatusStr="已完成";
            }else if (orderStatus==2){
                orderStatusStr="已取消";
            }
        }
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SalesDetail> getSalesDetailList() {
        return salesDetailList;
    }

    public void setSalesDetailList(List<SalesDetail> salesDetailList) {
        this.salesDetailList = salesDetailList;
    }

    @Override
    public String toString() {
        return "SalesOrder{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", salesNumber=" + salesNumber +
                ", orderTime=" + orderTime +
                ", totalMoney=" + totalMoney +
                ", payType=" + payType +
                ", userId=" + userId +
                ", orderStatus=" + orderStatus +
                ", orderRemark='" + orderRemark + '\'' +
                ", orderTimeStr='" + orderTimeStr + '\'' +
                ", payTypeStr='" + payTypeStr + '\'' +
                ", orderStatusStr='" + orderStatusStr + '\'' +
                ", user=" + user +
                ", customer=" + customer +
                '}';
    }
}
