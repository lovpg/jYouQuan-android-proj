package com.frame.network.base;

import android.os.AsyncTask;

/**
 * 请求数据的抽象层
 * Created by LDM on 13-12-23. Email : nightkid-s@163.com
 */
public abstract class Request<T> {

    protected abstract void preRequest();
    protected abstract T doRequest();
    protected abstract void doRequestComplete(T t);

    private RequestTask requestAsyncTask;
    
    private class RequestTask extends AsyncTask<Void, Void, T> {

        @Override
        protected void onPreExecute() {
            preRequest();
            super.onPreExecute();
        }

        @Override
        protected T doInBackground(Void... params) {
            return doRequest();
        }

        @Override
        protected void onPostExecute(T result) {
            doRequestComplete(result);
            super.onPostExecute(result);
        }

    }

    public void startRequest(){
    	requestAsyncTask = new RequestTask();
    	if (requestAsyncTask != null)requestAsyncTask.execute();
    }
    
    public void onClean() {
		// TODO Auto-generated method stub
    	requestAsyncTask.cancel(true);
	}
}
