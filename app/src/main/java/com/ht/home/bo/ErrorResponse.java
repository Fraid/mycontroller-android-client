package com.ht.home.bo;

import java.util.List;

/**
 * Created by Gábor Marosfalvi on 2016. 09. 14..
 */
public class ErrorResponse {
    public String error;
    public String error_description;
    public Messages messages;

    public class Messages {
        public List<String> base;
    }



}
