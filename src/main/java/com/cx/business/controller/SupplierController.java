package com.cx.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cx.business.beans.Supplier;
import com.cx.business.service.ISupplierService;
import com.cx.common.model.R;
import com.cx.sys.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cx
 * @since 2020-03-16
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    /**
     * 查询所有供应商
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/findAllSupplier")
    public ModelAndView findAllSupplier(ModelAndView modelAndView){
        List<Supplier> supplierList =supplierService.list();
        modelAndView.addObject("supplierList",supplierList);
        modelAndView.setViewName("business/supplier-list");
        return modelAndView;
    }

    @RequestMapping(value = "/addSupplier",method = RequestMethod.POST)
    public String addSupplier(Supplier supplier){
        supplierService.save(supplier);
        return "redirect:/supplier/findAllSupplier";
    }

    @RequestMapping(value = "/editSupplier",method = RequestMethod.POST)
    public String editSupplier(Supplier supplier){
        supplierService.updateById(supplier);
        return "redirect:/supplier/findAllSupplier";
    }

    @RequestMapping(value = "/removeSupplier",method = RequestMethod.POST)
    @ResponseBody
    public R removeSupplier(Integer supplierId){
        if (supplierId==null){
            return R.error("请先勾选要删除的供应商");
        }
        try {
            supplierService.removeById(supplierId);
            return R.ok();
        } catch (Exception e) {
            return R.error("该供应商下存在权限或者商品订单信息，不能删除！");
        }
    }


    @RequestMapping(value = "/getTargetSuppliers" )
    @ResponseBody
    public R getTargetSuppliers(){
        QueryWrapper<Supplier> queryWrapper=new QueryWrapper<>();
        List<Supplier> supplierList=supplierService.list(queryWrapper);
        return R.ok().put("supplierList",supplierList);
    }

}
