package com.erocraft.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erocraft.domain.TenantType;
import com.erocraft.mapper.TenantTypeMapper;
import com.erocraft.query.TenantTypeQuery;
import com.erocraft.service.ITenantTypeService;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 租户(机构)类型表 服务实现类
 * </p>
 *
 * @author 1417
 * @since 2020-02-14
 */
@Service
public class TenantTypeServiceImpl extends ServiceImpl<TenantTypeMapper, TenantType> implements ITenantTypeService {

    @Autowired
    private TenantTypeMapper tenantTypeMapper;
    @Override
    public PageList<TenantType> selectPageLiset(TenantTypeQuery query) {
        Page<TenantType> page = new Page<TenantType>(query.getPage(),query.getRows());
        List<TenantType> tenantTypes = tenantTypeMapper.loadPageLiset(page, query);
        return new PageList<TenantType>(page.getTotal(),tenantTypes);
}
}
