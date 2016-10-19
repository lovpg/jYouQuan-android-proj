package com.frame.network.base;


import java.util.List;

import com.frame.network.bean.NameValueParams;
import com.frame.network.inter.OnParseObserver;

/**
 * 处理分页
 * Created by LDM on 14-1-10. Email : nightkid-s@163.com
 */
public abstract class PageLoader<T> extends ListLoader<T> implements OnParseObserver<List<T>> {

    protected int page; //页数，默认为1

    private int ItemCount; //该页对应有多少条数据

    protected PageLoader() {
        this(1);
    }

    protected PageLoader(boolean  reLogin, boolean cache) {
        this(1, reLogin, cache);
    }

    protected PageLoader(int page) {
        this(page, true, false);
    }

    protected PageLoader(int page, boolean reLogin, boolean cache) {
        super(reLogin, cache);
        this.page = page;
        registerParserObserver(this);
    }

    protected abstract void setNoPageParams(List<NameValueParams> params); //设置参数，由于页数参数在这层已经设置好，所以这个方法不应该再设置页数参数

    @Override
    protected void setParams(List<NameValueParams> params) {
        params.add(new NameValueParams("page", String.valueOf(page)));
        setNoPageParams(params);
    }

    @Override
    public void onParseSuccess(List<T> list) {
        ItemCount = list == null ? 0 : list.size();
    }

    public int getPage(){
        return page;
    }

    public void setPage(int page){
        this.page = page;
    }

    public void loadPage(int page){
        this.page = page;
        startRequest();
    }

    public void loadNextPage(){
        loadPage( page + 1 );
    }

    public void loadFirstPage(){
        loadPage(1);
    }

    public int getItemCount() {
        return ItemCount;
    }
}
