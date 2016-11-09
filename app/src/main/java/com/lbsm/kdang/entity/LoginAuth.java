package com.lbsm.kdang.entity;

/**
 * date: 2016/10/26.
 */

public class LoginAuth {


    /**
     * uid : 561308
     * username : 13610241051
     * sessionId : 9f5c9e25-3b4b-46aa-b03a-c7c4b94b7e03
     * token : NzEwNDFkOGJlODA3Y2RiOTFhMTUwMTViYjJmNjRiNmM4MGJjMjM1YzoxNDc3NDcwOTkwMjc5
     * nickname : 迪文芷
     * gender :
     * avatar : http://app.lbslm.com/file/user/avatar/2872c238e341498490278f824bc5786a.jpg
     * server : app.cn.olla.im:master.cn.olla.im
     * imserver : im.cn.olla.im
     */

    private long uid;
    private String username;
    private String sessionId;
    private String token;
    private String nickname;
    private String gender;
    private String avatar;
    private String server;
    private String imserver;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getImserver() {
        return imserver;
    }

    public void setImserver(String imserver) {
        this.imserver = imserver;
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
                ", imserver='" + imserver + '\'' +
                '}';
    }
}
