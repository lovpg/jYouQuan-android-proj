package com.lbsm.kdang.db;


import com.frame.db.annotation.Id;
import com.frame.db.annotation.NoAutoIncrement;
import com.frame.db.exception.DbException;
import com.google.gson.Gson;
import com.lbsm.kdang.app.KDangApplication;
import com.lbsm.kdang.entity.Post;
import com.lbsm.kdang.entity.SimpleUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/27.
 */
public class PostDb {

    @NoAutoIncrement
    @Id
    public String postId;
    public String location;
    public String address;
    public String content;
    public String region;
    public long posttime;
    public String imageList;
    public long userId;
    public int goodCount;
    public int commentCount;
    public boolean good;
    public long uid;
    public long barId;
    public int top;
    public String tags;
    public String remark;
    public String goodIdList;


    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getPosttime() {
        return posttime;
    }

    public void setPosttime(long posttime) {
        this.posttime = posttime;
    }

    public String getImageList() {
        return imageList;
    }

    public void setImageList(String imageList) {
        this.imageList = imageList;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getBarId() {
        return barId;
    }

    public void setBarId(long barId) {
        this.barId = barId;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGoodIdList() {
        return goodIdList;
    }

    public void setGoodIdList(String goodIdList) {
        this.goodIdList = goodIdList;
    }

    public static PostDb toPostDb(Post post){
        PostDb postDb = new PostDb();
        postDb.setAddress(post.getAddress());
        if(post.getBar() != null){
            postDb.setBarId(post.getBar().getBid());
            BarDb.insert(post.getBar());
        }

        postDb.setCommentCount(post.getCommentCount());
        postDb.setContent(post.getContent());
        postDb.setGood(post.isGood());
        postDb.setGoodCount(post.getGoodCount());
        postDb.setGoodIdList(DbUtil.SimpleString(post.getGoodList()));
        SimpleUser.insertList(post.getGoodList());
        postDb.setImageList(DbUtil.ListString(post.getImageList()));
        postDb.setLocation(post.getLocation());
        postDb.setPostId(post.getPostId());
        postDb.setPosttime(post.getPosttime());
        postDb.setRegion(post.getRegion());
        postDb.setRemark(new Gson().toJson(post.getRemark()));
        postDb.setTags(post.getTags());
        postDb.setTop(post.getTop());
        postDb.setUid(post.getUid());
        postDb.setUserId(post.getUser().getUid());
        SimpleUser.insert(post.getUser());
        return  postDb;
    }


    public static void insert(Post post){
        try{
            KDangApplication.getInstance().dbHelper.insert(toPostDb(post));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void insertList(final List<Post> posts) {
        new Thread(){
            @Override
            public void run() {
                for (Post p : posts) {
                    insert(p);
                }
            }
        }.start();

    }


    public static void insertPageList(final List<Post> posts, final String url) {
        new Thread(){
            @Override
            public void run() {
                String objectIds = "";
                for (Post p : posts) {
                    insert(p);
                    objectIds += p.getPostId() + ";";
                }
                PageDb pageDb = new PageDb();
                pageDb.setUrl(url);
                pageDb.setObjectId(objectIds);
                PageDb.insert(pageDb);
            }
        }.start();
    }


    public static PostDb query(String postId){
        PostDb postDb = null;
        try{
            postDb = (PostDb) KDangApplication.getInstance().dbHelper.query(PostDb.class,"postId",postId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return postDb;
    }


    public static List<PostDb> queryList(List<String> shareIds){
        List<PostDb> postDbs = new ArrayList<PostDb>();
        for (String str : shareIds){
            PostDb postDb = query(str);
            if(postDb!= null)postDbs.add(postDb);
        }
        return postDbs;
    }

    public static void delete(String postId){
        try {
            KDangApplication.getInstance().dbHelper.delete(PostDb.class,"postId",postId);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
