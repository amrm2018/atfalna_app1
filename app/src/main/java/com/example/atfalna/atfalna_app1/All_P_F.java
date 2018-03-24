package com.example.atfalna.atfalna_app1;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class All_P_F extends AppCompatActivity {

    RequestQueue requestQueue;
    String url = "http://192.168.1.4/atfalna_app/show_all_post_found.php";
    ArrayList<listitem_f> listMovis = new ArrayList<listitem_f>();
    ListView listV_all_p_f ;
    GloablV gloablV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_p_f);

        Toolbar toolbar =findViewById(R.id.toolbar_all_p_f); // get the reference of Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Refresh();

        listV_all_p_f = findViewById(R.id.listv_all_p_f);

        gloablV = (GloablV) getApplicationContext();

        show_all_p_f();

    }

    public void show_all_p_f() {
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
                                String user_name_f = res.getString("user_name_f");

                                String lat_f = res.getString("lat_f");
                                String lng_f = res.getString("lng_f");

                                listMovis.add(new listitem_f(code_p_f, date_p_f, time_p_f,
                                        city_f,
                                        day_f, month_f, year_f,
                                        gender_f, phone_f,
                                        place_case_f, info_case_f,
                                        img_f,
                                        us_id_f, user_name_f ,
                                        lat_f ,lng_f));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "error is : " + e, Toast.LENGTH_SHORT).show();
                        }
                        listData();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "Error");
            }
        });
        requestQueue.add(jsonObjectRequest);

    }


    public void listData() {
        ListAdapter_all_p_f ad = new ListAdapter_all_p_f(listMovis);
        listV_all_p_f.setAdapter(ad);
    }

    class ListAdapter_all_p_f extends BaseAdapter {

        ArrayList<listitem_f> listA = new ArrayList<listitem_f>();

        ListAdapter_all_p_f(ArrayList<listitem_f> listitme) {
            this.listA = listitme;
        }

        @Override
        public int getCount() {
            return listA.size();
        }

        @Override
        public Object getItem(int i) {
            return listA.get(i).code_p_f;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View v1 = layoutInflater.inflate(R.layout.row_itme_p_f, null);

            TextView code_p_f = v1.findViewById(R.id.tv_code_post_f_list);
            TextView user_name_p_f = v1.findViewById(R.id.tv_user_name_m);
            TextView city_f = v1.findViewById(R.id.tv_city_f_list);
            TextView date_p_f = v1.findViewById(R.id.tv_date_m);

            final ImageView imgfound = v1.findViewById(R.id.img_f_list);

            code_p_f.setText(listA.get(i).code_p_f);
            user_name_p_f.setText(listA.get(i).user_name_f);
            city_f.setText(listA.get(i).city_f);
            date_p_f.setText(listA.get(i).date_p_f);

            Picasso.with(getApplicationContext())
                    .load("http://192.168.1.4/atfalna_app/img_found/" + listA
                    .get(i).img_f)
                    .into(imgfound);

            imgfound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent openPost = new Intent(getApplicationContext(), Post_found.class);

                    openPost.putExtra("text_code_p_f", listA.get(i).code_p_f);
                    openPost.putExtra("text_date_p_f", listA.get(i).date_p_f);
                    openPost.putExtra("text_time_p_f", listA.get(i).time_p_f);

                    openPost.putExtra("text_city_f", listA.get(i).city_f);

                    openPost.putExtra("text_day_f", listA.get(i).day_f);
                    openPost.putExtra("text_month_f", listA.get(i).month_f);
                    openPost.putExtra("text_year_f", listA.get(i).year_f);

                    openPost.putExtra("text_gender_f", listA.get(i).gender_f);
                    openPost.putExtra("text_phone_f", listA.get(i).phone_f);

                    openPost.putExtra("text_place_case_f", listA.get(i).place_case_f);
                    openPost.putExtra("text_info_case_f", listA.get(i).info_case_f);

                    openPost.putExtra("text_img_f", listA.get(i).img_f);

                    openPost.putExtra("text_us_id_f", listA.get(i).us_id_f);
                    openPost.putExtra("text_user_name_f", listA.get(i).user_name_f);

                    openPost.putExtra("text_lat_f", listA.get(i).lat_f);
                    openPost.putExtra("text_lng_f", listA.get(i).lng_f);

                    startActivity(openPost);
                }
            });

            return v1;
        }
    }

    public void Refresh() {
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipelayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.color2, R.color.colorPink, R.color.colorRed);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        listMovis.clear();
                        show_all_p_f();
                       // startActivity(new Intent(getApplicationContext(), All_P_F.class));
                    }
                }, 2000);
            }
        });

    }

}
