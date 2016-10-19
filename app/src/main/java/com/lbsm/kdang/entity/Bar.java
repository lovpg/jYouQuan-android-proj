/**
 *
 */
package com.lbsm.kdang.entity;


import android.text.TextUtils;

import com.lbsm.kdang.app.KDangApplication;
import com.lbsm.kdang.db.BarDb;
import com.lbsm.kdang.db.BarTopDbHandle;
import com.lbsm.kdang.db.DbUtil;
import com.lbsm.kdang.db.PageDb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Reco
 */
public class Bar implements Serializable {
    public Long sortTime;
    private String tags;
    private long posttime;
    private long bid;
    private String barName;
    private String nickname;
    private int postCount;
    private String category;
    private String region;
    private String city;
    private int del;
    private String barAvator;
    private int memberCount;
    private SimpleUser user;
    private long cuid;
    private String subcategory;
    private boolean join;

    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
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
    public int getDel() {
        return del;
    }
    public void setDel(int del) {
        this.del = del;
    }
    public String getBarAvator() {
        return barAvator;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setBarAvator(String url)
    {
        this.barAvator = url;

    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public int getMemberCount() {
        return memberCount;
    }
    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public SimpleUser getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

    public long getPosttime() {
        return posttime;
    }

    public void setPosttime(long posttime) {
        this.posttime = posttime;
    }

    public long getCuid() {
        return cuid;
    }

    public void setCuid(long cuid) {
        this.cuid = cuid;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public Long getSortTime() {
        return sortTime;
    }

    public void setSortTime(Long sortTime) {
        this.sortTime = sortTime;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public boolean isJoin(){
        return join;
    }

    public void setJoin(boolean join) {
        this.join = join;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "sortTime=" + sortTime +
                ", tags='" + tags + '\'' +
                ", posttime=" + posttime +
                ", bid=" + bid +
                ", barName='" + barName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", postCount=" + postCount +
                ", category='" + category + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", del=" + del +
                ", barAvator='" + barAvator + '\'' +
                ", memberCount=" + memberCount +
                ", user=" + user +
                ", cuid=" + cuid +
                '}';
    }

    public static List<Bar> sortBar(List<Bar> bars){
        List<BarTop> bts = BarTopDbHandle.getInstance().queryBarTopList();
        for(Bar bar : bars){
            bar.sortTime = -1L;
            for(BarTop bt : bts){
                if(bar.getBid() == bt.getBid()) {
                    bar.sortTime = bt.getCurrTime();
                    break;
                }
            }
        }
        Collections.sort(bars, new Comparator<Bar>() {
            @Override
            public int compare(Bar lhs, Bar rhs) {
                return -lhs.sortTime.compareTo(rhs.sortTime);
            }
        });
        return bars;
    }

    public static boolean isUidNull(Bar bar){
       boolean isUid = false;
        long uid = KDangApplication.getInstance().getUser().getUid();
        if(bar.getCuid() > 0) isUid = uid == bar.getCuid();
        else if(bar.getUser() != null) isUid = uid == bar.getUser().getUid();
        return  isUid;
    }

    public static Bar toBar(BarDb barDb){
        Bar bar = new Bar();
        bar.setBarAvator(barDb.getBarAvator());
        bar.setBarName(barDb.getBarName());
        bar.setBid(barDb.getBid());
        bar.setCategory(barDb.getCategory());
        bar.setCity(barDb.getCity());
        bar.setCuid(barDb.getCuid());
        bar.setDel(barDb.getDel());
        bar.setMemberCount(barDb.getMemberCount());
        bar.setNickname(barDb.getNickname());
        bar.setPostCount(barDb.getPostCount());
        bar.setPosttime(barDb.getPosttime());
        bar.setRegion(barDb.getRegion());
        bar.setTags(barDb.getTags());
        bar.setUser(SimpleUser.query(barDb.getUserId()));
        bar.setSubcategory(barDb.getSubcategory());
        bar.setJoin(barDb.isBar_join());
        return bar;
    }


    public static Bar toBar(long barId){
        BarDb barDb = BarDb.query(barId);
        return barDb == null ? null : toBar(barDb);
    }



    public static List<Bar> toBarList(List<BarDb> barDbs){
        List<Bar> posts = new ArrayList<Bar>();
        for (BarDb barDb : barDbs){
            posts.add(toBar(barDb));
        }
        return posts;
    }


    public static List<Bar> pageShareList(String url){
        String objects = PageDb.query(url);
        List<BarDb> barDbs = null;
        if(TextUtils.isEmpty(objects)){
            barDbs = BarDb.queryList(DbUtil.StringList(objects));
        }
        return  barDbs == null ? null : toBarList(barDbs);
    }
}
