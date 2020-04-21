package com.cx.business.beans.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cx.business.beans.PhoneInfo;

import java.io.Serializable;

public class StaticStock implements Serializable {


    private Integer phoneId;


    private String phoneName;


    private Integer number;

    @TableField(exist = false)
    private PhoneInfo phoneInfo;

    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


    public PhoneInfo getPhoneInfo() {
        return phoneInfo;
    }

    public void setPhoneInfo(PhoneInfo phoneInfo) {
        this.phoneInfo = phoneInfo;
    }

    @Override
    public String toString() {
        return "StaticStock{" +
                "phoneId=" + phoneId +
                ", phoneName='" + phoneName + '\'' +
                ", number=" + number +
                ", phoneInfo=" + phoneInfo +
                '}';
    }
}
