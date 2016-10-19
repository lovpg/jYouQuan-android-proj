package com.lbsm.kdang.entity;

/**
 * Created by Administrator on 2015/7/24.
 */
public class Remark extends Place {
    private int points;
    private int tip;
    private String speaking;
    private String learning;
    public Remark() {
    }

    public Remark(int tip, String speaking, String learning) {
        this.tip = tip;
        this.speaking = speaking;
        this.learning = learning;
    }

    public int getPoints() {
        return points;
    }

    public int getTip() {
        return tip;
    }

    public String getSpeaking() {
        return speaking;
    }

    public String getLearning() {
        return learning;
    }
}
