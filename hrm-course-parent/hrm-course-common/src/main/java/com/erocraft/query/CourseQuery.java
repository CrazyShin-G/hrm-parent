package com.erocraft.query;


/**
 *
 * @author 1417
 * @since 2020-02-20
 */
public class CourseQuery extends BaseQuery{

    private Long courseType;
    private Integer priceMin;
    private Integer priceMax;
    private String sortField;
    private String sortType;

    public Long getCourseType() {
        return courseType;
    }

    public void setCourseType(Long courseType) {
        this.courseType = courseType;
    }

    public Integer getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Integer priceMin) {
        this.priceMin = priceMin;
    }

    public Integer getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Integer priceMax) {
        this.priceMax = priceMax;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}