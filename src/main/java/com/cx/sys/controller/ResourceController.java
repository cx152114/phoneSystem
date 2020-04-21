package com.cx.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.common.model.R;
import com.cx.common.model.TreeNode;
import com.cx.sys.beans.Resource;
import com.cx.sys.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author cx
 * @since 2020-03-11
 */
@Controller
@RequestMapping("/sys/resource")
public class ResourceController {


    @Autowired
    private IResourceService resourceService;

    /**
     * 跳转到资源管理的初始化页面
     * @return
     */
    @GetMapping
    public String index(@RequestParam(defaultValue = "0") Integer parentId,Model model) {
        model.addAttribute("parentId",parentId);
        return "sys/resource-index";
    }

    @RequestMapping(value = "/showResource")
    public String showResource(@RequestParam(defaultValue = "0") Integer parentId, Model model){
        model.addAttribute("parentId",parentId);
        return "/sys/resource-index";
    }

    /**
     * 获取左侧菜单树
     * @return
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public R tree() {
        TreeNode treeNode = resourceService.getTreeById(0);
        return R.ok("请求成功", treeNode);
    }

    /**
     * 跳转到资源列表页面
     * @return
     */
    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "0") Integer parentId,Model model){
        // 查询出父节点的详细信息传递给resource_list.jsp
        Resource parent = resourceService.getById(parentId);
        model.addAttribute("parent",parent);
        return "sys/resource-list";
    }


    /**
     * 获取列表数据
     * @param parentId
     * @param page
     * @return
     */
    @RequestMapping("/data")
    @ResponseBody
    public R data(@RequestParam(defaultValue = "0") Integer parentId, Page<Resource> page){
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",parentId);
        queryWrapper.orderByAsc("order_num");
        resourceService.page(page,queryWrapper);
        return R.ok(page);
    }


    /**
     * 跳转到新增资源页面
     * @param parentId
     * @return
     */
    @RequestMapping("/add/{parentId}")
    public String add(@PathVariable Integer parentId, Model model){
        model.addAttribute("parent",resourceService.getById(parentId));
        return "sys/resource-add";
    }

    /**
     * 新增
     * @param resource
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public R add(Resource resource) {
        resourceService.save(resource);
        return R.ok();
    }

    /**
     * 跳转到修改页面
     * @param resourceId
     * @param model
     * @return
     */
    @GetMapping("/update/{resourceId}")
    public String update(@PathVariable Integer resourceId,Model model) {
        // 查询当前资源
        Resource resource = resourceService.getById(resourceId);
        model.addAttribute("resource",resource);
        // 查询上级资源
        Resource parent = resourceService.getById(resource.getParentId());
        model.addAttribute("parent",parent);
        return "sys/resource-update";
    }

    /**
     * 更新数据
     * @param resource
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public R update(Resource resource){
        resourceService.updateById(resource);
        return R.ok();
    }

    /**
     * 删除资源
     * @param resourceId
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public R delete( Integer resourceId){
        resourceService.removeById(resourceId);
        return R.ok();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/deletebatch")
    @ResponseBody
    public R deletebatch(@RequestBody List<Integer> ids) {
        resourceService.removeByIds(ids);
        return R.ok();
    }


}
