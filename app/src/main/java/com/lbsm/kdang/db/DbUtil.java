package com.lbsm.kdang.db;

import android.text.TextUtils;

import com.lbsm.kdang.entity.SimpleUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/28.
 */
public class DbUtil {


    public static String[] ArrayString(String array){
        return TextUtils.isEmpty(array) ? array.split(";") : null;
    }

    public static String ListString(List<String> list){
        String lists = "";
        if(list != null && list.size() > 0){
            for (String str : list){
                lists += str+";";
            }
        }
        return lists;
    }




    public static List<String> StringList(String str){

        List<String> list = new ArrayList<String>();
        if(TextUtils.isEmpty(str)){
            String date [] = str.split(";");
            for (int i = 0;i < date.length;i++){
                list.add(date[i]);
            }
        }

        return list;
    }

    public static String SimpleString(List<SimpleUser> list){
        String uids = "";
        for (SimpleUser simpleUser : list){
            uids += simpleUser.getUid()+";";
        }
        return uids;
    }
}
