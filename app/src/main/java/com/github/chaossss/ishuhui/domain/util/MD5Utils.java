package com.github.chaossss.ishuhui.domain.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chaos on 2016/1/6.
 */
public class MD5Utils {
    public static String encryptionFor32(String plainText){
        String re_md5;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuilder sb = new StringBuilder("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(i));
            }

            re_md5 = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            re_md5 = "";
            e.printStackTrace();
        }
        return re_md5;
    }
}
