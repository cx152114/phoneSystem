package com.cx.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.common.model.R;
import com.cx.sys.beans.Notice;
import com.cx.sys.service.INoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
