package com.lbsm.kdang.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/22.
 */
public class Place implements Serializable {

    private String title; //标题
    private String phone; //联系电话
    private String address; //地址
    private String type; //类型 目前有:hotel....按效果图来。
    private String location;//经纬度
    public Place() {
    }

    public Place(String title, String phone, String address, String type, String location) {
        this.title = title;
        this.phone = phone;
        this.address = address;
        this.type = type;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public Double getLongitude(){
        double d =  -1d;
        try{
            String[] s = location.split(",");
            if(s.length > 1)
                d = Double.parseDouble(s[1]);
        }catch (Exception e){
            e.printStackTrace();
        }
        return d;
    }

    public Double getLatitude(){
        double d =  -1d;
        try{
            String[] s = location.split(",");
            if(s.length > 1)
                d = Double.parseDouble(s[0]);
        }catch (Exception e){
            e.printStackTrace();
        }
        return d;
    }
}
