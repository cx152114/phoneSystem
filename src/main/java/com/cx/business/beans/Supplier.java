package com.cx.business.beans;

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
 * @since 2020-03-16
 */
@TableName("business_supplier")
@Data
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "supplier_id", type = IdType.AUTO)
    private Integer supplierId;

    /**
     * 地址
     */
    private String supplierName;

    /**
     * 地址
     */
    private String supplierAddress;


    /**
     * 联系人
     */
    private String supplierContacts;

    /**
     * 联系人手机号
     */
    private String supplierPhone;

    private String supplierEmail;

    private String supplierRemark;

}
