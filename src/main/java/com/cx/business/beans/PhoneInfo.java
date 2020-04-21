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
 * @since 2020-03-17
 */
@TableName("business_phoneInfo")
public class PhoneInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "phone_id", type = IdType.AUTO)
    private Integer phoneId;

    /**
     * 手机型号
     */
    private String phoneName;

    /**
     * 供应商编号
     */
    private Integer supplierId;

    /**
     * 手机类型 智能机 功能机
     */
    private String phoneType;

    /**
     * 手机颜色
     */
    private String phoneColor;

    /**
     * 运行内存
     */
    @TableField("phone_RAM")
    private String phoneRam;

    /**
     * 储存容量
     */
    private String phoneStorage;

    /**
     * 网络类型
     */
    private String phoneNetwork;

    /**
     * 图片
     */
    private String phoneImage;

    /**
     * 售卖状态 0 在售 1 下架
     */
    private Integer phoneState;


    /**
     * 描述
     */
    private String phoneRemark;

    @TableField(exist = false)
    private Supplier supplier;


    public PhoneInfo() {
    }

    public PhoneInfo(Integer phoneId, String phoneName, Integer supplierId, String phoneType, String phoneColor, String phoneRam, String phoneStorage, String phoneNetwork,  Integer phoneState) {
        this.phoneId = phoneId;
        this.phoneName = phoneName;
        this.supplierId = supplierId;
        this.phoneType = phoneType;
        this.phoneColor = phoneColor;
        this.phoneRam = phoneRam;
        this.phoneStorage = phoneStorage;
        this.phoneNetwork = phoneNetwork;
        this.phoneState = phoneState;
    }

    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }
    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }
    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }
    public String getPhoneColor() {
        return phoneColor;
    }

    public void setPhoneColor(String phoneColor) {
        this.phoneColor = phoneColor;
    }
    public String getPhoneRam() {
        return phoneRam;
    }

    public void setPhoneRam(String phoneRam) {
        this.phoneRam = phoneRam;
    }
    public String getPhoneStorage() {
        return phoneStorage;
    }

    public void setPhoneStorage(String phoneStorage) {
        this.phoneStorage = phoneStorage;
    }
    public String getPhoneNetwork() {
        return phoneNetwork;
    }

    public void setPhoneNetwork(String phoneNetwork) {
        this.phoneNetwork = phoneNetwork;
    }
    public String getPhoneImage() {
        return phoneImage;
    }

    public void setPhoneImage(String phoneImage) {
        this.phoneImage = phoneImage;
    }
    public Integer getPhoneState() {
        return phoneState;
    }

    public void setPhoneState(Integer phoneState) {
        this.phoneState = phoneState;
    }
    public String getPhoneRemark() {
        return phoneRemark;
    }

    public void setPhoneRemark(String phoneRemark) {
        this.phoneRemark = phoneRemark;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Phoneinfo{" +
            "phoneId=" + phoneId +
            ", phoneName=" + phoneName +
            ", supplierId=" + supplierId +
            ", phoneType=" + phoneType +
            ", phoneColor=" + phoneColor +
            ", phoneRam=" + phoneRam +
            ", phoneStorage=" + phoneStorage +
            ", phoneNetwork=" + phoneNetwork +
            ", phoneImage=" + phoneImage +
            ", phoneState=" + phoneState +
            ", phoneRemark=" + phoneRemark +
        "}";
    }
}
