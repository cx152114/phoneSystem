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
 * @since 2020-03-27
 */
@TableName("business_inventory_movement_order")
public class InventoryMovementOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存调拨单单号
     */
    @TableId(value = "bimOrder_id", type = IdType.AUTO)
    private Integer bimorderId;

    /**
     * 调出仓库
     */
    private Integer warehouseOutid;

    /**
     * 调入仓库
     */
    private Integer warehouseInid;

    /**
     * 调拨日期
     */
    private Date movementTime;

    /**
     * 经手人
     */
    private Integer userId;

    /**
     * 商品总数量
     */
    private Integer bimoNumber;



    /**
     * 调拨原因
     */
    private String bimoReason;



    @TableField(exist = false)
    private Warehouse outWarehouse;

    @TableField(exist = false)
    private Warehouse inWarehouse;

    @TableField(exist = false)
    private String movementTimeStr;

    @TableField(exist = false)
    private User user;


    @TableField(exist = false)
    private List<InventoryMovementDetail> inventoryMovementDetails=new ArrayList<>();


    public Integer getBimorderId() {
        return bimorderId;
    }

    public void setBimorderId(Integer bimorderId) {
        this.bimorderId = bimorderId;
    }
    public Integer getWarehouseOutid() {
        return warehouseOutid;
    }

    public void setWarehouseOutid(Integer warehouseOutid) {
        this.warehouseOutid = warehouseOutid;
    }
    public Integer getWarehouseInid() {
        return warehouseInid;
    }

    public void setWarehouseInid(Integer warehouseInid) {
        this.warehouseInid = warehouseInid;
    }
    public Date getMovementTime() {
        return movementTime;
    }

    public void setMovementTime(Date movementTime) {
        this.movementTime = movementTime;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getBimoNumber() {
        return bimoNumber;
    }

    public void setBimoNumber(Integer bimoNumber) {
        this.bimoNumber = bimoNumber;
    }

    public String getBimoReason() {
        return bimoReason;
    }

    public void setBimoReason(String bimoReason) {
        this.bimoReason = bimoReason;
    }


    public Warehouse getOutWarehouse() {
        return outWarehouse;
    }

    public void setOutWarehouse(Warehouse outWarehouse) {
        this.outWarehouse = outWarehouse;
    }

    public Warehouse getInWarehouse() {
        return inWarehouse;
    }

    public void setInWarehouse(Warehouse inWarehouse) {
        this.inWarehouse = inWarehouse;
    }

    public String getMovementTimeStr() {
        if (movementTime!=null){
            movementTimeStr= DateUtil.date2String(movementTime,"yyyy-MM-dd HH:mm:ss");
        }
        return movementTimeStr;
    }

    public void setMovementTimeStr(String movementTimeStr) {

        this.movementTimeStr = movementTimeStr;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<InventoryMovementDetail> getInventoryMovementDetails() {
        return inventoryMovementDetails;
    }

    public void setInventoryMovementDetails(List<InventoryMovementDetail> inventoryMovementDetails) {
        this.inventoryMovementDetails = inventoryMovementDetails;
    }

    @Override
    public String toString() {
        return "InventoryMovementOrder{" +
                "bimorderId=" + bimorderId +
                ", warehouseOutid=" + warehouseOutid +
                ", warehouseInid=" + warehouseInid +
                ", movementTime=" + movementTime +
                ", userId=" + userId +
                ", bimoNumber=" + bimoNumber +
                ", bimoReason='" + bimoReason + '\'' +
                ", outWarehouse=" + outWarehouse +
                ", inWarehouse=" + inWarehouse +
                ", movementTimeStr='" + movementTimeStr + '\'' +
                ", user=" + user +
                ", inventoryMovementDetails=" + inventoryMovementDetails +
                '}';
    }
}
