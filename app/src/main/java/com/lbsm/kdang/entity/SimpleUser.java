package com.lbsm.kdang.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.frame.db.exception.DbException;
import com.lbsm.kdang.app.KDangApplication;
import com.lbsm.kdang.db.DbUtil;
import com.lbsm.kdang.db.PageDb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SimpleUser implements Serializable,Parcelable {

    public String avatar;
    public String voice;
    public String gender;
    public String nickname;
    public String region;
    public String location;
    public String sign;
    public String distanceText;
    private float qtlevel;

    public long uid;
    public boolean isPlay = false;

    private boolean isSelect = false;
    private boolean isAlready = false;
    private String learning;
    private String speaking;

    private int fansCount;

    public int followCount;
    public boolean roomBlock;

    public SimpleUser(long uid, String avatar, String voice, String gender, String nickname, String region, String location, String sign, String distanceText, float qtlevel) {
        this.uid = uid;
        this.avatar = avatar;
        this.voice = voice;
        this.gender = gender;
        this.nickname = nickname;
        this.region = region;
        this.location = location;
        this.sign = sign;
        this.distanceText = distanceText;
        this.qtlevel = qtlevel;
    }

    public SimpleUser(long uid, String avatar, String nickname) {
        this.uid = uid;
        this.avatar = avatar;
        this.nickname = nickname;
    }

    public SimpleUser(long uid) {
        this.uid = uid;
    }

    public SimpleUser() {
    }


    public static SimpleUser setSimpleUser(SimpleUser simpleUser) {
        SimpleUser user = new SimpleUser();
        user.setAvatar(simpleUser.getAvatar());
        user.setDistanceText(simpleUser.getDistanceText());
        user.setGender(simpleUser.getGender());
        user.setLocation(simpleUser.getLocation());
        user.setNickname(simpleUser.getNickname());
        user.setRegion(simpleUser.getRegion());
        user.setSign(simpleUser.getSign());
        user.setUid(simpleUser.getUid());
        user.setVoice(simpleUser.getVoice());
        user.setQtlevel(simpleUser.getQtlevel());
        return user;
    }

    public static SimpleUser setSimpleUser(User user) {
        if(user != null){
            SimpleUser simpleUse = new SimpleUser();
            simpleUse.setAvatar(user.getAvatar());
            simpleUse.setDistanceText(user.getDistanceText());
            simpleUse.setGender(user.getGender());
            simpleUse.setLocation(user.getLocation());
            simpleUse.setNickname(user.getNickname());
            simpleUse.setRegion(user.getRegion());
            simpleUse.setSign(user.getSign());
            simpleUse.setUid(user.getUid());
            simpleUse.setVoice(user.getVoice());
            simpleUse.setQtlevel(user.getQtlevel());
            return simpleUse;
        }
        return null;
    }




    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDistanceText() {
        return distanceText;
    }

    public void setDistanceText(String distanceText) {
        this.distanceText = distanceText;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public boolean isPlay() {
        return isPlay;
    }

    public void setPlay(boolean isPlay) {
        this.isPlay = isPlay;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public boolean equals(Object obj) {
        return ((SimpleUser) obj).getUid() == uid;
    }

    public boolean isAlready() {
        return isAlready;
    }

    public void setAlready(boolean isAlready) {
        this.isAlready = isAlready;
    }

    public float getQtlevel() {
        return qtlevel;
    }

    public void setQtlevel(float qtlevel) {
        this.qtlevel = qtlevel;
    }

    public String getLearning() {
        return learning;
    }

    public void setLearning(String learning) {
        this.learning = learning;
    }

    public String getSpeaking() {
        return speaking;
    }

    public void setSpeaking(String speaking) {
        this.speaking = speaking;
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

    public boolean isRoomBlock() {
        return roomBlock;
    }

    public void setRoomBlock(boolean roomBlock) {
        this.roomBlock = roomBlock;
    }

    @Override
    public int hashCode() {
        String in = String.valueOf(uid);
        return in.hashCode();
    }

    @Override
    public String toString() {
        return "SimpleUser{" +
                "avatar='" + avatar + '\'' +
                ", voice='" + voice + '\'' +
                ", gender='" + gender + '\'' +
                ", nickname='" + nickname + '\'' +
                ", region='" + region + '\'' +
                ", location='" + location + '\'' +
                ", sign='" + sign + '\'' +
                ", distanceText='" + distanceText + '\'' +
                ", uid=" + uid +
                ", isPlay=" + isPlay +
                ", isSelect=" + isSelect +
                ", isAlready=" + isAlready +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(uid);
        dest.writeString(avatar);
        dest.writeString(voice);
        dest.writeString(gender);
        dest.writeString(nickname);
        dest.writeString(region);
        dest.writeString(location);
        dest.writeString(sign);
        dest.writeString(distanceText);
        dest.writeFloat(qtlevel);
    }

    public static final Parcelable.Creator<SimpleUser> CREATOR = new Parcelable.Creator<SimpleUser>() {
        @Override
        public SimpleUser createFromParcel(Parcel source) {
            return new SimpleUser(source.readLong(), source.readString(), source.readString(), source.readString(), source.readString(), source.readString(), source.readString(), source.readString(), source.readString(), source.readFloat());
        }

        @Override
        public SimpleUser[] newArray(int size) {
            return new SimpleUser[size];
        }
    };
    public long getUid() {
        return uid;
    }




    public static SimpleUser query(long uid){
        SimpleUser simpleUser = null;
        try {
            simpleUser = (SimpleUser) KDangApplication.getInstance().dbHelper.query(SimpleUser.class,"uid", String.valueOf(uid));
        } catch (DbException e) {
            e.printStackTrace();
        }
        return simpleUser;
    }

    public static List<SimpleUser> queryList(List<Long> list){
        List<SimpleUser> simpleUsers = new ArrayList<SimpleUser>();
        for (Long str : list){
            simpleUsers.add(query(str));
        }
        return simpleUsers;
    }


    public static List<SimpleUser> queryStringList(List<String> list){
        List<SimpleUser> simpleUsers = null;
        if(list != null && list.size() > 0){
            simpleUsers =  new ArrayList<SimpleUser>();
            for (String str : list){
                if(TextUtils.isEmpty(str))simpleUsers.add(query(Long.valueOf(str)));
            }
        }


        return simpleUsers;
    }

    public static void insert(SimpleUser sim){
        try {
            KDangApplication.getInstance().dbHelper.insert(sim);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    public static void insertList(List<SimpleUser> simpleUsers){
        try {
            KDangApplication.getInstance().dbHelper.getDb().saveOrUpdateAll(simpleUsers);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    public static void insertPageList(List<SimpleUser> simpleUsers, String url){
        String objects = "";
        try {
            for (SimpleUser simpleUser :simpleUsers){
                objects += simpleUser.getUid()+";";
            }
            PageDb.insert(url, objects);
            KDangApplication.getInstance().dbHelper.getDb().saveOrUpdateAll(simpleUsers);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void insertPageList(List<SimpleUser> simpleUsers, String url, boolean isNext){
        String objects = "";
        try {
            for (SimpleUser simpleUser :simpleUsers){
                objects += simpleUser.getUid()+";";
            }
            if(!isNext){
                PageDb.insert(url, objects);
                KDangApplication.getInstance().dbHelper.getDb().saveOrUpdateAll(simpleUsers);
            }

        } catch (DbException e) {
            e.printStackTrace();
        }
    }





    public static List<SimpleUser> queryList(String url){
        String objects = PageDb.query(url);
        List<SimpleUser> postDbs = null;
        if(TextUtils.isEmpty(objects)){
            postDbs = queryStrList(DbUtil.StringList(objects));
        }
        return  postDbs == null ? null : postDbs;
    }




    public static List<SimpleUser> queryStrList(List<String> simpleUserIds){
        List<SimpleUser> simpleUsers = new ArrayList<SimpleUser>();
        for (String str : simpleUserIds){
            SimpleUser simpleUser = query(Long.valueOf(str));
            if(simpleUser!= null)simpleUsers.add(simpleUser);
        }
        return simpleUsers;
    }


    public static void  detele(long uid){
        try {
            KDangApplication.getInstance().dbHelper.delete(SimpleUser.class,"uid", String.valueOf(uid));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

}
