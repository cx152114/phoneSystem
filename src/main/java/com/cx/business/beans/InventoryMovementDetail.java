package com.cx.business.beans;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cx
 * @since 2020-03-27
 */
@TableName("business_inventory_movement_detail")
public class InventoryMovementDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 调拨明细单单号
     */
    @TableId(value = "bimd_id", type = IdType.AUTO)
    private Integer bimdId;

    /**
     * 库存调拨单单号
     */
    @TableField("bimOrder_id")
    private Integer bimOrderId;

    /**
     * 商品编号
     */
    private Integer phoneId;

    /**
     * 手机序列号
     */
    private Integer id;


    @TableField(exist = false)
    private PhoneInfo phoneInfo;


    @TableField(exist = false)
    private Integer productNumber;




    public Integer getBimdId() {
        return bimdId;
    }

    public void setBimdId(Integer bimdId) {
        this.bimdId = bimdId;
    }
    public Integer getBimOrderId() {
        return bimOrderId;
    }

    public void setBimOrderId(Integer bimOrderId) {
        this.bimOrderId = bimOrderId;
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

    @Override
    public String toString() {
        return "InventoryMovementDetail{" +
                "bimdId=" + bimdId +
                ", bimOrderId=" + bimOrderId +
                ", phoneId=" + phoneId +
                ", id=" + id +
                ", phoneInfo=" + phoneInfo +
                '}';
    }
}
