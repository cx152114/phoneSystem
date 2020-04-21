package com.cx.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.common.exception.BizException;
import com.cx.sys.beans.Role;
import com.cx.sys.mapper.RoleMapper;
import com.cx.sys.mapper.RoleResourceMapper;
import com.cx.sys.mapper.UserRoleMapper;
import com.cx.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author cx
 * @since 2020-03-14
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;


    @Override
    public boolean save(Role role) {
        String roleName = role.getRoleName();
        if (StringUtils.isEmpty(roleName)) {
            throw new BizException("角色名称不能为空");
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_name", roleName);
        if (this.count(queryWrapper) > 0) {
            throw new BizException("角色名称已经被使用");
        }
        return super.save(role);
    }

    @Override
    public boolean updateById(Role role) {
        String roleName = role.getRoleName();
        if (StringUtils.isEmpty(roleName)) {
            throw new BizException("角色名称不能为空");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_name", roleName);
        queryWrapper.ne("role_id", role.getRoleId());
        if (this.count(queryWrapper) > 0) {
            throw new BizException("角色名称已经被使用");
        }

        return super.updateById(role);
    }

    @Override
    public boolean removeById(Serializable id) {
        // 将sys_user_role表中该角色的信息一并删除
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", id);
        userRoleMapper.delete(queryWrapper);
        //将sys_role_resource表中该角色的信息一并删除
        roleResourceMapper.delete(queryWrapper);
        return super.removeById(id);
    }
}
