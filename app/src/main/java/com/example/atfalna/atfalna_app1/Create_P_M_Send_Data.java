package com.example.atfalna.atfalna_app1;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amr1 on 3/16/2018.
 */

public class Create_P_M_Send_Data extends StringRequest {

    private static final String SEND_DATA_URL = "http://192.168.1.4/atfalna_app/post_missing.php";
    private Map<String, String> MapData;

    public Create_P_M_Send_Data(
            String city_m,
            String gender_m, String phone_m,
            String day_m, String month_m, String year_m,
            String color_eye_m, String color_hair_m, String color_body_m,
            String address_m, String bouns_m,
            String length_m, String wiegth_m,
            String case_name_m, String nickname_m, String age_now_m,
            String note_m, String img_m,
            String user_id_m,String user_name_m ,
            String lat_m ,String lng_m ,
                                Response.Listener<String> listener) {
        super(Method.POST, SEND_DATA_URL, listener, null);
        MapData = new HashMap<>();

        MapData.put("city_m", city_m);

        MapData.put("gender_m", gender_m);
        MapData.put("phone_m", phone_m);

        MapData.put("day_m", day_m);
        MapData.put("month_m", month_m);
        MapData.put("year_m", year_m);

        MapData.put("color_eye_m", color_eye_m);
        MapData.put("color_hair_m", color_hair_m);
        MapData.put("color_body_m", color_body_m);


        MapData.put("address_m", address_m);

        MapData.put("bouns_m", bouns_m);
        MapData.put("length_m", length_m);
        MapData.put("wiegth_m", wiegth_m);

        MapData.put("casename_m", case_name_m);
        MapData.put("nickname_m", nickname_m);
        MapData.put("age_now_m", age_now_m);

        MapData.put("note_m", note_m);
        MapData.put("img_m", img_m);

        MapData.put("user_id_m", user_id_m);
        MapData.put("user_name_m", user_name_m);

        MapData.put("lat_m", lat_m);
        MapData.put("lng_m", lng_m);

    }

    @Override
    public Map<String, String> getParams() {
        return MapData;
    }
}