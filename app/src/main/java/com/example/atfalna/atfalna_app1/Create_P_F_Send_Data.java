package com.example.atfalna.atfalna_app1;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amr1 on 3/16/2018.
 */

public class Create_P_F_Send_Data  extends StringRequest {

    private static final String SEND_DATA_URL = "http://192.168.1.2/atfalna_app/post_found.php";
    private Map<String, String> MapData;

    public Create_P_F_Send_Data(String img_f,
                                String city_f,
                                String day_f, String month_f , String year_f,
                                String gender_f, String phone_f ,
                                String place_f, String info_f,
                                String us_id_f ,  String user_name_f,
                                String lat_f , String lng_f ,
                                Response.Listener<String> listener) {
        super(Method.POST, SEND_DATA_URL, listener, null);
        MapData = new HashMap<>();

        MapData.put("img_f", img_f);

        MapData.put("city_f", city_f);

        MapData.put("day_f", day_f);
        MapData.put("month_f", month_f);
        MapData.put("year_f", year_f);

        MapData.put("gender_f", gender_f);
        MapData.put("phone_f", phone_f);

        MapData.put("place_f", place_f);
        MapData.put("info_f", info_f);

        MapData.put("us_id_f", us_id_f);
        MapData.put("user_name_f", user_name_f);

        MapData.put("lat_f", lat_f);
        MapData.put("lng_f", lng_f);

    }

    @Override
    public Map<String, String> getParams() {
        return MapData;
    }
}