package com.github.chaossss.ishuhui.domain.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chaos on 2016/1/6.
 */
public class EncryptionUtils {
    public static String createMd5(String... strParams){
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ;i<strParams.length;i++)
        {
            sb.append(strParams[i]);
        }

        return MD5Utils.encryptionFor32(sb.toString());
    }


    public static String getTime(){
        try{
            String default_time = "1970-01-010:00:00.000";

            Date date = new Date(System.currentTimeMillis());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddd:HH:mm.sss");

            long l2 = sdf.parse(default_time).getTime();

            String time = String.valueOf(date.getTime() - l2);

            return time;

        } catch (ParseException e) {
            printlnMsg("cjj error "+e.toString());
            return null;
        }
    }

    private static void printlnMsg(String msg){
        System.out.println(msg);
    }
}
