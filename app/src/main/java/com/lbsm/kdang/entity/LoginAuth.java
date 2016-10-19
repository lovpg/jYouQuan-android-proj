package com.lbsm.kdang.entity;

public class LoginAuth {

	private long uid;
	private String username;
	private String sessionId;
	private String token;
	private String nickname;// 昵称
	private String gender;// 性别
	private String avatar;
	private String server;
	private String imServer;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getImServer() {
		return imServer;
	}

	public void setImServer(String imServer) {
		this.imServer = imServer;
	}

	@Override
	public String toString() {
		return "LoginAuth{" +
				"uid=" + uid +
				", username='" + username + '\'' +
				", sessionId='" + sessionId + '\'' +
				", token='" + token + '\'' +
				", nickname='" + nickname + '\'' +
				", gender='" + gender + '\'' +
				", avatar='" + avatar + '\'' +
				", server='" + server + '\'' +
				'}';
	}
}
