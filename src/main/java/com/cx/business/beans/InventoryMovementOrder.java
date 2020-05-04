package com.cx.business.beans;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cx.common.util.DateUtil;
import com.cx.sys.beans.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

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
@Data
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    private User user;


    @TableField(exist = false)
    private List<InventoryMovementDetail> inventoryMovementDetails=new ArrayList<>();

}
