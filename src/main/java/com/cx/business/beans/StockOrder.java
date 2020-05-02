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
 * @since 2020-03-18
 */
@TableName("business_stock_order")
@Data
public class StockOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @TableId(value = "sto_id", type = IdType.AUTO)
    private Integer stoId;


    /**
     * 仓库编号
     */
    private Integer warehouseId;


    /**
     * 订单商品总数目
     */
    private Integer stoNumber;

    /**
     * 进货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;


    /**
     * 订单总金额
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
     * 订单状态：0 未完成 1完成 2 取消
     */
    private Integer stoStatus;

    @TableField(exist = false)
    private String stoStatusStr;

    /**
     * 订单备注
     */
    private String stoRemark;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private  List<SorderDetail> sorderDetails=new ArrayList<>();

}
