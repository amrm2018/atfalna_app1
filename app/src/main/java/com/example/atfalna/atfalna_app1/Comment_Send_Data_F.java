package com.example.atfalna.atfalna_app1;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amr1 on 3/16/2018.
 */

public class Comment_Send_Data_F extends StringRequest {

    private static final String SEND_DATA_URL = "http://192.168.1.3/atfalna_app/send_comment.php";
    private Map<String, String> MapData;

    public Comment_Send_Data_F(String comment_p_f, String us_id_login, String code_p_f, Response.Listener<String> listener) {
        super(Method.POST, SEND_DATA_URL, listener, null);
        MapData = new HashMap<>();

        MapData.put("comment", comment_p_f);
        MapData.put("us_id_login", us_id_login);
        MapData.put("code_p_f", code_p_f);
    }

    @Override
    public Map<String, String> getParams() {
        return MapData;
    }
}