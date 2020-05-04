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
 * @since 2020-03-21
 */
@TableName("business_salesback_order")
@Data
public class SalesBackOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 销售退货单编号
     */
    @TableId(value = "sbo_id", type = IdType.AUTO)
    private Integer sboId;

    private Integer customerId;

    /**
     * 仓库编号
     */
    private Integer warehouseId;

    /**
     * 订单数量
     */
    private Integer sboNumber;

    /**
     * 退货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;

    /**
     * 总金额
     */
    private BigDecimal totalMoney;

    /**
     * 支付方式 ：0：现金，1 银行转账 2：支付宝 3：微信 4：支票 5：其他
     */
    private Integer payType;

    /**
     * 经手人
     */
    private Integer userId;

    /**
     * 订单状态：0 正常 1 取消 (用于确定订单未完成前的订单退货)
     */
    private Integer sboStatus;

    /**
     * 退货理由
     */
    private String sboReason;


    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Customer customer;

    @TableField(exist = false)
    private List<SalesBackDetail> salesBackDetailList=new ArrayList<SalesBackDetail>();

}
