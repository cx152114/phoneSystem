package com.cx.business.beans;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

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
@Data
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

}
