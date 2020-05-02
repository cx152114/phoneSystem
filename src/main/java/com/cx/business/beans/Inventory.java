package com.cx.business.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cx.common.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
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
@Data
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date firstStockTime;

    /**
     * 最后一次库存变更时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
}
