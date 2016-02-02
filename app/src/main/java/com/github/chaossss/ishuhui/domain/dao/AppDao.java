package com.github.chaossss.ishuhui.domain.dao;

import com.github.chaossss.httplibrary.http.Http;
import com.github.chaossss.httplibrary.listener.BaseCallbackListener;
import com.github.chaossss.ishuhui.domain.model.AdvModel;
import com.github.chaossss.ishuhui.domain.model.AllBookModels;
import com.github.chaossss.ishuhui.domain.model.CategoryModel;
import com.github.chaossss.ishuhui.domain.model.ComicDetailModel;
import com.github.chaossss.ishuhui.domain.model.LoginModel;
import com.github.chaossss.ishuhui.domain.model.SearchComicModel;
import com.github.chaossss.ishuhui.domain.model.SubscribeModel;
import com.github.chaossss.ishuhui.domain.url.ShuHuiURL;
import com.github.chaossss.ishuhui.domain.util.EncryptionUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chaossss on 2016/1/4.
 */
public class AppDao {

    private static final String FromType = "2";

    private static AppDao instance;

    public static AppDao getInstance() {
        if (instance == null) {
            instance = new AppDao();
        }
        return instance;
    }

    private AppDao() {
    }

    public Map<String,String> createMap()
    {
        Map<String,String> map = new HashMap<>();
        return map;
    }

    /**
     * 获取最新漫画
     * @param listener
     */
    public void getAllBook(BaseCallbackListener<AllBookModels> listener)
    {
        Http.post(ShuHuiURL.GET_ALL_BOOK, null, listener);
    }

    /**
     * 获取最新推荐漫画
     */
    public void getRecommendBook(String Recommended,String Subscribe,BaseCallbackListener<AllBookModels> listener)
    {
        Map<String,String> map = createMap();
        map.put("Recommended", Recommended);
        map.put("Subscribe",Subscribe);
        Http.post(ShuHuiURL.URL_RECOMMEND,map,listener);
    }

    /**
     * 本周更新
     */
    public void getWeekBook(String Days,String Subscribe,BaseCallbackListener<AllBookModels> listener)
    {
        Map<String,String> map = createMap();
        map.put("Days", Days);
        map.put("Subscribe",Subscribe);
        Http.post(ShuHuiURL.URL_WEEK_SEVEN,map,listener);
    }

    /**
     * 获取某章节的图片信息
     */
    public void getChapterImg(String id,BaseCallbackListener<String> listener)
    {
        Http.get(ShuHuiURL.URL_IMG_CHAPTER + id, listener);
    }

    /**
     * 订阅漫画
     * @param id
     * @param flag
     * @param listener
     */
    public void subscribeBook(String id,boolean flag, BaseCallbackListener<SubscribeModel> listener)
    {
        Map<String,String> map = createMap();
        map.put("isSubscribe", String.valueOf(flag));
        map.put("bookid",id);
        Http.post(ShuHuiURL.URL_USER_SUBSCRIBE, map, listener);
    }


    /**
     * 获取用户订阅的列表
     * @param Subscribe
     * @param listener
     */
    public void subscribeByUser(String Subscribe,BaseCallbackListener<AllBookModels> listener)
    {
        Map<String,String> map = createMap();
        map.put("Subscribe", Subscribe);
        Http.post(ShuHuiURL.URL_SUBCRIBE_USER, map, listener);
    }

    /**
     * 获取幻灯片接口
     */
    public void getSlideData(BaseCallbackListener<AdvModel> listener)
    {
        Http.get(ShuHuiURL.URL_SLIDE_DATA, listener);
    }


    /**
     * 获取某一分类30条记录
     * ClassifyId分类标识，0热血，1国产，2同人，3鼠绘
     * Size每次获取的消息条数，最大值为30
     * PageIndex获取第几页的数据
     */
    public void getCategoryData(String ClassifyId,String Size,String PageIndex,BaseCallbackListener<CategoryModel> listener)
    {
        Map<String,String> map = createMap();
        map.put("ClassifyId", ClassifyId);
        map.put("Size",Size);
        map.put("PageIndex",PageIndex);
        Http.post(ShuHuiURL.URL_CATEGORY_DATA, map, listener);
    }

    /**
     * 搜索动漫数据
     * @param Title
     * @param listener
     */
    public void searchComicData(String Title,BaseCallbackListener<SearchComicModel> listener)
    {
        Map<String,String> map = createMap();
        try {
            map.put("Title", URLDecoder.decode(Title, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Http.post(ShuHuiURL.URL_SEARCH_DATA, map, listener);
    }

    public void getBookComicData(String id,String PageIndex,BaseCallbackListener<ComicDetailModel> listener)
    {
        Map<String,String> map = createMap();
        map.put("id",id);
        map.put("PageIndex",PageIndex);
        Http.post(ShuHuiURL.URL_COMIC_BOOK_DATA, map, listener);
    }

    /**
     * 登录
     * @param email
     * @param pass
     * @param listener
     */
    public void userLogin(String email,String pass, BaseCallbackListener<LoginModel> listener)
    {
        Map<String,String> map = createMap();
        map.put("Email", email);
        map.put("Password", EncryptionUtils.createMd5(pass));
        map.put("FromType",FromType);
        Http.post(ShuHuiURL.URL_USER_LOGIN,map, listener);
    }
}
