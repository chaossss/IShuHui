package com.github.chaossss.ishuhui.domain;

/**
 * Created by chaossss on 2016/1/6.
 */
public class AppConstant {
    /**
     * status constant
     */
    public static final int NET_RESPONSE_SUCCESS = 0;
    public static final String NET_RESPONSE_ERROR = "网络出错";
    public static final String EXCEPTION = "Exception----->";

    public static final int RECOMMENT = 0;//最新推荐

    public static final int WEEK = 1;//周更新

    public static final int ALLCOMMIC = 2;//所以漫画


    /**
     * login constant
     */
    public static final String LOGIN_EMAIL = "9291781011@qq.com";
    public static final String LOGIN_PSD = "123";
    public static final String LOGIN_CACHE_NAME = "key_login";
    public static final String LOGIN_RESULT = "login result----->";

    /**
     * others' result constant
     */
    public static final String GET_NEWEST_COMIC_RESULT = "get newest comic result----->";
    public static final String GET_COMIC_UPDATED_THIS_WEEK_RESULT = "get comic updated this week result----->";
    public static final String GET_SELECTED_COMIC_RESULT = "get selected comic result----->";
    public static final String GET_CATEGORY_ADV_DATA_RESULT = "get adv data result----->";
    public static final String GET_CATEGORY_RESULT = "get category data result----->";

    /**
     * comic detail & subscribe constant
     */
    public static final String SUBSCRIBE_SUCCESS = "Subscribe success!";
    public static final String SUBSCRIBE_CANCEL = "Subscribe canceled!";
    public static final String SUBSCRIBE_FAIL = "Subscribe failed!";
    public static final String SUBSCRIBE_RESULT = "subscribe result----->";
    public static final String COMIC_DETAIL_RESULT = "get comic detail result----->";
}
