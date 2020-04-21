package com.cx.business.beans;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cx.common.util.DateUtil;
import com.cx.sys.beans.User;

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
@TableName("business_salesback_order")
public class SalesBackOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 销售退货单编号
     */
    @TableId(value = "sbo_id", type = IdType.AUTO)
    private Integer sboId;

    private Integer customerId;

    /**
     * 仓库编号
     */
    private Integer warehouseId;

    /**
     * 订单数量
     */
    private Integer sboNumber;

    /**
     * 退货时间
     */
    private Date orderTime;

    /**
     * 总金额
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
    private Integer sboStatus;

    /**
     * 退货理由
     */
    private String sboReason;

    //附加信息
    @TableField(exist = false)
    private String orderTimeStr;

    @TableField(exist = false)
    private String payTypeStr;

    @TableField(exist = false)
    private String sboStatusStr;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Customer customer;

    @TableField(exist = false)
    private List<SalesBackDetail> salesBackDetailList=new ArrayList<SalesBackDetail>();



    public Integer getSboId() {
        return sboId;
    }

    public void setSboId(Integer sboId) {
        this.sboId = sboId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getSboNumber() {
        return sboNumber;
    }

    public void setSboNumber(Integer sboNumber) {
        this.sboNumber = sboNumber;
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
    public Integer getSboStatus() {
        return sboStatus;
    }

    public void setSboStatus(Integer sboStatus) {
        this.sboStatus = sboStatus;
    }
    public String getSboReason() {
        return sboReason;
    }

    public void setSboReason(String sboReason) {
        this.sboReason = sboReason;
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

    public String getSboStatusStr() {
        if (sboStatus!=null){
            if (sboStatus==0){
                sboStatusStr="未完成";
            }else if (sboStatus==1){
                sboStatusStr="已完成";
            }else if (sboStatus==2){
                sboStatusStr="已取消";
            }
        }
        return sboStatusStr;
    }

    public void setSboStatusStr(String sboStatusStr) {
        this.sboStatusStr = sboStatusStr;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public List<SalesBackDetail> getSalesBackDetailList() {
        return salesBackDetailList;
    }

    public void setSalesBackDetailList(List<SalesBackDetail> salesBackDetailList) {
        this.salesBackDetailList = salesBackDetailList;
    }

    @Override
    public String toString() {
        return "SalesBackOrder{" +
                "sboId=" + sboId +
                ", customerId=" + customerId +
                ", sboNumber=" + sboNumber +
                ", orderTime=" + orderTime +
                ", totalMoney=" + totalMoney +
                ", payType=" + payType +
                ", userId=" + userId +
                ", sboStatus=" + sboStatus +
                ", sboReason='" + sboReason + '\'' +
                ", orderTimeStr='" + orderTimeStr + '\'' +
                ", payTypeStr='" + payTypeStr + '\'' +
                ", sboStatusStr='" + sboStatusStr + '\'' +
                ", user=" + user +
                ", customer=" + customer +
                ", salesBackDetailList=" + salesBackDetailList +
                '}';
    }
}
