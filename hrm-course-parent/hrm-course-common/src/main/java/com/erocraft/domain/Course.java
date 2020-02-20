package com.erocraft.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 1417
 * @since 2020-02-20
 */
@TableName("t_course")
public class Course extends Model<Course> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private String users;
    @TableField("course_type_id")
    private Long courseTypeId;
    @TableField(exist = false)
    private CourseType courseType;
    public CourseType getCourseType() {
        return courseType;
    }
    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }
    private String gradeName;
    private Long grade;
    private Integer status;
    @TableField("tenant_id")
    private Long tenantId;
    private String tenantName;
    @TableField("user_id")
    private Long userId;
    private String userName;
    @TableField("start_time")
    private Date startTime;
    @TableField("end_time")
    private Date endTime;
    private String pic;
    @TableField(exist = false)
    private CourseDetail courseDetail;
    @TableField(exist = false)
    private CourseMarket courseMarket;

    public CourseDetail getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(CourseDetail courseDetail) {
        this.courseDetail = courseDetail;
    }

    public CourseMarket getCourseMarket() {
        return courseMarket;
    }

    public void setCourseMarket(CourseMarket courseMarket) {
        this.courseMarket = courseMarket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Course{" +
        ", id=" + id +
        ", name=" + name +
        ", users=" + users +
        ", courseTypeId=" + courseTypeId +
        ", gradeName=" + gradeName +
        ", grade=" + grade +
        ", status=" + status +
        ", tenantId=" + tenantId +
        ", tenantName=" + tenantName +
        ", userId=" + userId +
        ", userName=" + userName +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", pic=" + pic +
        "}";
    }
}
