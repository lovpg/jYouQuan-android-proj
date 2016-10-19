package com.lbsm.kdang.entity;


import com.frame.db.annotation.Id;
import com.frame.db.annotation.NoAutoIncrement;
import com.lbsm.kdang.app.KDangApplication;

/**
 * Created by Administrator on 2015/5/22.
 */
public class BarTop {

    @NoAutoIncrement
    @Id
    public long bid;
    public long currTime;
    public long uid;

    public BarTop() {
    }

    public BarTop(long bid) {
        this(System.currentTimeMillis(), bid);
    }

    public BarTop(long currTime, long bid) {
        this.currTime = currTime;
        this.bid = bid;
        this.uid = KDangApplication.getInstance().getAccount().getUid();
    }

    public long getCurrTime() {
        return currTime;
    }

    public long getBid() {
        return bid;
    }

    public long getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return "BarTop{" +
                "currTime=" + currTime +
                ", bid=" + bid +
                ", uid=" + uid +
                '}';
    }

}
