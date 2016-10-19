package com.lbsm.kdang.db;


import com.frame.db.annotation.Id;
import com.frame.db.annotation.NoAutoIncrement;
import com.frame.db.exception.DbException;
import com.google.gson.Gson;
import com.lbsm.kdang.app.KDangApplication;
import com.lbsm.kdang.entity.Share;
import com.lbsm.kdang.entity.SimpleUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShareDb implements Serializable {

	@NoAutoIncrement
	@Id
	private String shareId;
	private String location;
	private String address;
	private String content;
	private long posttime;
	private boolean good;
	private int goodCount;
	private int commentCount;
	private String imageList;
	private String goodIdList;

	private long userId;
	private long uid;
	private long barId = -1;
	private String remark;

	public String getShareId() {
		return shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getPosttime() {
		return posttime;
	}

	public void setPosttime(long posttime) {
		this.posttime = posttime;
	}

	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
	}

	public int getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public String getImageList() {
		return imageList;
	}

	public void setImageList(String imageList) {
		this.imageList = imageList;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public Long getBarId() {
		return barId;
	}

	public void setBarId(Long barId) {
		this.barId = barId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGoodIdList() {
		return goodIdList;
	}

	public void setGoodIdList(String goodIdList) {
		this.goodIdList = goodIdList;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setBarId(long barId) {
		this.barId = barId;
	}

	public static ShareDb toShareDb(Share share){
		Gson gson = new Gson();
		ShareDb shareDb = new ShareDb();
		shareDb.setAddress(share.getAddress());
		if(share.getBar() != null){
			shareDb.setBarId(share.getBar().getBid());
			BarDb.insert(share.getBar());
		}
		shareDb.setCommentCount(share.getCommentCount());
		shareDb.setContent(share.getContent());
		shareDb.setGood(share.isGood());
		shareDb.setGoodCount(share.getGoodCount());
		shareDb.setImageList(DbUtil.ListString(share.getImageList()));
		shareDb.setGoodIdList(DbUtil.SimpleString(share.getGoodList()));
		SimpleUser.insertList(share.getGoodList());
		shareDb.setUid(share.getUid());
		shareDb.setUserId(share.getUser().getUid());
		SimpleUser.insert(share.getUser());
		shareDb.setShareId(share.getShareId());
		shareDb.setRemark(gson.toJson(share.getRemark()));
		shareDb.setPosttime(share.getPosttime());
		shareDb.setLocation(share.getLocation());
		return shareDb;
	}

	public static void insert(Share share){
		try {
			KDangApplication.getInstance().dbHelper.insert(toShareDb(share));
		} catch (DbException e) {
			e.printStackTrace();
		}
	}


	public static void insertList(final List<Share> shares) {
		new Thread(){
			@Override
			public void run() {
				for (Share s : shares) {
					insert(s);
				}
			}
		}.start();

	}


	public static void insertPageList(final List<Share> shares, final String url) {
		new Thread(){
			@Override
			public void run() {
				String objectIds = "";
				for (Share s : shares) {
					insert(s);
					objectIds += s.getShareId() + ";";
				}
				PageDb pageDb = new PageDb();
				pageDb.setUrl(url);
				pageDb.setObjectId(objectIds);
				PageDb.insert(pageDb);
			}
		}.start();

	}


	public static ShareDb query(String shareId){
		ShareDb shareDb = null;
		try{
			shareDb = (ShareDb) KDangApplication.getInstance().dbHelper.query(ShareDb.class,"shareId",shareId);
		}catch (Exception e){
			e.printStackTrace();
		}
		return shareDb;
	}


	public static List<ShareDb> queryList(List<String> shareIds){
		List<ShareDb> shareDbs = new ArrayList<ShareDb>();
		for (String str : shareIds){
			ShareDb shareDb = query(str);
			if(shareDb!= null)shareDbs.add(shareDb);
		}
		return shareDbs;
	}

	public static void delete(String shareId){
		try {
			KDangApplication.getInstance().dbHelper.delete(ShareDb.class,"shareId",shareId);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
}
