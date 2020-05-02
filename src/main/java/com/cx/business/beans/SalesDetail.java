package com.cx.business.beans;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cx
 * @since 2020-03-21
 */
@TableName("business_sales_detail")
@Data
public class SalesDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sor_id", type = IdType.AUTO)
    private Integer sorId;

    private Integer orderId;

    private Integer phoneId;

    private Integer warehouseId;

    private Integer id;

    private BigDecimal unitPrice;

    @TableField(exist = false)
    private Integer productNumber;

    @TableField(exist = false)
    private BigDecimal money;

    @TableField(exist = false)
    private PhoneInfo phoneInfo;

    public BigDecimal getMoney() {
        if (productNumber!=null&&unitPrice!=null){
            money=unitPrice.multiply(BigDecimal.valueOf(productNumber));
        }
        return money;
    }

}
