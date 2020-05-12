package com.cx.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.business.beans.Customer;
import com.cx.common.model.R;
import com.cx.sys.beans.Notice;
import com.cx.sys.beans.User;
import com.cx.sys.service.INoticeService;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cx
 * @since 2020-05-06
 */
@Controller
@RequestMapping("/sys/notice")
public class NoticeController {

    @Autowired
    private INoticeService noticeService;

    @GetMapping
    public String noticeManagement(){
        return "/sys/noticeManagement";
    }

    @RequestMapping(value = "/findAllNotice")
    @ResponseBody
    public R findAllNotice(Page<Notice> page, Notice notice, String startTime, String endTime){
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<Notice>();
        if (!StringUtils.isEmpty(notice.getTitle())){
            queryWrapper.like("title",notice.getTitle());
        }
        if (!StringUtils.isEmpty(notice.getOpername())){
            queryWrapper.like("opername",notice.getOpername());
        }
        if (!StringUtils.isEmpty(startTime)){
            queryWrapper.ge("createtime",startTime);
        }
        if (!StringUtils.isEmpty(endTime)){
            queryWrapper.le("createtime",startTime);
        }
        noticeService.page(page, queryWrapper);
        return R.ok(page);
    }


    @RequestMapping(value = "/addNotice",method = RequestMethod.POST)
    @ResponseBody
    public R addNotice(Notice notice){
        notice.setCreatetime(new Date());
        User user=(User) SecurityUtils.getSubject().getPrincipal();
        notice.setOpername(user.getUsername());
        noticeService.save(notice);
        return R.ok();
    }

    @RequestMapping(value = "/editNotice",method = RequestMethod.POST)
    @ResponseBody
    public R editNotice(Notice notice){
        notice.setCreatetime(new Date());
        //User user=(User) SecurityUtils.getSubject().getPrincipal();
        //notice.setOpername(user.getUsername());
        noticeService.updateById(notice);
        return R.ok();
    }

    @RequestMapping(value = "deleteTargetNotice")
    @ResponseBody
    public R deleteTargetNotice(Integer noticeId){
        noticeService.removeById(noticeId);
        return R.ok("删除成功");
    }


    @RequestMapping(value = "batchDeleteNotices")
    @ResponseBody
    public R batchDeleteNotices(String ids){
        JSONArray arrIds= JSONArray.fromObject(ids);
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <arrIds.size() ; i++) {
            Integer id= (Integer) arrIds.get(i);
            list.add(id);
        }
        noticeService.removeByIds(list);
        return R.ok("删除成功");
    }



    @RequestMapping(value = "/findTargetNotice",method = RequestMethod.POST)
    @ResponseBody
    public R findTargetNotice(Integer noticeId){
        Notice notice=noticeService.getById(noticeId);
        //notice.setCreatetime(new Date());
        //User user=(User) SecurityUtils.getSubject().getPrincipal();
        //notice.setOpername(user.getUsername());
        //noticeService.save(notice);
        return R.ok().put("notice",notice);
    }

}
