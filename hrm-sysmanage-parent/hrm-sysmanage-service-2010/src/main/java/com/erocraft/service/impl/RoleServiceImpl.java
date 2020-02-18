package com.erocraft.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erocraft.domain.Role;
import com.erocraft.mapper.RoleMapper;
import com.erocraft.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
