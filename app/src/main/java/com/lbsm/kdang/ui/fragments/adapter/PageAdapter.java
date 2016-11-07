package com.lbsm.kdang.ui.fragments.adapter;

import android.content.Context;

import com.frame.network.inter.OnFailSessionObserver2;
import com.frame.network.inter.OnLoadObserver2;
import com.frame.network.inter.OnParseObserver2;
import com.frame.view.pullview.OnLoadMoreListener;
import com.frame.view.pullview.OnRefreshListener;
import com.frame.view.pullview.PullListView;
import com.lbsm.kdang.R;
import com.lbsm.kdang.entity.vo.BaseVo;
import com.lbsm.kdang.net.request.base.PageLoader;
import com.lbsm.kdang.utils.NetUtil;

import java.util.List;

/**
 * date: 2016/11/1.
 */

public abstract class PageAdapter<T,P extends PageLoader<? extends BaseVo<T>>,H>
        extends BaseCusAdapter<T,H> implements OnLoadObserver2,OnParseObserver2<BaseVo<T>>
        ,OnFailSessionObserver2,OnRefreshListener,OnLoadMoreListener{

    protected PullListView plv;
    public P pageLoader;

    public PageAdapter(Context context, PullListView plv,P pageLoader) {
        super(context);
        this.plv=plv;
        registerLoader(pageLoader);
        plv.setAdapter(this);
        plv.setOnLoadMoreListener(this);
        plv.setOnRefreshListener(this);
    }

    @Override
    public void onPreLoadObserver(int id) {

    }

    @Override
    public void onLoadFinishObserver(int id, Object callbackData) {
        plv.refreshCompleted();
        plv.loadMoreCompleted(pageLoader.isNext());
    }

    @Override
    public void onParseSuccess(BaseVo<T> tBaseVo, int id, Object callBackData) {
        notifyDataSetChanged(tBaseVo.getData(),pageLoader.getPageId()<=1);
    }

    @Override
    public void onFailSession(String errorInfo, int failCode, int id, Object callBackData) {
        displayToast(errorInfo);
    }


    @Override
    public void onLoadMore() {
        if(NetUtil.isNetWorkConnected(context)){
            pageLoader.loadNextPage();
        }else {
            plv.loadMoreCompleted(true);
        }
    }

    @Override
    public void onRefresh() {
        pageLoader.loadFirstPage();
    }


    public void refreshPage(){
        refreshPage(true);
    }


    public void refreshPage(boolean showHeaderView){
        if(showHeaderView) plv.onHeadLoading(R.string.plv_text_loading);
        plv.setSelection(0);
        pageLoader.loadFirstPage();
    }

    public void refreshPage(P pageLoader){
        registerLoader(pageLoader);
        refreshPage();
    }

    private void notifyDataSetChanged(List<T> data, boolean isRefresh) {
        if (isRefresh) dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    private void registerLoader(P pageLoader){
        this.pageLoader=pageLoader;
        pageLoader.registerParserObserver(this);
        pageLoader.registerLoadObserver2(this);
        pageLoader.registerFailObserver(this);
    }

    public void setPlvAdapter(PullListView plv){
        this.plv = plv;
        plv.setAdapter(this);
        plv.setOnLoadMoreListener(this);
        plv.setOnRefreshListener(this);
    }

    public void reloadAdapter(){
        plv.setAdapter(this);
        plv.setOnRefreshListener(this);
    }

    public void setLoadMoreCompleted(){
        plv.loadMoreCompleted(false);
    }
}
