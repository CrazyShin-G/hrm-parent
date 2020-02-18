package com.erocraft.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erocraft.domain.Employee;
import com.erocraft.domain.Tenant;
import com.erocraft.mapper.EmployeeMapper;
import com.erocraft.mapper.TenantMapper;
import com.erocraft.query.TenantQuery;
import com.erocraft.service.ITenantService;
import com.erocraft.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 1417
 * @since 2020-02-15
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {

    @Autowired
    private TenantMapper tenantMapper;

    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public PageList<Tenant> selectPageList(TenantQuery query) {
        Page<Tenant> page = new Page<>(query.getPage(),query.getRows());
        List<Tenant> tenants = tenantMapper.loadPageList(page,query);
        return new PageList<Tenant>(page.getTotal(),tenants);
    }

    @Override
    public boolean deleteById(Serializable id) {
        tenantMapper.deleteById(id);
        tenantMapper.deleteTenantMeal(id);
        return true;
    }

    @Override
    public boolean insert(Tenant entity) {

        Employee admin = entity.getAdmin();
        admin.setInputTime(new Date());
        admin.setState(0);
        admin.setType(1);
        employeeMapper.insert(admin);
        System.out.println(admin.getId());

        entity.setAdminId(admin.getId());
        tenantMapper.insert(entity);

        System.out.println(entity.getId());
        admin.setTenantId(entity.getId());
        employeeMapper.updateById(admin);

        return true;
    }


    @Override
    public boolean updateById(Tenant entity) {
        return super.updateById(entity);
    }
}
