package com.lbsm.kdang.picture.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/4.
 */
public class LocalFile implements Serializable{

    private String originalUri;//原图URI
    private String thumbnailUri;//缩略图URI
    private int orientation;//图片旋转角度
    private boolean isSelect = false;
    private long time;
    private String compressUri;
    private String thumbnaiPath;

    public String getOriginalUri() {
        return originalUri;
    }

    public void setOriginalUri(String originalUri) {
        this.originalUri = originalUri;
    }

    public String getThumbnailUri() {
        return thumbnailUri;
    }

    public void setThumbnailUri(String thumbnailUri) {
        this.thumbnailUri = thumbnailUri;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getCompressUri() {
        return compressUri;
    }

    public void setCompressUri(String compressUri) {
        this.compressUri = compressUri;
    }

    public String getThumbnaiPath() {
        return thumbnaiPath;
    }

    public void setThumbnaiPath(String thumbnaiPath) {
        this.thumbnaiPath = thumbnaiPath;
    }
}
