package com.cx.business.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cx
 * @since 2020-03-17
 */
@TableName("business_customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "customer_id", type = IdType.AUTO)
    private Integer customerId;

    /**
     * 客户名称
     */
    private String customerName;


    /**
     * 地址
     */
    private String customerAddress;

    /**
     * 手机号
     */
    private String customerPhone;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 银行名称
     */
    private String customerBank;

    /**
     * 银行账号
     */
    private String customerAccount;

    /**
     * 邮箱
     */
    private String customerEmail;


    /**
     * 状态
     */
    private Integer available;

    @TableField(exist = false)
    private String availableStr;


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getCustomerBank() {
        return customerBank;
    }

    public void setCustomerBank(String customerBank) {
        this.customerBank = customerBank;
    }
    public String getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(String customerAccount) {
        this.customerAccount = customerAccount;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getAvailableStr() {
        if (available!=null){
            if (available==0){
                availableStr="合作中";
            }else {
                availableStr="取消合作";
            }
        }
        return availableStr;
    }

    public void setAvailableStr(String availableStr) {
        this.availableStr = availableStr;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "customerId=" + customerId +
            ", customerName=" + customerName +
            ", customerAddress=" + customerAddress +
            ", customerPhone=" + customerPhone +
            ", contact=" + contact +
            ", telephone=" + telephone +
            ", customerBank=" + customerBank +
            ", customerAccount=" + customerAccount +
            ", customerEmail=" + customerEmail +
            ", available=" + available +
        "}";
    }
}
