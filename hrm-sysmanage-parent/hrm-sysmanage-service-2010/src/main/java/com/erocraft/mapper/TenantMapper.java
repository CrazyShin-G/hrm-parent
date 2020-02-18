package com.erocraft.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.erocraft.domain.Tenant;
import com.erocraft.query.TenantQuery;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 1417
 * @since 2020-02-15
 */
public interface TenantMapper extends BaseMapper<Tenant> {

    /**
     *
     * @param page
     * @param query
     * @return
     */
    List<Tenant> loadPageList(Page<Tenant> page, TenantQuery query);

    /**
     * 删除中间表
     * @param id
     */
    void deleteTenantMeal(Serializable id);
}
