package com.cx.business.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author cx
 * @since 2020-03-27
 */
@TableName("business_productdamage_detail")
public class ProductDamageDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存报损详情单单号
     */
    @TableId(value = "pdd_id", type = IdType.AUTO)
    private Integer pddId;

    /**
     * 库存报损单单号
     */
    private Integer pdoId;

    /**
     * 商品编号
     */
    private Integer phoneId;

    /**
     * 串号编号
     */
    private Integer id;


    /**
     * 商品价值
     */
    private BigDecimal unitPrice;

    @TableField(exist = false)
    private Integer productNumber;

    @TableField(exist = false)
    private PhoneInfo phoneInfo;


    @TableField(exist = false)
    private SerialNumber serialNumber;

    public Integer getPddId() {
        return pddId;
    }

    public void setPddId(Integer pddId) {
        this.pddId = pddId;
    }
    public Integer getPdoId() {
        return pdoId;
    }

    public void setPdoId(Integer pdoId) {
        this.pdoId = pdoId;
    }
    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public PhoneInfo getPhoneInfo() {
        return phoneInfo;
    }

    public void setPhoneInfo(PhoneInfo phoneInfo) {
        this.phoneInfo = phoneInfo;
    }

    public SerialNumber getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(SerialNumber serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    @Override
    public String toString() {
        return "ProductDamageDetail{" +
                "pddId=" + pddId +
                ", pdoId=" + pdoId +
                ", phoneId=" + phoneId +
                ", id=" + id +
                ", unitPrice=" + unitPrice +
                ", phoneInfo=" + phoneInfo +
                ", serialNumber=" + serialNumber +
                '}';
    }
}
