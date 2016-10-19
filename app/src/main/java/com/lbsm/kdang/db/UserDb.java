package com.lbsm.kdang.db;


import com.frame.db.exception.DbException;
import com.google.gson.Gson;
import com.lbsm.kdang.app.KDangApplication;
import com.lbsm.kdang.entity.SimpleUser;
import com.lbsm.kdang.entity.User;

public class UserDb {


	public long uid = -1;
	public String nickname = "";
	public String gender = "gay";
	public String sign = "";
	public String cover = "";
	public String avatar = "";
	public String region = "";
	public String location = "";
	public String distanceText = "";

	public String speaking = "";
	public String learning = "";
	public boolean dark = false;// 是否被关小黑屋
	public String interests = "";// 兴趣爱好
	public String voice = "";

	public String shareId = "";

	private int fansCount;
	private int followCount;

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
		return user;
	}

	
	public static UserDb toUser(User user) {
		// TODO Auto-generated method stub
		UserDb db = new UserDb();
		db.setAvatar(user.getAvatar());
		db.setCover(user.getCover());
		db.setDark(user.isDark());
		db.setDistanceText(user.getDistanceText());
		db.setGender(user.getGender());
		db.setInterests(user.getInterests());
		db.setLearning(user.getLearning());
		db.setLocation(user.getLocation());
		db.setNickname(user.getNickname());
		db.setRegion(user.getRegion());
		db.setShareId(new Gson().toJson(user.getShare()));
		db.setSign(user.getSign());
		db.setSpeaking(user.getSpeaking());
		db.setUid(user.getUid());
		db.setVoice(user.getVoice());
		db.setFansCount(user.getFansCount());
		db.setFollowCount(user.getFollowCount());
		return db;
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

	public String getShareId() {
		return shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
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

	@Override
	public String toString() {
		return "User [uid=" + uid + ", nickname=" + nickname + ", gender="
				+ gender + ", sign=" + sign + ", cover=" + cover + ", avatar="
				+ avatar + ", region=" + region + ", location=" + location
				+ ", distanceText=" + distanceText + ", speaking=" + speaking
				+ ", learning=" + learning + ", dark=" + dark + ", interests="
				+ interests + ", voice=" + voice + ", shareId=" + shareId
				+ ", user=" + user + "]";
	}

    public static void insert(User user1){
        try {
            KDangApplication.getInstance().dbHelper.insert(toUser(user1));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static UserDb query(String uid){
        UserDb userDb = null;
        try {
            userDb = (UserDb) KDangApplication.getInstance().dbHelper.query(UserDb.class,"uid",uid);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return userDb;
    }

}
