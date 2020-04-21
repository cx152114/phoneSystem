package com.cx.sys.mapper;

import com.cx.sys.beans.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户与角色对应关系 Mapper 接口
 * </p>
 *
 * @author cx
 * @since 2020-03-14
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 通过用户id查询用户拥有的的角色信息
     * 使用左外连接查询，用户未分配的角色信息一并显示出来
     * @param userId
     * @return
     */
    List<UserRole> selectByUserId(Integer userId);

}
