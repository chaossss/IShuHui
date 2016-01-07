package com.github.chaossss.httplibrary.listener;

/**
 * Created by chaos on 2016/1/1.
 */
public class BaseCallbackListener<T> extends CallBackListener<T> {

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onSuccess(T result) {

    }

    @Override
    public void onStringResult(String result) {

    }

    @Override
    public void onDownloadFinish(String path) {

    }

    @Override
    public void onDownloadProgress(int progress) {

    }
}
