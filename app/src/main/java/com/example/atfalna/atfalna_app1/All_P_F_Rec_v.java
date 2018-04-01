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

public class All_P_F_Rec_v extends AppCompatActivity {

    GloablV gloablV ;
    String S_user_id_login , S_ip;
    private RecyclerView recy_all_p_f ;
    private rec_v_ad_all_p_f recyclerView_dAdapter;

    RequestQueue requestQueue ;

    public List<listitem_f> list_p_f_rec = new ArrayList<listitem_f>();
    private GridLayoutManager gridLayoutManager ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_p_f_rec_v);


        gloablV =(GloablV) getApplicationContext();
        S_ip = gloablV.getIp_url();
        S_user_id_login = gloablV.getUser_id_login();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_all_p_f_rec); // get the reference of Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recy_all_p_f = findViewById(R.id.recy_all_p_f);
        recy_all_p_f.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(this, 1);
        recy_all_p_f.setLayoutManager(gridLayoutManager);

        recyclerView_dAdapter = new rec_v_ad_all_p_f(list_p_f_rec, All_P_F_Rec_v.this);
        recy_all_p_f.setAdapter(recyclerView_dAdapter);

        Refresh();
        show_all_p_f();

    }

    public void show_all_p_f() {

        String url = S_ip+"/atfalna_app/show_all_post_found.php";
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("allpost_found");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject res = jsonArray.getJSONObject(i);

                                String code_p_f = res.getString("code_p_f");
                                String date_p_f = res.getString("date_p_f");
                                String time_p_f = res.getString("time_p_f");

                                String city_f = res.getString("city_f");

                                String day_f = res.getString("day_f");
                                String month_f = res.getString("month_f");
                                String year_f = res.getString("year_f");

                                String gender_f = res.getString("gender_f");
                                String phone_f = res.getString("phone_f");

                                String place_case_f = res.getString("place_case_f");
                                String info_case_f = res.getString("info_case_f");
                                String img_f = res.getString("img_f");

                                String us_id_f = res.getString("us_id");
                                String user_name_f = res.getString("user_name");

                                String lat_f = res.getString("lat_f");
                                String lng_f = res.getString("lng_f");

                                list_p_f_rec.add(new listitem_f(code_p_f, date_p_f, time_p_f,
                                        city_f,
                                        day_f, month_f, year_f,
                                        gender_f, phone_f,
                                        place_case_f, info_case_f,
                                        img_f,
                                        us_id_f, user_name_f ,
                                        lat_f ,lng_f));
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
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipelayout_all_p_f_rec);
        swipeRefreshLayout.setColorSchemeResources(R.color.color2, R.color.colorPink, R.color.colorRed);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        list_p_f_rec.clear();
                        show_all_p_f();
                        // startActivity(new Intent(getApplicationContext(), All_P_F.class));
                    }
                }, 1000);
            }
        });

    }

}
