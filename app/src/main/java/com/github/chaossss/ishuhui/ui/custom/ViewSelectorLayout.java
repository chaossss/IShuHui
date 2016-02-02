package com.github.chaossss.ishuhui.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.chaossss.httplibrary.listener.ReloadCallbackListener;
import com.github.chaossss.ishuhui.R;
import com.github.chaossss.ishuhui.domain.util.NetUtils;

/**
 * Created by chaossss on 2016/1/4.
 */
public class ViewSelectorLayout extends FrameLayout implements View.OnClickListener {
    private View failView;
    private View emptyView;
    private View loadingView;
    private View contentView;

    private TextView tv_reload;
    private TextView tv_not_data;
    private ReloadCallbackListener callbackListener;

    public ViewSelectorLayout(Context context,View contentView) {
        super(context);
        this.contentView = contentView;
        initView();
        handleView();
    }

    public ViewSelectorLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ViewSelectorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    private void initView() {
        if(failView == null) {
            failView = inflaterView(R.layout.view_selector_loadfail);
            tv_reload = (TextView) failView.findViewById(R.id.view_selector_tv_fail);
            tv_reload.setOnClickListener(this);
        }

        if(emptyView == null){
            emptyView = inflaterView( R.layout.view_selector_empty);
            tv_not_data = (TextView) emptyView.findViewById(R.id.view_selector_tv_empty);
        }

        if(loadingView==null) {
            loadingView = inflaterView( R.layout.view_loading);
        }
    }

    /**
     *装载器
     * @param layoutId
     * @return
     */
    private View inflaterView(int layoutId) {
        return LayoutInflater.from(getContext()).inflate(layoutId, null);
    }

    private void handleView() {
        add_AllView();
        hide_AllView();
    }

    private void add_AllView() {
        this.addView(failView);
        this.addView(emptyView);
        this.addView(loadingView);
        this.addView(contentView);
    }

    private void hide_AllView() {
        if(failView != null) {
            failView.setVisibility(INVISIBLE);
        }
        if(loadingView != null) {
            loadingView.setVisibility(INVISIBLE);
        }
        if(emptyView != null) {
            emptyView.setVisibility(INVISIBLE);
        }
        if(contentView != null) {
            contentView.setVisibility(INVISIBLE);
        }
    }

    public void show_ContentView() {
        if(contentView != null) {
            contentView.setVisibility(VISIBLE);
        }
        if(failView != null) {
            failView.setVisibility(INVISIBLE);
        }
        if(loadingView != null) {
            loadingView.setVisibility(INVISIBLE);
        }
        if(emptyView != null) {
            emptyView.setVisibility(INVISIBLE);
        }
    }

    public void show_FailView() {
        if(failView != null) {
            failView.setVisibility(VISIBLE);
        }
        if(loadingView != null) {
            loadingView.setVisibility(INVISIBLE);
        }
        if(emptyView != null) {
            emptyView.setVisibility(INVISIBLE);
        }
        if(contentView != null) {
            contentView.setVisibility(INVISIBLE);
        }
    }

    public void show_EmptyView() {
        if(emptyView != null) {
            emptyView.setVisibility(VISIBLE);
        }

        if(failView != null) {
            failView.setVisibility(INVISIBLE);
        }
        if(loadingView != null) {
            loadingView.setVisibility(INVISIBLE);
        }

        if(contentView != null) {
            contentView.setVisibility(INVISIBLE);
        }
    }

    public void show_LoadingView() {
        if(loadingView != null) {
            loadingView.setVisibility(VISIBLE);
        }
        if(failView != null) {
            failView.setVisibility(INVISIBLE);
        }
        if(emptyView != null) {
            emptyView.setVisibility(INVISIBLE);
        }
        if(contentView != null) {
            contentView.setVisibility(INVISIBLE);
        }
    }

    public void setReLoadCallbackListener(ReloadCallbackListener callbackListener) {
        this.callbackListener = callbackListener;
    }

    @Override
    public void onClick(View v) {
        solveReLoad();
    }

    /**
     * 处理重新点击事件
     */
    private void solveReLoad() {
        if(NetUtils.isNetworkConnected(getContext()))
        {
            if(callbackListener != null) {
                this.callbackListener.onReLoadCallback();
            } else {
                throw new RuntimeException("You must be set setReLoadCallbackListener");
            }
        }
    }
}
