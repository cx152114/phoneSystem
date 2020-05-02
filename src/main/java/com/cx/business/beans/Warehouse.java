package com.cx.business.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cx.sys.beans.User;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cx
 * @since 2020-03-18
 */
@TableName("business_warehouse")
@Data
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "warehouse_id", type = IdType.AUTO)
    private Integer warehouseId;

    private Integer userId;

    private String warehouseName;

    private String warehouseAddress;

    private String warehouseRemark;

    @TableField(exist = false)
    private User user;


}
