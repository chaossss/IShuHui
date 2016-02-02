package com.github.chaossss.ishuhui.domain.handler;

import android.os.Handler;
import android.os.Message;

/**
 * Created by chaos on 2016/1/9.
 */
public class AdvPagerHandler extends Handler {
    public static final int DEFAULT_TIME = 6_000;
    public static final int ADV_PAGER_MSG = 0x233;

    private AdvPagerMsgListener advPagerMsgListener;

    public AdvPagerHandler(AdvPagerMsgListener advPagerMsgListener) {
        this.advPagerMsgListener = advPagerMsgListener;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case ADV_PAGER_MSG:
                advPagerMsgListener.onAdvPagerMsgRespond(msg);
                break;
        }
    }

    public void slidingPlayView(int currIndex) {
        Message message = Message.obtain();
        message.arg1 = currIndex;
        message.what = ADV_PAGER_MSG;
        sendMessageDelayed(message, DEFAULT_TIME);
    }
    
    public interface AdvPagerMsgListener{
        void onAdvPagerMsgRespond(Message msg);
    }
}
