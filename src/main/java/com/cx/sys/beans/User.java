package com.cx.sys.beans;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cx.common.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 员工
 * </p>
 *
 * @author cx
 * @since 2020-03-11
 */
@TableName("sys_user")
public class User extends Model<User> {

    private static final long serialVersionUID=1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 手机号
     */
    private String userPhone;

    /**
     * 头像
     */
    private String userImage;

    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 状态  0：正常   1：禁用   2：锁定
     */
    private Integer userStatus;

    @TableField(exist = false)
    private String userStatusStr;

    /**
     * 0正常  1删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date createTime;


    private Date updateTime;

    @TableField(exist = false)
    private String createTimeStr;

    @TableField(exist = false)
    private String updateTimeStr;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String getCreateTimeStr() {
        if (createTime!=null){
            createTimeStr= DateUtil.date2String(createTime,"yyyy-MM-dd HH:mm:ss");
        }
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {

        this.createTimeStr = createTimeStr;
    }

    public String getUpdateTimeStr() {
        if (updateTime!=null){
            updateTimeStr= DateUtil.date2String(updateTime,"yyyy-MM-dd HH:mm:ss");
        }
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserStatusStr() {
        if (userStatus!=null){
            if (userStatus==0){
                userStatusStr="正常";
            }else if (userStatus==1){
                userStatusStr="禁用";
            }else{
                userStatusStr="锁定";
            }
        }
        return userStatusStr;
    }

    public void setUserStatusStr(String userStatusStr) {
        this.userStatusStr = userStatusStr;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userImage='" + userImage + '\'' +
                ", deptId=" + deptId +
                ", userStatus=" + userStatus +
                ", userStatusStr='" + userStatusStr + '\'' +
                ", deleted=" + deleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
