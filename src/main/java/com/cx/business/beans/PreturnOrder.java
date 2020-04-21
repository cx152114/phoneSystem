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
 * @since 2020-03-20
 */
@TableName("business_preturn_order")
public class PreturnOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 退货单号
     */
    @TableId(value = "pro_id", type = IdType.AUTO)
    private Integer proId;

    private Integer warehouseId;


    /**
     * 退货总数量
     */
    private Integer proNumber;

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
    private Integer proStatus;

    /**
     * 退货理由
     */
    private String proReason;


    //附加信息
    @TableField(exist = false)
    private String orderTimeStr;

    @TableField(exist = false)
    private String payTypeStr;

    @TableField(exist = false)
    private String proStatusStr;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private List<PreturnDetail> preturnDetails=new ArrayList<>();


    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getProNumber() {
        return proNumber;
    }

    public void setProNumber(Integer proNumber) {
        this.proNumber = proNumber;
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
    public Integer getProStatus() {
        return proStatus;
    }

    public void setProStatus(Integer proStatus) {
        this.proStatus = proStatus;
    }
    public String getProReason() {
        return proReason;
    }

    public void setProReason(String proReason) {
        this.proReason = proReason;
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

    public String getProStatusStr() {
        if (proStatus!=null){
            if (proStatus==0){
                proStatusStr="未完成";
            }else if (proStatus==1){
                proStatusStr="已完成";
            }else if (proStatus==2){
                proStatusStr="已取消";
            }
        }
        return proStatusStr;
    }

    public void setProStatusStr(String proStatusStr) {
        this.proStatusStr = proStatusStr;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PreturnDetail> getPreturnDetails() {
        return preturnDetails;
    }

    public void setPreturnDetails(List<PreturnDetail> preturnDetails) {
        this.preturnDetails = preturnDetails;
    }

    @Override
    public String toString() {
        return "PreturnOrder{" +
                "proId=" + proId +
                ", proNumber=" + proNumber +
                ", orderTime=" + orderTime +
                ", totalMoney=" + totalMoney +
                ", payType=" + payType +
                ", userId=" + userId +
                ", proStatus=" + proStatus +
                ", proReason='" + proReason + '\'' +
                ", orderTimeStr='" + orderTimeStr + '\'' +
                ", payTypeStr='" + payTypeStr + '\'' +
                ", proStatusStr='" + proStatusStr + '\'' +
                ", user=" + user +
                '}';
    }
}
