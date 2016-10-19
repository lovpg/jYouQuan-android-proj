package com.lbsm.kdang.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.frame.db.preference.BasePreference;
import com.lbsm.kdang.entity.User;


public class UserPref extends BasePreference {

    public static final String UID = "uid";
    public static final String NICKNAME = "nickname";
    public static final String GENDER = "gender";
    public static final String SIGN = "sign";
    public static final String COVER = "cover";
    public static final String AVATAR = "avatar";
    public static final String REGION = "region";
    public static final String LOCATION = "location";
    public static final String DISTANCETEXT = "distanceText";
    public static final String SPENKING = "speaking";
    public static final String LEARNING = "learning";
    // 是否被关小黑屋
    public static final String DARK = "dark";
    // 兴趣爱好
    public static final String INTREESTS = "interests";
    public static final String VOICE = "voice";
    public static final String FRIENDS = "friends";
    public static final String EMAIL = "email";
    public static final String IS_CLICK_EMAIL = "clickEmail";
    public static final String BIRTH_DAY = "birthday";
    public static final String IS_CLICK_BIRTH_DAY = "clickBirthday";
    public static final String QTLEVEL = "qtlevel";
    public static final String FANSCOUNT = "fansCount";
    public static final String FOLLOWCOUNT = "followCount";

    public UserPref(Context context) {
        super(context);
    }

    @Override
    public SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences("user", Context.MODE_PRIVATE);
    }


    public User getUser() {
        User user = new User();
        user.setUid(getLong(UID, -1));
        user.setNickname(getString(NICKNAME));
        user.setGender(getString(GENDER));
        user.setSign(getString(SIGN));
        user.setCover(getString(COVER));
        user.setAvatar(getString(AVATAR));
        user.setRegion(getString(REGION));
        user.setLocation(getString(REGION));
        user.setDistanceText(getString(DISTANCETEXT));
        user.setSpeaking(getString(SPENKING));
        user.setLearning(getString(LEARNING));
        user.setDark(getBooleanTrue(DARK));
        user.setInterests(getString(INTREESTS));
        user.setVoice(getString(VOICE));
        user.setBirth(getString(BIRTH_DAY));
        user.setQtlevel(mPreferences.getFloat(QTLEVEL, 0));
        user.setFansCount(getInt(FANSCOUNT));
        user.setFollowCount(getInt(FOLLOWCOUNT));
        return user;
    }

    public void savaUser(User user) {
        mPreferences.edit().putLong(UID, user.getUid())
                .putString(NICKNAME, user.getNickname())
                .putString(GENDER, user.getGender())
                .putString(SIGN, user.getSign())
                .putString(COVER, user.getCover())
                .putString(AVATAR, user.getAvatar())
                .putString(REGION, user.getRegion())
                .putString(LOCATION, user.getLocation())
                .putString(DISTANCETEXT, user.getDistanceText())
                .putString(SPENKING, user.getSpeaking())
                .putString(LEARNING, user.getLearning())
                .putBoolean(DARK, user.isDark())
                .putString(INTREESTS, user.getInterests())
                .putString(VOICE, user.getVoice())
                .putString(EMAIL, user.getEmail())
                .putBoolean(IS_CLICK_EMAIL, false)
                .putString(BIRTH_DAY, user.getBirth())
                .putBoolean(IS_CLICK_BIRTH_DAY, false)
                .putFloat(QTLEVEL,user.getQtlevel())
                .putInt(FANSCOUNT,user.getFansCount())
                .putInt(FOLLOWCOUNT,user.getFollowCount())
                .commit();
    }

    public void setNickname(String nickname) {
        editString(NICKNAME, nickname);
    }

    public String getNickname() {
        return getString(NICKNAME);
    }

    public void setGender(String gender) {
        editString(GENDER, gender);
    }

    public String getGender() {
        return getString(GENDER);
    }

    public void setRegion(String region) {
        editString(REGION, region);
    }

    public String getRegion() {
        return getString(REGION);
    }

    public void setSign(String sign) {
        editString(SIGN, sign);
    }

    public String getSign() {
        return getString(SIGN);
    }

    public String getSpeaking() {
        return getString(SPENKING);
    }

    public void setSpeaking(String speaking) {
        editString(SPENKING, speaking);
    }

    public void setLearning(String learning) {
        editString(LEARNING, learning);
    }

    public String getLearning() {
        return getString(LEARNING);
    }

    public void setInterests(String interests) {
        editString(INTREESTS, interests);
    }

    public String getInterests() {
        return getString(INTREESTS);
    }

    public void setLocation(String location) {
        editString(LOCATION, location);
    }

    public String getLocation() {
        return getString(LOCATION);
    }

    public void setAvatar(String avatar) {
        editString(AVATAR, avatar);
    }

    public String getAvatar() {
        return getString(AVATAR);
    }

    public long getUid() {
        return getLong(UID);
    }

    public void setVoice(String url) {
        editString(VOICE, url);
    }

    public void setCover(String cover) {
        editString(COVER, cover);
    }

    public String getCover() {
        return getString(COVER);
    }

    public void setFriends(String friends) {
        editString(FRIENDS, friends);
    }

    public String getFriends() {
        return getString(FRIENDS);
    }

    public String getEmail() {
        return getString(EMAIL);
    }

    public void setEmail(String email) {
        editString(EMAIL, email);
    }

    public boolean isClickEmail() {
        return getBooleanFalse(IS_CLICK_EMAIL);
    }

    public void setClickEmail(boolean isClick) {
        mPreferences.edit().putBoolean(IS_CLICK_EMAIL, isClick).commit();
    }

    public boolean isClickBirthDay() {
        return getBooleanFalse(IS_CLICK_BIRTH_DAY);
    }

    public void setClickBirthDay(boolean isClick) {
        mPreferences.edit().putBoolean(IS_CLICK_BIRTH_DAY, isClick).commit();
    }

    public String getBirthDay() {
        return getString(BIRTH_DAY);
    }

    public void setBirthDay(String birthDay) {
        editString(BIRTH_DAY, birthDay);
    }

    public void setFollowCount(int followCount){
        editInt(FOLLOWCOUNT,followCount);
    }

    public int getFollowCount(){
        return getInt(FOLLOWCOUNT);
    }

    public void setFansCount(int fansCount){
        editInt(FANSCOUNT,fansCount);
    }

    public int getFansCount(){
        return getInt(FANSCOUNT);
    }
    public void saveTheraUser(final User user) {
        new Thread() {
            public void run() {
                savaUser(user);
            }
        }.start();
    }



    public void clean() {
        long uid = getUid();
        String nickName = getNickname();
        String avatar = getAvatar();
        mPreferences.edit().clear().commit();
        setAvatar(avatar);
        setNickname(nickName);
        mPreferences.edit().putLong(UID,uid);
    }
}
