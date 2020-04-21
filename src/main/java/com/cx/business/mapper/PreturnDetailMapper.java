package com.cx.business.mapper;

import com.cx.business.beans.PreturnDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cx
 * @since 2020-03-20
 */
public interface PreturnDetailMapper extends BaseMapper<PreturnDetail> {

    List<PreturnDetail> selectTargetPreturnDetailById(Integer proId);

}
