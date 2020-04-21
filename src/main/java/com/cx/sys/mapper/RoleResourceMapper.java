package com.cx.sys.mapper;

import com.cx.sys.beans.RoleResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色与菜单对应关系 Mapper 接口
 * </p>
 *
 * @author cx
 * @since 2020-03-14
 */
public interface RoleResourceMapper extends BaseMapper<RoleResource> {

    /**
     * 通过角色id查询角色拥有的的资源信息
     * 使用左外连接查询，角色未分配的资源信息一并显示出来
     * @param roleId
     * @return
     */
    List<RoleResource> selectByRoleId(Integer roleId);

}
