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
 * @since 2020-03-20
 */
@TableName("business_preturn_order")
@Data
public class PreturnOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 退货单号
     */
    @TableId(value = "pro_id", type = IdType.AUTO)
    private Integer proId;

    private Integer warehouseId;


    /**
     * 退货总数量
     */
    private Integer proNumber;

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
    private Integer proStatus;

    /**
     * 退货理由
     */
    private String proReason;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private List<PreturnDetail> preturnDetails=new ArrayList<>();

}
