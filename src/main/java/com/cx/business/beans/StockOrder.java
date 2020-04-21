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
 * @since 2020-03-18
 */
@TableName("business_stock_order")
public class StockOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @TableId(value = "sto_id", type = IdType.AUTO)
    private Integer stoId;


    /**
     * 仓库编号
     */
    private Integer warehouseId;


    /**
     * 订单商品总数目
     */
    private Integer stoNumber;

    /**
     * 进货时间
     */
    private Date orderTime;

    @TableField(exist = false)
    private String orderTimeStr;

    /**
     * 订单总金额
     */
    private BigDecimal totalMoney;

    /**
     * 支付方式 ：0：现金，1 银行转账 2：支付宝 3：微信 4：支票 5：其他
     */
    private Integer payType;

    @TableField(exist = false)
    private String payTypeStr;

    /**
     * 经手人
     */
    private Integer userId;

    /**
     * 订单状态：0 未完成 1完成 2 取消
     */
    private Integer stoStatus;

    @TableField(exist = false)
    private String stoStatusStr;

    /**
     * 订单备注
     */
    private String stoRemark;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private  List<SorderDetail> sorderDetails=new ArrayList<>();




    public Integer getStoId() {
        return stoId;
    }

    public void setStoId(Integer stoId) {
        this.stoId = stoId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getStoNumber() {
        return stoNumber;
    }

    public void setStoNumber(Integer stoNumber) {
        this.stoNumber = stoNumber;
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
    public Integer getStoStatus() {
        return stoStatus;
    }

    public void setStoStatus(Integer stoStatus) {
        this.stoStatus = stoStatus;
    }
    public String getStoRemark() {
        return stoRemark;
    }

    public void setStoRemark(String stoRemark) {
        this.stoRemark = stoRemark;
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

    public String getStoStatusStr() {
        if (stoStatus!=null){
            if (stoStatus==0){
                stoStatusStr="未完成";
            }else if (stoStatus==1){
                stoStatusStr="已完成";
            }else if (stoStatus==2){
                stoStatusStr="已取消";
            }
        }
        return stoStatusStr;
    }

    public void setStoStatusStr(String stoStatusStr) {
        this.stoStatusStr = stoStatusStr;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<SorderDetail> getSorderDetails() {
        return sorderDetails;
    }

    public void setSorderDetails(List<SorderDetail> sorderDetails) {
        this.sorderDetails = sorderDetails;
    }

    @Override
    public String toString() {
        return "StockOrder{" +
                "stoId=" + stoId +
                ", stoNumber=" + stoNumber +
                ", orderTime=" + orderTime +
                ", orderTimeStr='" + orderTimeStr + '\'' +
                ", totalMoney=" + totalMoney +
                ", payType=" + payType +
                ", payTypeStr='" + payTypeStr + '\'' +
                ", userId=" + userId +
                ", stoStatus=" + stoStatus +
                ", stoStatusStr='" + stoStatusStr + '\'' +
                ", stoRemark='" + stoRemark + '\'' +
                ", user=" + user +
                '}';
    }
}
