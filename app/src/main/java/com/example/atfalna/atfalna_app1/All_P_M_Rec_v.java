package com.example.atfalna.atfalna_app1;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class All_P_M_Rec_v extends AppCompatActivity {

    GloablV gloablV ;
    String S_user_id_login , S_ip;
    private RecyclerView recy_all_p_m ;
    private rec_v_ad_all_p_m recyclerView_dAdapter;

    RequestQueue requestQueue ;

    public List<listitem_m> list_p_m_rec = new ArrayList<listitem_m>();
    private GridLayoutManager gridLayoutManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_p_m_rec_v);


        gloablV =(GloablV) getApplicationContext();
        S_ip = gloablV.getIp_url();
        S_user_id_login = gloablV.getUser_id_login();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_all_p_m_rec); // get the reference of Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recy_all_p_m = findViewById(R.id.recy_all_p_m);
        recy_all_p_m.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(this, 1);
        recy_all_p_m.setLayoutManager(gridLayoutManager);

        recyclerView_dAdapter = new rec_v_ad_all_p_m(list_p_m_rec, All_P_M_Rec_v.this);
        recy_all_p_m.setAdapter(recyclerView_dAdapter);

        Refresh();
        show_all_p_m();


    }

    public void show_all_p_m() {

        String url = S_ip+"/atfalna_app/show_all_post_missing.php";
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("allpost_missing");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject res = jsonArray.getJSONObject(i);

                                String code_p_m = res.getString("code_p_m");
                                String date_p_m = res.getString("date_p_m");
                                String time_p_m = res.getString("time_p_m");

                                String city_m = res.getString("city_m");
                                String gender_m = res.getString("gender_m");
                                String phone_m = res.getString("phone_m");

                                String day_m = res.getString("day_m");
                                String month_m = res.getString("month_m");
                                String year_m = res.getString("year_m");

                                String color_eye = res.getString("color_eye");
                                String color_hair = res.getString("color_hair");
                                String color_body = res.getString("color_body");

                                String address_m = res.getString("address_m");
                                String bonus = res.getString("bonus");

                                String length_m = res.getString("length_m");
                                String wiegth = res.getString("wiegth_m");

                                String case_name = res.getString("case_name");
                                String nickname = res.getString("nickname");

                                String age_now = res.getString("age_now");
                                String note_m = res.getString("note_m");

                                String img_m = res.getString("img_m");

                                String us_id_m = res.getString("us_id");
                                String user_name_m = res.getString("user_name_m");

                                String lat_m = res.getString("lat_m");
                                String lng_m = res.getString("lng_m");

                                list_p_m_rec.add(new listitem_m(code_p_m, date_p_m, time_p_m,
                                        city_m, gender_m, phone_m,
                                        day_m, month_m, year_m,
                                        color_eye, color_hair, color_body,
                                        address_m, bonus,
                                        length_m, wiegth,
                                        case_name, nickname,
                                        age_now, note_m,
                                        img_m,
                                        us_id_m, user_name_m ,
                                        lat_m , lng_m));
                            }

                            recyclerView_dAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "خطا الاتصال بالخادم" , Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "Error");
                error.printStackTrace();
                if (error.getMessage().contains("Network is unreachable"))
                {
                    Toast.makeText(getApplicationContext(), "خطا الاتصال بالانترنت", Toast.LENGTH_SHORT).show();
                }
                // Toast.makeText(getApplicationContext(), "VolleyError :: " + error, Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void Refresh() {
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipelayout_all_p_m_rec);
        swipeRefreshLayout.setColorSchemeResources(R.color.color2, R.color.colorPink, R.color.colorRed);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        list_p_m_rec.clear();
                        show_all_p_m();
                        // startActivity(new Intent(getApplicationContext(), All_P_F.class));
                    }
                }, 1000);
            }
        });

    }


}
