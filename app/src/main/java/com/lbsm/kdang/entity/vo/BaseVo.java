package com.lbsm.kdang.entity.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * date: 2016/10/25.
 */

public class BaseVo<T> {
    private boolean next;
    private List<T> data=new ArrayList<>();

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
