package com.erocraft.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erocraft.domain.Menu;
import com.erocraft.mapper.MenuMapper;
import com.erocraft.service.IMenuService;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
