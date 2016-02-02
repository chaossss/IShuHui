package com.github.chaossss.ishuhui.domain.model;

/**
 * Created by chaossss on 2016/1/6.
 */
public class LoginModel {
    public String ErrCode;
    public String ErrMsg;
    public UserMessage Return;

    public class UserMessage{
        public String Email;
    }
}
