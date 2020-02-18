package com.erocraft.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.erocraft.domain.TenantType;
import com.erocraft.query.TenantTypeQuery;
import com.erocraft.util.PageList;

/**
 * <p>
 * 租户(机构)类型表 服务类
 * </p>
 *
 * @author 1417
 * @since 2020-02-14
 */
public interface ITenantTypeService extends IService<TenantType> {

    /**
     * 带条件的分页
     * @param query
     * @return
     */
    PageList<TenantType> selectPageLiset(TenantTypeQuery query);
}
