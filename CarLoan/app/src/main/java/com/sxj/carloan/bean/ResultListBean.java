package com.sxj.carloan.bean;

import java.util.List;

/**
 * Created by admin on 2017/9/11.
 */

public class ResultListBean<T> {

    private  int total ;

    private List<T> rows ;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
