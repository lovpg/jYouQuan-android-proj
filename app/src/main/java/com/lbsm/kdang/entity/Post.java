package com.lbsm.kdang.entity;

import java.util.List;

/**
 * date: 2016/10/28.
 */

public class Post {

    /**
     * shareId : b1590413ac7c4423bc5dd33cbc8a3f30
     * lat : 0
     * lng : 0
     * applyCount : 0
     * applyList : null
     * distance : 0
     * address : 发送位置
     * content : 叫你摆车别那么大胆，现在好了，请两台吊车
     * region :
     * posttime : 1476981522000
     * imageList : ["http://app.lbslm.com/file/share/c60af21835b04dfdbaafc1b54bcdd5c5.jpg",678.jpg",...]
     * goodList : []
     * enrollList : []
     * user :
     * {"uid":8895128,"nickname":"8895128","username":"18627606632","sign":"",
     * "avatar":"http://app.lbslm.com/file/user/avatar/7189669e13cf4d88955002f363c21460.jpg",
     * "voice":null,"gender":"","region":"","location":null,"distanceText":null,
     * "email":"","goodCount":0,"age":46,"qtlevel":0,"learning":null,
     * "speaking":null,"followCount":2,"fansCount":3,"hide":0
     * }
     * goodCount : 1
     * commentCount : 3
     * good : false
     * uid : 8895128
     * favorite : false
     * cposttime : 7 days ago
     * donationCount : 0
     * type : null
     * ext : null
     * enrollCount : 0
     * tags : 泵车
     * vedioUrl :
     * top : false
     * collection : null
     */

    private String shareId;
    private double lat;
    private double lng;
    private int applyCount;
    private List<SimpleUser> applyList;
    private double distance;
    private String address;
    private String content;
    private String region;
    private long posttime;
    private List<String> imageList;
    private List<SimpleUser> goodList;
    private List<SimpleUser> enrollList;
    private SimpleUser user;
    private int goodCount;
    private int commentCount;
    private boolean good;
    private long uid;
    private boolean favorite;
    private String cposttime;
    private int donationCount;
    private String type;
    private String ext;
    private int enrollCount;
    private String tags;
    private String vedioUrl;
    private boolean top;
//    private String collection;

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(int applyCount) {
        this.applyCount = applyCount;
    }

    public List<SimpleUser> getApplyList() {
        return applyList;
    }

    public void setApplyList(List<SimpleUser> applyList) {
        this.applyList = applyList;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
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

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public List<SimpleUser> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<SimpleUser> goodList) {
        this.goodList = goodList;
    }

    public List<SimpleUser> getEnrollList() {
        return enrollList;
    }

    public void setEnrollList(List<SimpleUser> enrollList) {
        this.enrollList = enrollList;
    }

    public SimpleUser getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getCposttime() {
        return cposttime;
    }

    public void setCposttime(String cposttime) {
        this.cposttime = cposttime;
    }

    public int getDonationCount() {
        return donationCount;
    }

    public void setDonationCount(int donationCount) {
        this.donationCount = donationCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public int getEnrollCount() {
        return enrollCount;
    }

    public void setEnrollCount(int enrollCount) {
        this.enrollCount = enrollCount;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getVedioUrl() {
        return vedioUrl;
    }

    public void setVedioUrl(String vedioUrl) {
        this.vedioUrl = vedioUrl;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }
//
//    public String getCollection() {
//        return collection;
//    }
//
//    public void setCollection(String collection) {
//        this.collection = collection;
//    }

   /* 没有做 Post 缓存*/
//    public static Post toPost(String postId){
//        return Post.toPost(PostDb.query(postId));
//    }
//
//    public static List<Post> toPostList(List<PostDb>postDbs){
//        List<Post> posts = new ArrayList<Post>();
//        for (PostDb postDb : postDbs){
//            posts.add(toPost(postDb));
//        }
//        return posts;
//    }
//
//    public static List<Post> pageShareList(String url){
//        String objects = PageDb.query(url);
//        List<PostDb> postDbs = null;
//        if(!TextUtils.isEmpty(objects)){
//            postDbs = PostDb.queryList(DbUtil.StringList(objects));
//        }
//        return  postDbs == null ? null : toPostList(postDbs);
//    }

    @Override
    public String toString() {
        return "Post{" +
                "shareId='" + shareId + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", applyCount=" + applyCount +
                ", applyList=" + applyList +
                ", distance=" + distance +
                ", address='" + address + '\'' +
                ", content='" + content + '\'' +
                ", region='" + region + '\'' +
                ", posttime=" + posttime +
                ", imageList=" + imageList +
                ", goodList=" + goodList +
                ", enrollList=" + enrollList +
                ", user=" + user +
                ", goodCount=" + goodCount +
                ", commentCount=" + commentCount +
                ", good=" + good +
                ", uid=" + uid +
                ", favorite=" + favorite +
                ", cposttime='" + cposttime + '\'' +
                ", donationCount=" + donationCount +
                ", type='" + type + '\'' +
                ", ext='" + ext + '\'' +
                ", enrollCount=" + enrollCount +
                ", tags='" + tags + '\'' +
                ", vedioUrl='" + vedioUrl + '\'' +
                ", top=" + top +
                '}';
    }
}

