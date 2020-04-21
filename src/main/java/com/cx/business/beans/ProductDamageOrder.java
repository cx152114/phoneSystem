package com.cx.business.beans;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cx.common.util.DateUtil;
import com.cx.sys.beans.User;

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
    private String pdoTimeStr;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private List<ProductDamageDetail> productDamageDetails=new ArrayList<>();




    public Integer getPdoId() {
        return pdoId;
    }

    public void setPdoId(Integer pdoId) {
        this.pdoId = pdoId;
    }
    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }
    public Date getPdoTime() {
        return pdoTime;
    }

    public void setPdoTime(Date pdoTime) {
        this.pdoTime = pdoTime;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getPdoNumber() {
        return pdoNumber;
    }

    public void setPdoNumber(Integer pdoNumber) {
        this.pdoNumber = pdoNumber;
    }
    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
    public String getPdoRemark() {
        return pdoRemark;
    }

    public void setPdoRemark(String pdoRemark) {
        this.pdoRemark = pdoRemark;
    }


    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getPdoTimeStr() {
        if (pdoTime!=null){
            pdoTimeStr= DateUtil.date2String(pdoTime,"yyyy-MM-dd HH:mm:ss");
        }
        return pdoTimeStr;
    }

    public void setPdoTimeStr(String pdoTimeStr) {
        this.pdoTimeStr = pdoTimeStr;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ProductDamageDetail> getProductDamageDetails() {
        return productDamageDetails;
    }

    public void setProductDamageDetails(List<ProductDamageDetail> productDamageDetails) {
        this.productDamageDetails = productDamageDetails;
    }

    @Override
    public String toString() {
        return "ProductDamageOrder{" +
                "pdoId=" + pdoId +
                ", warehouseId=" + warehouseId +
                ", pdoTime=" + pdoTime +
                ", userId=" + userId +
                ", pdoNumber=" + pdoNumber +
                ", totalMoney=" + totalMoney +
                ", pdoRemark='" + pdoRemark + '\'' +
                ", warehouse=" + warehouse +
                ", pdoTimeStr='" + pdoTimeStr + '\'' +
                ", user=" + user +
                '}';
    }
}
