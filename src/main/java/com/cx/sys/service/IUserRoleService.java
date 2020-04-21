package com.cx.sys.service;

import com.cx.sys.beans.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户与角色对应关系 服务类
 * </p>
 *
 * @author cx
 * @since 2020-03-14
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 根据用户id查询用户拥有的角色
     * @param userId
     * @return
     */
    List<UserRole> getByUserId(Integer userId);

    /**
     * 保存给当前用户分配的角色
     * @param userId
     * @param roleIdList
     * @return
     */
    boolean save(Integer userId,List<Integer> roleIdList);

}
