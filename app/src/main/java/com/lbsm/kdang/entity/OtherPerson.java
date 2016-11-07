package com.lbsm.kdang.entity;

import java.util.List;

/**
 * date: 2016/11/2.
 */

public class OtherPerson {

    /**
     * uid : 4462018
     * nickname : 天道酬勤
     * sign :
     * avatar : http://app.lbslm.com/file/user/avatar/f0d59990b6eb469291d80f64233ac65e.jpg
     * cover : null
     * imageList : []
     * email :
     * mobile :
     * region :
     * speaking : null
     * gender :
     * learning : null
     * birth : 1970-01-01
     * voice : null
     * goodList : null
     * interests :
     * qtlevel : 0
     * location : 0.0,0.0
     * distanceText : null
     * follow : true
     * fans : false
     * followCount : 4
     * fansCount : 5
     * nativeLanguage : null
     */

    private long uid;
    private String nickname;
    private String sign;
    private String avatar;
    private String cover;
    private String email;
    private String mobile;
    private String region;
    private String speaking;
    private String gender;
    private String learning;
    private String birth;
    private String voice;
    private String goodList;
    private String interests;
    private int qtlevel;
    private String location;
    private String distanceText;
    private boolean follow;
    private boolean fans;
    private String followCount;
    private String fansCount;
    private String nativeLanguage;
    private List<String> imageList;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSpeaking() {
        return speaking;
    }

    public void setSpeaking(String speaking) {
        this.speaking = speaking;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLearning() {
        return learning;
    }

    public void setLearning(String learning) {
        this.learning = learning;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getGoodList() {
        return goodList;
    }

    public void setGoodList(String goodList) {
        this.goodList = goodList;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public int getQtlevel() {
        return qtlevel;
    }

    public void setQtlevel(int qtlevel) {
        this.qtlevel = qtlevel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDistanceText() {
        return distanceText;
    }

    public void setDistanceText(String distanceText) {
        this.distanceText = distanceText;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }

    public boolean isFans() {
        return fans;
    }

    public void setFans(boolean fans) {
        this.fans = fans;
    }

    public String getFollowCount() {
        return followCount;
    }

    public void setFollowCount(String followCount) {
        this.followCount = followCount;
    }

    public String getFansCount() {
        return fansCount;
    }

    public void setFansCount(String fansCount) {
        this.fansCount = fansCount;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public void setNativeLanguage(String nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    @Override
    public String toString() {
        return "OtherPerson{" +
                "avatar='" + avatar + '\'' +
                ", uid=" + uid +
                ", nickname='" + nickname + '\'' +
                ", sign='" + sign + '\'' +
                ", cover='" + cover + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", region='" + region + '\'' +
                ", speaking='" + speaking + '\'' +
                ", gender='" + gender + '\'' +
                ", learning='" + learning + '\'' +
                ", birth='" + birth + '\'' +
                ", voice='" + voice + '\'' +
                ", goodList='" + goodList + '\'' +
                ", interests='" + interests + '\'' +
                ", qtlevel=" + qtlevel +
                ", location='" + location + '\'' +
                ", distanceText='" + distanceText + '\'' +
                ", follow=" + follow +
                ", fans=" + fans +
                ", followCount='" + followCount + '\'' +
                ", fansCount='" + fansCount + '\'' +
                ", nativeLanguage='" + nativeLanguage + '\'' +
                ", imageList=" + imageList +
                '}';
    }
}
