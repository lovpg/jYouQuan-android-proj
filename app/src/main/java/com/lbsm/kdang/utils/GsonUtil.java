package com.lbsm.kdang.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Date on 2016/10/16.
 */

public class GsonUtil {

    public static <T> T getObject(String jsonString,Class<T> cls){
        T t=null;
        try{
            Gson gson=new Gson();
            t=gson.fromJson(jsonString,cls);
        }catch (Exception e){
            e.printStackTrace();
        }

        return t;
    }

    public static <T> List<T> getObjects(String jsonString, Class<T> cls){
        List<T> list=new ArrayList<>();
        try{
            Gson gson=new Gson();
            list=gson.fromJson(jsonString,new TypeToken<List<T>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
