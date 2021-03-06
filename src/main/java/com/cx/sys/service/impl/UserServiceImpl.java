package com.cx.sys.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cx.common.exception.BizException;
import com.cx.common.model.TreeNode;
import com.cx.common.util.MD5Util;
import com.cx.common.util.TreeUtil;
import com.cx.sys.beans.Dept;
import com.cx.sys.beans.Menu;
import com.cx.sys.beans.User;
import com.cx.sys.mapper.DeptMapper;
import com.cx.sys.mapper.ResourceMapper;
import com.cx.sys.mapper.UserMapper;
import com.cx.sys.mapper.UserRoleMapper;
import com.cx.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 员工 服务实现类
 * </p>
 *
 * @author cx
 * @since 2020-03-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeptMapper deptMapper;




    @Override
    public List<User> list(Wrapper<User> queryWrapper) {
        List<User> list=userMapper.selectList(queryWrapper);
        for (User user:list) {
            Dept dept=deptMapper.selectById(user.getDeptId());
            user.setDept(dept);
        }
        return list;
    }

    @Override
    public Set<String> selectUserRoleNameSet(Integer id) {
        return userMapper.selectUserRoleNameSet(id);
    }

    @Override
    public Set<String> selectUserPermissionNameSet(Integer id) {
        return userMapper.selectUserPermissionNameSet(id);
    }

    /**
     * 获取用户的菜单树
     * @param id
     * @return
     */
    @Override
    public List<TreeNode> getMenuTreeByUserId(Integer id) {
        // 查询用户拥有的菜单资源
        List<Menu> menuList = userMapper.selectMenuList(id);
        if(menuList.isEmpty()){
            return new ArrayList<>();
        }

        // 存储父id是0的节点的id
        List<Integer> nodeIds = new ArrayList<>();
        List<TreeNode> treeNodeList = new ArrayList<>();
        for (Menu menu : menuList) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(menu.getResourceId());
            treeNode.setName(menu.getResourceName());
            treeNode.setParentId(menu.getParentId());
            treeNode.setUrl(menu.getUrl());
            treeNode.setIcon(menu.getIcon());
            treeNodeList.add(treeNode);
            if(treeNode.getParentId() == 0) {
                nodeIds.add(treeNode.getId());
            }
        }
        TreeUtil treeUtil = new TreeUtil(treeNodeList);
        List<TreeNode> treeNodeData = new ArrayList<>();
        for (Integer nodeId : nodeIds) {
            treeNodeData.add(treeUtil.generateTree(nodeId));
        }
        return treeNodeData;
    }

    @Override
    public boolean save(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getUserEmail();
        String mobile = user.getUserPhone();
        // 校验用户名不能为空
        if (StringUtils.isEmpty(username)) {
            throw new BizException("用户名不能为空");
        }
        // 校验密码不能为空
        if (StringUtils.isEmpty(password)) {
            throw new BizException("密码不能为空");
        }

        // 校验用户名是否被占用
        QueryWrapper queryUsername = new QueryWrapper();
        queryUsername.eq("username", username);
        if (this.count(queryUsername) > 0) {
            throw new BizException("用户名已存在");
        }

        if (!StringUtils.isEmpty(mobile)) {
            QueryWrapper queryMobile = new QueryWrapper();
            queryMobile.eq("user_phone", user.getUserPhone());
            if (this.count(queryMobile) > 0) {
                throw new BizException("手机号已经被使用");
            }
        }


        if (!StringUtils.isEmpty(email)) {
            QueryWrapper queryEmail = new QueryWrapper();
            queryEmail.eq("user_email", user.getUserEmail());
            if (this.count(queryEmail) > 0) {
                throw new BizException("邮箱已经被使用");
            }
        }
        String salt= IdUtil.simpleUUID().toUpperCase();
        password = MD5Util.md5_private_salt(password, salt);
        user.setPassword(password);
        user.setSalt(salt);
        return super.save(user);
    }


    @Override
    public boolean updateById(User user) {
        String userEmail = user.getUserEmail();
        String userPhone = user.getUserPhone();

        // 不是当前用户使用了该手机号，表示手机号被占用
        if (!StringUtils.isEmpty(userPhone)) {
            QueryWrapper queryMobile = new QueryWrapper();
            queryMobile.eq("user_phone", user.getUserPhone());
            queryMobile.ne("user_id",user.getUserId());
            if (this.count(queryMobile) > 0) {
                throw new BizException("手机号已经被使用");
            }
        }
        if (!StringUtils.isEmpty(userEmail)) {
            QueryWrapper queryEmail = new QueryWrapper();
            queryEmail.eq("user_email", user.getUserEmail());
            queryEmail.ne("user_id",user.getUserId());
            if (this.count(queryEmail) > 0) {
                throw new BizException("邮箱已经被使用");
            }
        }
        return super.updateById(user);
    }
}
