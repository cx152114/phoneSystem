package com.cx.business.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

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
@Data
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
}
