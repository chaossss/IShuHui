package com.github.chaossss.ishuhui.domain.util;

import android.os.Handler;

/**
 * Utility class created to send messages to the future using an Android Handler.
 *
 * Created by chaossss on 2016/1/2.
 */
public class TimeTaskUtils {
    private static Handler handler = new Handler();

    private TimeTaskUtils() {
    }

    /**
     * Execute a Runnable implementation some milliseconds in the future.
     *
     * @param runnable to execute.
     * @param delay in miliseconds.
     */
    public static void sendMessageToTheFuture(final Runnable runnable, final int delay){
        handler.postDelayed(runnable, delay);
    }

    /**
     * Cancel a message already sent to the future.
     */
    public static void cancelMessage(Runnable runnable){
        handler.removeCallbacks(runnable);
    }
}
