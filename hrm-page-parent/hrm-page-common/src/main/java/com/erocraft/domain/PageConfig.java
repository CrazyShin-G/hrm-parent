package com.erocraft.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 1417
 * @since 2020-02-24
 */
@TableName("t_page_config")
public class PageConfig extends Model<PageConfig> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("template_url")
    private String templateUrl;
    private String templateName;
    @TableField("data_key")
    private String dataKey;
    private String physicalPath;
    @TableField("dfs_type")
    private Long dfsType;
    @TableField("page_url")
    private String pageUrl;
    @TableField("page_id")
    private Long pageId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getPhysicalPath() {
        return physicalPath;
    }

    public void setPhysicalPath(String physicalPath) {
        this.physicalPath = physicalPath;
    }

    public Long getDfsType() {
        return dfsType;
    }

    public void setDfsType(Long dfsType) {
        this.dfsType = dfsType;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PageConfig{" +
        ", id=" + id +
        ", templateUrl=" + templateUrl +
        ", templateName=" + templateName +
        ", dataKey=" + dataKey +
        ", physicalPath=" + physicalPath +
        ", dfsType=" + dfsType +
        ", pageUrl=" + pageUrl +
        ", pageId=" + pageId +
        "}";
    }
}
