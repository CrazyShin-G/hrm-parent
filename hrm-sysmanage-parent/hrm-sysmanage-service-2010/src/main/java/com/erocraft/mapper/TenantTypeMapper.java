package com.erocraft.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.TenantType;
import com.erocraft.query.TenantTypeQuery;

import java.util.List;

/**
 * <p>
 * 租户(机构)类型表 Mapper 接口
 * </p>
 *
 * @author 1417
 * @since 2020-02-14
 */
public interface TenantTypeMapper extends BaseMapper<TenantType> {

    /**
     *
     * @param page
     * @param query
     * @return
     */
    List<TenantType> loadPageLiset(Page<TenantType> page, TenantTypeQuery query);
}
