package com.cx.business.beans;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cx
 * @since 2020-03-19
 */
@TableName("business_sorder_detail")
public class SorderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "stod_id", type = IdType.AUTO)
    private Integer stodId;

    private Integer stoId;

    private Integer phoneId;

    private Integer id;

    private BigDecimal unitPrice;

    @TableField(exist = false)
    private Integer productNumber;

    @TableField(exist = false)
    private BigDecimal money;

    @TableField(exist = false)
    private PhoneInfo phoneInfo;

    public Integer getStodId() {
        return stodId;
    }

    public void setStodId(Integer stodId) {
        this.stodId = stodId;
    }
    public Integer getStoId() {
        return stoId;
    }

    public void setStoId(Integer stoId) {
        this.stoId = stoId;
    }
    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public BigDecimal getMoney() {
        if (productNumber!=null&&unitPrice!=null){
            money=unitPrice.multiply(BigDecimal.valueOf(productNumber));
        }
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public PhoneInfo getPhoneInfo() {
        return phoneInfo;
    }

    public void setPhoneInfo(PhoneInfo phoneInfo) {
        this.phoneInfo = phoneInfo;
    }

    @Override
    public String toString() {
        return "SorderDetail{" +
                "stodId=" + stodId +
                ", stoId=" + stoId +
                ", phoneId=" + phoneId +
                ", id=" + id +
                ", unitPrice=" + unitPrice +
                ", productNumber=" + productNumber +
                ", money=" + money +
                ", phoneInfo=" + phoneInfo +
                '}';
    }
}
