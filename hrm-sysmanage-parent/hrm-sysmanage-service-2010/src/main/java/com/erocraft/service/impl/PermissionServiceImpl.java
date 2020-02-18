package com.erocraft.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erocraft.domain.Permission;
import com.erocraft.mapper.PermissionMapper;
import com.erocraft.service.IPermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 1417
 * @since 2020-02-15
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
