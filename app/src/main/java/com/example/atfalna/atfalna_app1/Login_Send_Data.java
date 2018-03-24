package com.example.atfalna.atfalna_app1;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amr1 on 3/16/2018.
 */

public class Login_Send_Data  extends StringRequest {

    private static final String SEND_DATA_URL = "http://192.168.1.4/atfalna_app/login.php";
    private Map<String,String> MapData;

    public Login_Send_Data(String Login_name, String Login_password, Response.Listener<String> listener) {
        super(Method.POST, SEND_DATA_URL, listener, null);
        MapData = new HashMap<>();
        MapData.put("Login_email", Login_name);
        MapData.put("Login_password", Login_password);
    }

    @Override
    public Map<String, String> getParams() {
        return MapData;
    }
}
