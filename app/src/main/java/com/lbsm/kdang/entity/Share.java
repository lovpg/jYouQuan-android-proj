package com.lbsm.kdang.entity;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.lbsm.kdang.db.DbUtil;
import com.lbsm.kdang.db.PageDb;
import com.lbsm.kdang.db.ShareDb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Share implements Serializable {

    private String shareId;
    private String location;
    private String address;
    private String content;
    private long posttime;
    private boolean good;
    private int goodCount;
    private int commentCount;
    private List<String> imageList;
    private List<SimpleUser> goodList;

    private SimpleUser user;
    private long uid;
    private Bar bar;
    private Remark remark;
    private boolean favorite;
    private int donationCount;
    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPosttime() {
        return posttime;
    }

    public void setPosttime(long posttime) {
        this.posttime = posttime;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public SimpleUser getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    public List<SimpleUser> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<SimpleUser> goodList) {
        this.goodList = goodList;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public Remark getRemark() {
        return remark;
    }

    public void setRemark(Remark remark) {
        this.remark = remark;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getDonationCount() {
        return donationCount;
    }

    public void setDonationCount(int donationCount) {
        this.donationCount = donationCount;
    }

    public boolean equals(Object obj) {
        Share s = (Share) obj;
        return String.valueOf(uid).equals(String.valueOf(s.uid));
    }

    @Override
    public int hashCode() {
        String in = String.valueOf(uid);
        return in.hashCode();
    }

    @Override
    public String toString() {
        return "Share{" +
                "shareId='" + shareId + '\'' +
                ", location='" + location + '\'' +
                ", address='" + address + '\'' +
                ", content='" + content + '\'' +
                ", posttime=" + posttime +
                ", good=" + good +
                ", goodCount=" + goodCount +
                ", commentCount=" + commentCount +
                ", imageList=" + imageList +
                ", goodList=" + goodList +
                ", user=" + user +
                ", uid=" + uid +
                ", bar=" + bar +
                ", remark=" + remark +
                '}';
    }

    public static Share toShare(ShareDb shareDb) {
        Share share = null;
        try {
            if (shareDb != null) {
                share = new Share();
                share.setUser(SimpleUser.query(shareDb.getUserId()));
                share.setImageList(DbUtil.StringList(shareDb.getImageList()));
                share.setUid(shareDb.getUid());
                share.setGoodList(SimpleUser.queryStringList(DbUtil.StringList(shareDb.getGoodIdList())));
                share.setShareId(shareDb.getShareId());
                share.setGoodCount(shareDb.getGoodCount());
                share.setPosttime(shareDb.getPosttime());
                share.setRemark(new Gson().fromJson(shareDb.getRemark(),Remark.class));
                share.setLocation(shareDb.getLocation());
                share.setContent(shareDb.getContent());
                share.setBar(Bar.toBar(shareDb.getBarId()));
                share.setAddress(shareDb.getAddress());
                share.setCommentCount(shareDb.getCommentCount());
                share.setGood(shareDb.isGood());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return share;
    }


    public static List<Share> toShareList(List<ShareDb> shareDbs){
        List<Share> shares = new ArrayList<Share>();
        for (ShareDb shareDb : shareDbs){
            shares.add(toShare(shareDb));
        }
        return shares;
    }

    public static Share toShare(String shareId){
        return TextUtils.isEmpty(shareId) ? toShare(ShareDb.query(shareId)) : null;
    }

    public static Share toShare(Post post) {
        Share share = new Share();
        share.setShareId(post.getPostId());
        share.setLocation(post.getLocation());
        share.setAddress(post.getAddress());
        share.setContent(post.getContent());
        share.setPosttime(post.getPosttime());
        share.setGood(post.isGood());
        share.setGoodCount(post.getGoodCount());
        share.setCommentCount(post.getCommentCount());
        share.setImageList(post.getImageList());
        share.setGoodList(post.getGoodList());
        share.setUser(post.getUser());
        share.setUid(post.getUid());
        share.setBar(post.getBar());
        share.setRemark(post.getRemark());
        share.setFavorite(post.isFavorite());
        share.setDonationCount(post.getDonationCount());
        return share;
    }

    public static List<Share> pageShareList(String url){
        String objects = PageDb.query(url);
        List<ShareDb> shareDbs = null;
        if(TextUtils.isEmpty(objects)){
           shareDbs = ShareDb.queryList(DbUtil.StringList(objects));
        }
        return  shareDbs == null ? null : toShareList(shareDbs);
    }

}
