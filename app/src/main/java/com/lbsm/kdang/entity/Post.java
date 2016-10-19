package com.lbsm.kdang.entity;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.lbsm.kdang.db.DbUtil;
import com.lbsm.kdang.db.PageDb;
import com.lbsm.kdang.db.PostDb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Post implements Serializable {
	private String postId;
	private String location;
	private String address;
	private String content;
	private String region;
	private long posttime;
	private List<String> imageList;
    private List<SimpleUser> goodList;
	private SimpleUser user;
	private int goodCount;
	private int commentCount;
	private boolean good;
	private long uid;
    private Bar bar;
    private int top;
    private String tags;
    private Remark remark;
    private int donationCount;
    private boolean favorite;
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
	public List<String> getImageList() {
		return imageList;
	}
	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
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

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public Remark getRemark() {
        return remark;
    }

    public void setRemark(Remark remark) {
        this.remark = remark;
    }

    public String getTags() {
        return tags;
    }

    public List<SimpleUser> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<SimpleUser> goodList) {
        this.goodList = goodList;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getDonationCount() {
        return donationCount;
    }

    public void setDonationCount(int donationCount) {
        this.donationCount = donationCount;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", location='" + location + '\'' +
                ", address='" + address + '\'' +
                ", content='" + content + '\'' +
                ", region='" + region + '\'' +
                ", posttime=" + posttime +
                ", imageList=" + imageList +
                ", goodList=" + goodList +
                ", user=" + user +
                ", goodCount=" + goodCount +
                ", commentCount=" + commentCount +
                ", good=" + good +
                ", uid=" + uid +
                ", bar=" + bar +
                ", top=" + top +
                ", tags='" + tags + '\'' +
                ", remark=" + remark +
                '}';
    }

    public static Post toPost(Share share){
        Post post = null;
        if(share != null){
            post = new Post();
            post.setPostId(share.getShareId());
            post.setLocation(share.getLocation());
            post.setAddress(share.getAddress());
            post.setContent(share.getContent());
            post.setPosttime(share.getPosttime());
            post.setBar(share.getBar());
            post.setImageList(share.getImageList());
            post.setUser(share.getUser());
            post.setGoodCount(share.getGoodCount());
            post.setCommentCount(share.getCommentCount());
            post.setGood(share.isGood());
            post.setUid(share.getUid());
            post.setRemark(share.getRemark());
            post.setFavorite(share.isFavorite());
            post.setDonationCount(share.getDonationCount());
        }
        return post;
    }

    public static Post toPost(PostDb postDb){
        Post post = null;
        try{
            if(postDb != null){
            post = new Post();
            post.setAddress(postDb.getAddress());
            post.setBar(Bar.toBar(postDb.getBarId()));
            post.setCommentCount(postDb.getCommentCount());
            post.setContent(postDb.getContent());
            post.setGood(postDb.isGood());
            post.setGoodCount(postDb.getGoodCount());
            post.setGoodList(SimpleUser.queryStringList(DbUtil.StringList(postDb.getGoodIdList())));
            post.setImageList(DbUtil.StringList(postDb.getImageList()));
            post.setLocation(postDb.getLocation());
            post.setPostId(postDb.getPostId());
            post.setPosttime(postDb.getPosttime());
            post.setRegion(postDb.getRegion());
            post.setRemark(new Gson().fromJson(postDb.getRemark(),Remark.class));
            post.setTags(postDb.getTags());
            post.setTop(postDb.getTop());
            post.setUid(postDb.getUid());
            post.setUser(SimpleUser.query(postDb.getUserId()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return post;
    }


    public static Post toPost(String postId){
        return Post.toPost(PostDb.query(postId));
    }

    public static List<Post> toPostList(List<PostDb> postDbs){
        List<Post> posts = new ArrayList<Post>();
        for (PostDb postDb : postDbs){
            posts.add(toPost(postDb));
        }
        return posts;
    }


    public static List<Post> pageShareList(String url){
        String objects = PageDb.query(url);
        List<PostDb> postDbs = null;
        if(TextUtils.isEmpty(objects)){
            postDbs = PostDb.queryList(DbUtil.StringList(objects));
        }
        return  postDbs == null ? null : toPostList(postDbs);
    }
}
