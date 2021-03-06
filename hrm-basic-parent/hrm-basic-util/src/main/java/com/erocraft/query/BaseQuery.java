package com.erocraft.query;



/**
 * @author 14179
 */
public class BaseQuery {

    private String keyword;
    private Integer page = 1;
    private Integer rows = 10;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        System.out.println(page);
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
