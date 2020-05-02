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
 * @since 2020-03-20
 */
@TableName("business_preturn_detail")
@Data
public class PreturnDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 采购退货详情单单号
     */
    @TableId(value = "prd_id", type = IdType.AUTO)
    private Integer prdId;

    /**
     * 采购退货单单号
     */
    private Integer proId;

    /**
     * 商品编号
     */
    private Integer phoneId;

    private Integer id;

    /**
     * 单价
     */
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
