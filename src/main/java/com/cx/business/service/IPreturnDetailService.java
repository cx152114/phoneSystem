package com.cx.business.service;

import com.cx.business.beans.PreturnDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.business.beans.SorderDetail;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cx
 * @since 2020-03-20
 */
public interface IPreturnDetailService extends IService<PreturnDetail> {

    List<PreturnDetail> listTargetPreturnDetailByProId(Integer proId);

}
