package com.lbsm.kdang.db;


import com.frame.db.annotation.Id;
import com.frame.db.annotation.NoAutoIncrement;
import com.frame.db.exception.DbException;
import com.lbsm.kdang.app.KDangApplication;

/**
 */
public class PageDb {


    @NoAutoIncrement
    @Id
    private String url;


    private String objectId;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }


    public static String query(String url){
        PageDb pageDb =null;
        try {
            url = url + KDangApplication.getInstance().getAccount().getUid();
            pageDb = (PageDb) KDangApplication.getInstance().dbHelper.query(PageDb.class,"url",url);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return pageDb == null ? null : pageDb.getObjectId();
    }

    public static void insert(String url, String objectIds){
        PageDb pageDb = new PageDb();
        pageDb.setUrl(url);
        pageDb.setObjectId(objectIds);
        insert(pageDb);
    }

    public static void insert(PageDb pageDb){
        pageDb.setUrl(pageDb.getUrl()+KDangApplication.getInstance().getAccount().getUid());
        try {
            KDangApplication.getInstance().dbHelper.insert(pageDb);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void Detele(String url, String data){
        PageDb pageDb =null;
        url = url + KDangApplication.getInstance().getAccount().getUid();
        try {
            pageDb = (PageDb) KDangApplication.getInstance().dbHelper.query(PageDb.class,"url",url);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


}
