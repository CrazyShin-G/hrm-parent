package com.erocraft.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erocraft.domain.Meal;
import com.erocraft.mapper.MealMapper;
import com.erocraft.service.IMealService;
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
public class MealServiceImpl extends ServiceImpl<MealMapper, Meal> implements IMealService {

}
