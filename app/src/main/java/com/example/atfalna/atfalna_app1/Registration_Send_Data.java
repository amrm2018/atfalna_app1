package com.example.atfalna.atfalna_app1;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amr1 on 3/16/2018.
 */

public class Registration_Send_Data  extends StringRequest {

    private static final String SEND_DATA_URL = "http://192.168.1.4/atfalna_app/register.php";
    private Map<String, String> MapData;

    public Registration_Send_Data(String name, String email, String password,String gender, String phone, Response.Listener<String> listener) {
        super(Method.POST, SEND_DATA_URL, listener, null);
        MapData = new HashMap<>();

        MapData.put("user_name", name);
        MapData.put("user_email", email);
        MapData.put("password", password);
        MapData.put("user_gender", gender);
        MapData.put("user_phone", phone);
    }

    @Override
    public Map<String, String> getParams() {
        return MapData;
    }
}