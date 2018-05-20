package com.example.atfalna.atfalna_app1;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amr1 on 3/24/2018.
 */

public class Comment_Send_Data_M extends StringRequest {

    private static final String SEND_DATA_URL = "http://192.168.1.4/atfalna_app/send_comment_m.php";
    private Map<String, String> MapData;

    public Comment_Send_Data_M(String comment_p_m,
                               String us_id_login, String code_p_m,
                               Response.Listener<String> listener) {
        super(Method.POST, SEND_DATA_URL, listener, null);
        MapData = new HashMap<>();

        MapData.put("comment", comment_p_m);
        MapData.put("us_id_login", us_id_login);
        MapData.put("code_p_m", code_p_m);
    }

    @Override
    public Map<String, String> getParams() {
        return MapData;
    }
}