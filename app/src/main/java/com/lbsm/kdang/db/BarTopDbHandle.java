package com.lbsm.kdang.db;


import com.frame.db.DBHelper;
import com.frame.db.exception.DbException;
import com.frame.db.sqlite.Selector;
import com.lbsm.kdang.app.KDangApplication;
import com.lbsm.kdang.entity.BarTop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/22.
 */
public class BarTopDbHandle {
    private static BarTopDbHandle topDbHandle;
    private DBHelper dbHelper = KDangApplication.getInstance().dbHelper;

    public static BarTopDbHandle getInstance() {
        return new BarTopDbHandle();
    }

    public void insert(long bid) {
//        dbHelper.insert(barTop, BarTop.class, " where currTime = " + barTop.getCurrTime());
        try {
            BarTop barTop = new BarTop(bid);
            dbHelper.insert(barTop);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void delete(long bid) {
        BarTop barTop = new BarTop(bid);
//        dbHelper.delete(BarTop.class, " bid = " + barTop.getBid() + " and uid = " + barTop.getUid(), null);
        try {
            dbHelper.delete(BarTop.class,"bid", String.valueOf(barTop.getBid()));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public boolean query(long bid) {
        BarTop barTop = null;
        try {
            barTop = dbHelper.getDb().findFirst(Selector.from(BarTop.class).
                    where("bid","=", String.valueOf(bid)).and("uid","=",
                    String.valueOf(KDangApplication.getInstance().getAccount().getUid())));
        } catch (DbException e) {
            e.printStackTrace();
        }
        return barTop != null;
    }

    public List<BarTop> queryBarTopList() {
        List<BarTop> list = null;
        try {
            list = dbHelper.getDb().findAll(Selector.from(BarTop.class).where("uid","=", KDangApplication.getInstance().getAccount().getUid()));
            if(list == null){
                list = new ArrayList<BarTop>();
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }
}
