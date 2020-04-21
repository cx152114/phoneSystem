package com.cx.business.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cx.common.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author cx
 * @since 2020-03-18
 */
@TableName("business_inventory")
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "inventory_id", type = IdType.AUTO)
    private Integer inventoryId;

    /**
     * 仓库编号
     */
    private Integer warehouseId;

    /**
     * 手机编号
     */
    private Integer phoneId;

    /**
     * 第一次入库时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date firstStockTime;

    /**
     * 最后一次库存变更时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastStockTime;

    private Integer productNumber;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private PhoneInfo phoneInfo;

    @TableField(exist = false)
    private Warehouse warehouse;

    @TableField(exist = false)
    private String firstStockTimeStr;

    @TableField(exist = false)
    private String lastStockTimeStr;

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }
    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }
    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }
    public Date getFirstStockTime() {
        return firstStockTime;
    }

    public void setFirstStockTime(Date firstStockTime) {
        this.firstStockTime = firstStockTime;
    }
    public Date getLastStockTime() {
        return lastStockTime;
    }

    public void setLastStockTime(Date lastStockTime) {
        this.lastStockTime = lastStockTime;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public PhoneInfo getPhoneInfo() {
        return phoneInfo;
    }

    public void setPhoneInfo(PhoneInfo phoneInfo) {
        this.phoneInfo = phoneInfo;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getFirstStockTimeStr() {
        if (firstStockTime!=null){
            firstStockTimeStr= DateUtil.date2String(firstStockTime,"yyyy-MM-dd HH:mm:ss");
        }
        return firstStockTimeStr;
    }

    public void setFirstStockTimeStr(String firstStockTimeStr) {
        this.firstStockTimeStr = firstStockTimeStr;
    }

    public String getLastStockTimeStr() {
        if (lastStockTime!=null){
            lastStockTimeStr=DateUtil.date2String(lastStockTime,"yyyy-MM-dd HH:mm:ss");
        }
        return lastStockTimeStr;
    }

    public void setLastStockTimeStr(String lastStockTimeStr) {
        this.lastStockTimeStr = lastStockTimeStr;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", warehouseId=" + warehouseId +
                ", phoneId=" + phoneId +
                ", firstStockTime=" + firstStockTime +
                ", lastStockTime=" + lastStockTime +
                ", productNumber=" + productNumber +
                ", remark='" + remark + '\'' +
                ", phoneInfo=" + phoneInfo +
                ", warehouse=" + warehouse +
                '}';
    }
}
