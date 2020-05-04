package com.cx.sys.beans;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 员工
 * </p>
 *
 * @author cx
 * @since 2020-03-11
 */
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
@Data
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


    /**
     * 0正常  1删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @TableField(exist = false)
    private Dept dept;


}
