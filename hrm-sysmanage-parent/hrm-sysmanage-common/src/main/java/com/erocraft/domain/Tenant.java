package com.erocraft.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
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
 * @since 2020-02-15
 */
@TableName("t_tenant")
public class Tenant extends Model<Tenant> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("tenant_type")
    private Long tenantType;
    //数据库里面是没有关联属性，只是用来显示或接受值
    @TableField(exist = false)
    private TenantType type;
    private String companyName;
    private String companyNum;
    private Date registerTime;
    private Boolean state;
    private String address;
    private String logo;
    @TableField("admin_id")
    private Long adminId;
    //数据库里面是没有关联属性，只是用来显示或接受值
    @TableField(exist = false)
    private Employee admin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantType() {
        return tenantType;
    }

    public void setTenantType(Long tenantType) {
        this.tenantType = tenantType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(String companyNum) {
        this.companyNum = companyNum;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


    public Employee getAdmin() {
        return admin;
    }

    public void setAdmin(Employee admin) {
        this.admin = admin;
    }

    public TenantType getType() {
        return type;
    }

    public void setType(TenantType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Tenant{" +
        ", id=" + id +
        ", tenantType=" + tenantType +
        ", companyName=" + companyName +
        ", companyNum=" + companyNum +
        ", registerTime=" + registerTime +
        ", state=" + state +
        ", address=" + address +
        ", logo=" + logo +
        ", adminId=" + adminId +
        "}";
    }


}
