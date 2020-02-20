package com.erocraft.domain;

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
 * @since 2020-02-20
 */
@TableName("t_course_detail")
public class CourseDetail extends Model<CourseDetail> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private String intro;
    @TableField("course_id")
    private Long courseId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CourseDetail{" +
        ", id=" + id +
        ", description=" + description +
        ", intro=" + intro +
        ", courseId=" + courseId +
        "}";
    }
}
