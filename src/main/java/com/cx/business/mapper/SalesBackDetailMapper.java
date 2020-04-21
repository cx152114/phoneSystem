package com.cx.business.mapper;

import com.cx.business.beans.PreturnDetail;
import com.cx.business.beans.SalesBackDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cx
 * @since 2020-03-22
 */
public interface SalesBackDetailMapper extends BaseMapper<SalesBackDetail> {

    List<SalesBackDetail> selectTargetSalesBackDetailBySboId(Integer sboId);

}
