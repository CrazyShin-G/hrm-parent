package com.erocraft.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.erocraft.domain.Sso;
import com.erocraft.mapper.SsoMapper;
import com.erocraft.service.ISsoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员登录账号 服务实现类
 * </p>
 *
 * @author yhptest
 * @since 2020-02-27
 */
@Service
public class SsoServiceImpl extends ServiceImpl<SsoMapper, Sso> implements ISsoService {

}
