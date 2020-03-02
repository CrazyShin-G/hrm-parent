package com.erocraft.service;


import com.baomidou.mybatisplus.service.IService;
import com.erocraft.domain.Sso;
import com.erocraft.util.AjaxResult;

import java.util.Map;

/**
 * <p>
 * 会员登录账号 服务类
 * </p>
 *
 * @author 1417
 * @since 2020-02-27
 */
public interface ISsoService extends IService<Sso> {

    AjaxResult register(Map<String, String> params);

    AjaxResult login(Sso sso);

    Sso querySso(String accessToken);
}
