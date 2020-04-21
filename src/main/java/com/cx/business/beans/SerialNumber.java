package com.cx.business.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cx
 * @since 2020-03-20
 */
@TableName("business_serial_number")
public class SerialNumber implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 序列号
     */
    @TableField("SN")
    private String sn;

    private Integer status;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private PhoneInfo phoneInfo;

    @TableField(exist = false)
    private SorderDetail sorderDetail;


    @TableField(exist = false)
    private SalesDetail salesDetail;


    private Integer warehouseId;


    @TableField(exist = false)
    private Warehouse warehouse;


    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public PhoneInfo getPhoneInfo() {
        return phoneInfo;
    }

    public void setPhoneInfo(PhoneInfo phoneInfo) {
        this.phoneInfo = phoneInfo;
    }

    public SorderDetail getSorderDetail() {
        return sorderDetail;
    }

    public void setSorderDetail(SorderDetail sorderDetail) {
        this.sorderDetail = sorderDetail;
    }

    public SalesDetail getSalesDetail() {
        return salesDetail;
    }

    public void setSalesDetail(SalesDetail salesDetail) {
        this.salesDetail = salesDetail;
    }


    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }


    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public String toString() {
        return "SerialNumber{" +
                "id=" + id +
                ", sn='" + sn + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", phoneInfo=" + phoneInfo +
                ", sorderDetail=" + sorderDetail +
                ", salesDetail=" + salesDetail +
                ", warehouseId=" + warehouseId +
                ", warehouse=" + warehouse +
                '}';
    }
}
