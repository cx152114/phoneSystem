package com.cx.sys.service;

import com.cx.common.model.TreeNode;
import com.cx.sys.beans.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author cx
 * @since 2020-03-11
 */
public interface IResourceService extends IService<Resource> {

     TreeNode getTreeById(Integer id);
}
