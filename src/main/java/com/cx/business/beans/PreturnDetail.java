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
 * @since 2020-03-20
 */
@TableName("business_preturn_detail")
public class PreturnDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 采购退货详情单单号
     */
    @TableId(value = "prd_id", type = IdType.AUTO)
    private Integer prdId;

    /**
     * 采购退货单单号
     */
    private Integer proId;

    /**
     * 商品编号
     */
    private Integer phoneId;

    private Integer id;

    /**
     * 单价
     */
    private BigDecimal unitPrice;



    @TableField(exist = false)
    private Integer productNumber;

    @TableField(exist = false)
    private BigDecimal money;

    @TableField(exist = false)
    private PhoneInfo phoneInfo;


    public Integer getPrdId() {
        return prdId;
    }

    public void setPrdId(Integer prdId) {
        this.prdId = prdId;
    }
    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
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

    public PhoneInfo getPhoneInfo() {
        return phoneInfo;
    }

    public void setPhoneInfo(PhoneInfo phoneInfo) {
        this.phoneInfo = phoneInfo;
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

    @Override
    public String toString() {
        return "PreturnDetail{" +
                "prdId=" + prdId +
                ", proId=" + proId +
                ", phoneId=" + phoneId +
                ", id=" + id +
                ", unitPrice=" + unitPrice +
                ", productNumber=" + productNumber +
                ", money=" + money +
                ", phoneInfo=" + phoneInfo +
                '}';
    }
}
