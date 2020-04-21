package com.cx.sys.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author cx
 * @since 2020-03-17
 */
@TableName("sys_log_login")
public class LogLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    private String loginName;

    private String loginIp;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private Date startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private Date endTime;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "LogLogin{" +
                "loginName='" + loginName + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", loginTime=" + loginTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
