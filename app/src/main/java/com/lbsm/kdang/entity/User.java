package com.lbsm.kdang.entity;

import com.google.gson.Gson;
import com.lbsm.kdang.db.UserDb;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private long uid;
    private String nickname;
    private String gender;
    private String sign;
    private String cover;
    private String avatar;
    private String region;
    private String location;
    private String distanceText;

    private String speaking;
    private String learning;
    private boolean dark;// 是否被关小黑屋
    private String interests;// 兴趣爱好
    private String voice;

    private Share share;
    /**是否是好友关系**/
    private boolean follow;
    /****/
    private boolean fans;
    private String email;
    private String birth;
    private float qtlevel;
    private int fansCount;
    private int followCount;
    private List<Bar> bars;
    private boolean isLive = false;
    private boolean isBanSpeak = false;
    /**
     * 增加 roomBlock
     * 用户在聊天室 是否被禁言
     */
    private boolean roomBlock;

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getBirth() {
        return birth;
    }

    private SimpleUser user;


    public SimpleUser toSimpleUser() {
        if (user != null) {
            return user;
        }
        this.user = new SimpleUser();
        user.setAvatar(avatar);
        user.setRegion(region);
        user.setDistanceText(distanceText);
        user.setGender(gender);
        user.setLocation(location);
        user.setNickname(nickname);
        user.setSign(sign);
        user.setUid(uid);
        user.setQtlevel(qtlevel);
        return user;
    }






    public static User toUser(SimpleUser simpleUser) {
        User user = new User();
        user.setAvatar(simpleUser.getAvatar());
        user.setVoice(simpleUser.getVoice());
        simpleUser.setGender(simpleUser.getGender());
        user.setNickname(simpleUser.getNickname());
        user.setRegion(simpleUser.getRegion());
        user.setLocation(simpleUser.getLocation());
        user.setSign(simpleUser.getSign());
        user.setDistanceText(simpleUser.getDistanceText());
        user.setQtlevel(simpleUser.getQtlevel());
        user.setUid(simpleUser.getUid());
        user.setLearning(simpleUser.getLearning());
        user.setSpeaking(simpleUser.getSpeaking());
        user.setFansCount(simpleUser.getFansCount());
        user.setFollowCount(simpleUser.getFollowCount());
        return user;
    }

    public static User toUser(UserDb userDb) {
        // TODO Auto-generated method stub
        try {
            User user = new User();
            user.setAvatar(userDb.getAvatar());
            user.setCover(userDb.getCover());
            user.setDark(userDb.isDark());
            user.setDistanceText(userDb.getDistanceText());
            user.setGender(userDb.getGender());
            user.setInterests(userDb.getInterests());
            user.setLearning(userDb.getLearning());
            user.setLocation(userDb.getLocation());
            user.setNickname(userDb.getNickname());
            user.setRegion(userDb.getRegion());
            user.setShare(new Gson().fromJson(userDb.getShareId(),Share.class));
            user.setSign(userDb.getSign());
            user.setSpeaking(userDb.getSpeaking());
            user.setUid(userDb.getUid());
            user.setVoice(userDb.getVoice());
            user.setFollowCount(userDb.getFollowCount());
            user.setFansCount(userDb.getFansCount());
            return user;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String getSpeaking() {
        return speaking;
    }

    public void setSpeaking(String speaking) {
        this.speaking = speaking;
    }

    public String getLearning() {
        return learning;
    }

    public void setLearning(String learning) {
        this.learning = learning;
    }

    public boolean isDark() {
        return dark;
    }

    public void setDark(boolean dark) {
        this.dark = dark;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
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

    public SimpleUser getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public float getQtlevel() {
        return qtlevel;
    }


    public void setQtlevel(float qtlevel) {
        this.qtlevel = qtlevel;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getFollowCount() {
        return followCount;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    public List<Bar> getBars() {
        return bars;
    }

    public void setBars(List<Bar> bars) {
        this.bars = bars;
    }

    public boolean isRoomBlock() {
        return roomBlock;
    }

    public void setRoomBlock(boolean roomBlock) {
        this.roomBlock = roomBlock;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setIsLive(boolean isLive) {
        this.isLive = isLive;
    }

    public boolean isBanSpeak() {
        return isBanSpeak;
    }

    public void setIsBanSpeak(boolean isBanSpeak) {
        this.isBanSpeak = isBanSpeak;
    }
}
