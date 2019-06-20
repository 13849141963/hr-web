package com.fescotech.apps.entryonline.entity.vo;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by cy on 2018/2/11.
 */
public class PageRequest {

    private Integer page;

    private Integer rows;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
