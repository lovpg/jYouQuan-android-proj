/**
 *
 */
package com.lbsm.kdang.db;


import com.frame.db.annotation.Id;
import com.frame.db.annotation.NoAutoIncrement;
import com.frame.db.exception.DbException;
import com.lbsm.kdang.app.KDangApplication;
import com.lbsm.kdang.entity.Bar;
import com.lbsm.kdang.entity.SimpleUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BarDb implements Serializable {

    public String tags;
    public long posttime;
    @NoAutoIncrement
    @Id
    public long bid;
    public String barName;
    public String nickname;
    public int postCount;
    public String category;
    public String region;
    public String city;
    public int del;
    public String barAvator;
    public int memberCount;
    public long userId;
    public long cuid;
    public String subcategory;
    public boolean bar_join;


    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public boolean isBar_join() {
        return bar_join;
    }

    public void setBar_join(boolean bar_join) {
        this.bar_join = bar_join;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public long getPosttime() {
        return posttime;
    }

    public void setPosttime(long posttime) {
        this.posttime = posttime;
    }

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public String getBarAvator() {
        return barAvator;
    }

    public void setBarAvator(String barAvator) {
        this.barAvator = barAvator;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCuid() {
        return cuid;
    }

    public void setCuid(long cuid) {
        this.cuid = cuid;
    }

    public static BarDb toBarDB(Bar bar){
        BarDb barDb = new BarDb();
        barDb.setBarAvator(bar.getBarAvator());
        barDb.setBarName(bar.getBarName());
        barDb.setBid(bar.getBid());
        barDb.setCategory(bar.getCategory());
        barDb.setCity(bar.getCity());
        barDb.setCuid(bar.getCuid());
        barDb.setDel(bar.getDel());
        barDb.setMemberCount(bar.getMemberCount());
        barDb.setNickname(bar.getNickname());
        barDb.setPostCount(bar.getPostCount());
        barDb.setPosttime(bar.getPosttime());
        barDb.setRegion(bar.getRegion());
        barDb.setTags(bar.getTags());
        barDb.setUserId(bar.getUser().getUid());
        barDb.setSubcategory(bar.getSubcategory());
        barDb.setBar_join(bar.isJoin());
        SimpleUser.insert(bar.getUser());
      return barDb;
    }
    public static void insert(Bar bar){
        try {
            KDangApplication.getInstance().dbHelper.insert(toBarDB(bar));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    public static void insertList(final List<Bar> bars){
        new Thread(){
            @Override
            public void run() {
                for (Bar bar : bars) {
                    insert(bar);
                }
            }
        }.start();

    }


    public static void insertPageList(final List<Bar> bars, final String url){
        new Thread(){
            @Override
            public void run() {
                String objectIds = "";
                for (Bar bar : bars) {
                    insert(bar);
                    objectIds += bar.getBid() + ";";
                }
                PageDb pageDb = new PageDb();
                pageDb.setUrl(url);
                pageDb.setObjectId(objectIds);
                PageDb.insert(pageDb);
            }
        }.start();

    }

    public static BarDb query(long barId){
        BarDb barDb = null;
        try {
            barDb = (BarDb) KDangApplication.getInstance().dbHelper.query(BarDb.class,"bid", String.valueOf(barId));
        } catch (DbException e) {
            e.printStackTrace();
        }
        return barDb;
    }


    public static List<BarDb> queryList(List<String> barIds){
        List<BarDb> barDbs = new ArrayList<BarDb>();
        for (String str : barIds){
            BarDb barDb = query(Long.valueOf(str));
            if(barDbs!= null)barDbs.add(barDb);
        }
        return barDbs;
    }
}
