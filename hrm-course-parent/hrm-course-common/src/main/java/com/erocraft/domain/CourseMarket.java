package com.erocraft.domain;

import java.util.Date;
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
@TableName("t_course_market")
public class CourseMarket extends Model<CourseMarket> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long charge;
    private Long valid;
    private Date expires;
    private String qq;
    private Float price;
    @TableField("price_old")
    private Float priceOld;
    @TableField("course_id")
    private Long courseId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCharge() {
        return charge;
    }

    public void setCharge(Long charge) {
        this.charge = charge;
    }

    public Long getValid() {
        return valid;
    }

    public void setValid(Long valid) {
        this.valid = valid;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(Float priceOld) {
        this.priceOld = priceOld;
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
        return "CourseMarket{" +
        ", id=" + id +
        ", charge=" + charge +
        ", valid=" + valid +
        ", expires=" + expires +
        ", qq=" + qq +
        ", price=" + price +
        ", priceOld=" + priceOld +
        ", courseId=" + courseId +
        "}";
    }
}
