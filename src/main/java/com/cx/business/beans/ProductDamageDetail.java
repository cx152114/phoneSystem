package com.cx.business.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author cx
 * @since 2020-03-27
 */
@TableName("business_productdamage_detail")
@Data
public class ProductDamageDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存报损详情单单号
     */
    @TableId(value = "pdd_id", type = IdType.AUTO)
    private Integer pddId;

    /**
     * 库存报损单单号
     */
    private Integer pdoId;

    /**
     * 商品编号
     */
    private Integer phoneId;

    /**
     * 串号编号
     */
    private Integer id;


    /**
     * 商品价值
     */
    private BigDecimal unitPrice;

    @TableField(exist = false)
    private Integer productNumber;

    @TableField(exist = false)
    private PhoneInfo phoneInfo;


    @TableField(exist = false)
    private SerialNumber serialNumber;
}
