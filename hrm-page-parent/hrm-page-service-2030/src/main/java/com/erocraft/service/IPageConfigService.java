package com.erocraft.service;


import com.baomidou.mybatisplus.service.IService;
import com.erocraft.domain.PageConfig;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 1417
 * @since 2020-02-24
 */
public interface IPageConfigService extends IService<PageConfig> {

    /**
     * 做页面静态化
     * @param pageName
     * @param dataKey
     */
    void staticPage(String pageName, String dataKey) throws IOException;
}
