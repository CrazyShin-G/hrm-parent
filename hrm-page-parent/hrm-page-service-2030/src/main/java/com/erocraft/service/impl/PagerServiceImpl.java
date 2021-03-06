package com.erocraft.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erocraft.domain.Pager;
import com.erocraft.mapper.PagerMapper;
import com.erocraft.service.IPagerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 1417
 * @since 2020-02-24
 */
@Service
public class PagerServiceImpl extends ServiceImpl<PagerMapper, Pager> implements IPagerService {

}
