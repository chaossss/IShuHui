package com.github.chaossss.httplibrary.listener;

/**
 * Interface that helps finish Http's work
 * Created by chaos on 2016/1/1.
 */
public abstract class CallBackListener<T> {
    public abstract void  onError(Exception e);
    public abstract void  onSuccess(T result);
    public abstract void  onStringResult(String result);
    public abstract void  onDownloadFinish(String path);
    public abstract void  onDownloadProgress(int progress);
}
