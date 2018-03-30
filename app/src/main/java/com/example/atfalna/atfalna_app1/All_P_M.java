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
import android.widget.AdapterView;
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

import maes.tech.intentanim.CustomIntent;

public class All_P_M extends AppCompatActivity {


    RequestQueue requestQueue;

    ArrayList<listitem_m> listMissing = new ArrayList<listitem_m>();
    ListView listV_all_p_m;
    GloablV gloablV;
    String sip ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_p_m);

        Toolbar toolbar = findViewById(R.id.toolbar_all_p_m); // get the reference of Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Refresh();

        listV_all_p_m = findViewById(R.id.listv_all_p_m);

        gloablV = (GloablV) getApplicationContext();
        sip= gloablV.getIp_url();

        show_all_p_m();

    }

    public void show_all_p_m() {

        String url_all_m = sip +"/atfalna_app/show_all_post_missing.php";
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_all_m,
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

                                listMissing.add(new listitem_m(code_p_m, date_p_m, time_p_m,
                                        city_m, gender_m, phone_m,
                                        day_m, month_m, year_m,
                                        color_eye, color_hair, color_body,
                                        address_m, bonus,
                                        length_m, wiegth,
                                        case_name, nickname,
                                        age_now, note_m,
                                        img_m,
                                        us_id_m, user_name_m ,
                                        lat_m , lng_m ));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "error is : " + e, Toast.LENGTH_LONG).show();
                        }
                        listData_m();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "Error");
            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    public void listData_m() {
        ListAdapter_all_p_m ad = new ListAdapter_all_p_m(listMissing);
        listV_all_p_m.setAdapter(ad);
    }

    class ListAdapter_all_p_m extends BaseAdapter {

        ArrayList<listitem_m> listM = new ArrayList<listitem_m>();

        ListAdapter_all_p_m(ArrayList<listitem_m> listitme) {
            this.listM = listitme;
        }

        @Override
        public int getCount() {
            return listM.size();
        }

        @Override
        public Object getItem(int i) {
            return listM.get(i).code_p_m;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View v1 = layoutInflater.inflate(R.layout.row_itme_p_m, null);

            TextView code_p_m = v1.findViewById(R.id.tv_code_post_m_list);
            TextView user_name_p_m = v1.findViewById(R.id.tv_user_name_f_row);
            TextView city_m = v1.findViewById(R.id.tv_city_m_list);
            TextView date_p_m = v1.findViewById(R.id.tv_date_f_row);


            TextView case_name = v1.findViewById(R.id.tv_name_case_m_list);
            TextView bonus = v1.findViewById(R.id.tv_bonus_m_list);
            TextView phone_m = v1.findViewById(R.id.tv_phone_m_list);


            ImageView imgfound = v1.findViewById(R.id.img_m_list);

            code_p_m.setText(listM.get(i).code_p_m);
            user_name_p_m.setText(listM.get(i).user_name_m);
            city_m.setText(listM.get(i).city_m);
            date_p_m.setText(listM.get(i).date_p_m);
            case_name.setText(listM.get(i).case_name);
            bonus.setText(listM.get(i).bonus_m +" جنية ");
            phone_m.setText(listM.get(i).phone_m);

            Picasso.with(getApplicationContext())
                    .load(sip+ "/atfalna_app/img_missing/" + listM.get(i).img_m).into(imgfound);

            listV_all_p_m.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent openPost_m = new Intent(getApplicationContext(), Post_Missing.class);

                    openPost_m.putExtra("text_code_m", listM.get(i).code_p_m);
                    openPost_m.putExtra("text_date_m", listM.get(i).date_p_m);
                    openPost_m.putExtra("text_time_m", listM.get(i).time_p_m);

                    openPost_m.putExtra("text_city_m", listM.get(i).city_m);
                    openPost_m.putExtra("text_gender_m", listM.get(i).gender_m);
                    openPost_m.putExtra("text_phone_m", listM.get(i).phone_m);

                    openPost_m.putExtra("text_day_m", listM.get(i).day_m);
                    openPost_m.putExtra("text_month_m", listM.get(i).month_m);
                    openPost_m.putExtra("text_year_m", listM.get(i).year_m);

                    openPost_m.putExtra("text_color_eye_m", listM.get(i).color_eye);
                    openPost_m.putExtra("text_color_hair_m", listM.get(i).color_hair);
                    openPost_m.putExtra("text_color_body_m", listM.get(i).color_body);

                    openPost_m.putExtra("text_address_m", listM.get(i).address_m);
                    openPost_m.putExtra("text_bonus_m", listM.get(i).bonus_m);

                    openPost_m.putExtra("text_length_m", listM.get(i).length_m);
                    openPost_m.putExtra("text_wiegth_m", listM.get(i).wiegth_m);

                    openPost_m.putExtra("text_case_name_m", listM.get(i).case_name);
                    openPost_m.putExtra("text_nickname_m", listM.get(i).nickname);

                    openPost_m.putExtra("text_age_now_m", listM.get(i).age_now);
                    openPost_m.putExtra("text_note_m", listM.get(i).note_m);

                    openPost_m.putExtra("text_img_m", listM.get(i).img_m);

                    openPost_m.putExtra("text_us_id_m", listM.get(i).us_id_m);
                    openPost_m.putExtra("text_user_name_m", listM.get(i).user_name_m);

                    openPost_m.putExtra("text_lat_p_m", listM.get(i).lat_m);
                    openPost_m.putExtra("text_lng_p_m", listM.get(i).lng_m);

                    startActivity(openPost_m);
                    CustomIntent.customType(All_P_M.this, "left-to-right");

                }
            });

            return v1;
        }
    }


    public void Refresh() {
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipelayout_all_p_m);
        swipeRefreshLayout.setColorSchemeResources(R.color.color2, R.color.colorPink, R.color.colorRed);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        listMissing.clear();
                        show_all_p_m();
                       // startActivity(new Intent(getApplicationContext(), All_P_M.class));
                    }
                }, 1000);
            }
        });

    }


}
