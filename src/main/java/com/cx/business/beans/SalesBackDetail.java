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
 * @since 2020-03-22
 */
@TableName("business_salesback_detail")
public class SalesBackDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 销售退货详情单单号
     */
    @TableId(value = "sbd_id", type = IdType.AUTO)
    private Integer sbdId;

    /**
     * 销售退货单单号
     */
    private Integer sboId;

    /**
     * 商品编号
     */
    private Integer phoneId;

    private Integer id;

    /**
     * 退货价格
     */
    private BigDecimal unitPrice;

    @TableField(exist = false)
    private Integer productNumber;

    @TableField(exist = false)
    private BigDecimal money;

    @TableField(exist = false)
    private PhoneInfo phoneInfo;

    public Integer getSbdId() {
        return sbdId;
    }

    public void setSbdId(Integer sbdId) {
        this.sbdId = sbdId;
    }
    public Integer getSboId() {
        return sboId;
    }

    public void setSboId(Integer sboId) {
        this.sboId = sboId;
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
        return "SalesbackDetail{" +
                "sbdId=" + sbdId +
                ", sboId=" + sboId +
                ", phoneId=" + phoneId +
                ", id=" + id +
                ", unitPrice=" + unitPrice +
                ", productNumber=" + productNumber +
                ", money=" + money +
                ", phoneInfo=" + phoneInfo +
                '}';
    }
}
