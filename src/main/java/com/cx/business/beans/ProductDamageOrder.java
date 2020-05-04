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
@TableName("business_productdamage_order")
@Data
public class ProductDamageOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存报损单单号
     */
    @TableId(value = "pdo_id", type = IdType.AUTO)
    private Integer pdoId;

    /**
     * 仓库编号
     */
    private Integer warehouseId;

    /**
     * 报损日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pdoTime;

    /**
     * 经手人
     */
    private Integer userId;

    /**
     * 商品总数量
     */
    private Integer pdoNumber;

    /**
     * 商品总价值
     */
    private BigDecimal totalMoney;

    /**
     * 备注
     */
    private String pdoRemark;


    @TableField(exist = false)
    private Warehouse warehouse;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private List<ProductDamageDetail> productDamageDetails=new ArrayList<>();

}
