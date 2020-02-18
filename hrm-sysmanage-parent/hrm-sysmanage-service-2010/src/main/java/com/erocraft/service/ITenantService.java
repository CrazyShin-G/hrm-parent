package com.erocraft.service;


import com.baomidou.mybatisplus.service.IService;
import com.erocraft.domain.Tenant;
import com.erocraft.query.TenantQuery;
import com.erocraft.util.PageList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 1417
 * @since 2020-02-15
 */
public interface ITenantService extends IService<Tenant> {


    PageList<Tenant> selectPageList(TenantQuery query);
}
