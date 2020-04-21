package com.cx.sys.service;

import com.cx.common.model.TreeNode;
import com.cx.sys.beans.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 员工 服务类
 * </p>
 *
 * @author cx
 * @since 2020-03-11
 */
public interface IUserService extends IService<User> {



    /**
     * 根据id查询用户角色名称的集合
     * @param id
     * @return
     */
    Set<String> selectUserRoleNameSet(Integer id);

    /**
     * 根据id查询用户权限名称的集合
     * @param id
     * @return
     */
    Set<String> selectUserPermissionNameSet(Integer id);


    /**
     * 根据用户id获取用户的菜单树
     * @param id
     * @return
     */
    List<TreeNode> getMenuTreeByUserId(Integer id);

}
