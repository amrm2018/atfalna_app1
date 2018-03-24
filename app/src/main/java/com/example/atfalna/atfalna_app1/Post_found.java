package com.example.atfalna.atfalna_app1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import maes.tech.intentanim.CustomIntent;

public class Post_found extends AppCompatActivity {

    TextView tv_code_p, tv_user_name_f, tv_date_p_f, tv_time_p_f, tv_city, tv_day, tv_month,
            tv_year, tv_gender, tv_phone, tv_place, tv_info, tv_place_map1 ,tv_total_like_f;
    ImageView img_p_f  , img_like_f_1;

    EditText ed_comm_p_f, ed_comm_p_f_alert;
    String S_user_id_f;// صاحب البوست
    // دول بتاعه ال user اللى عمل login
    String S_user_name_login, S_user_id_login;
    GloablV gloablV;

    // من هنا الجزء الخاص بالتعليقات
    RequestQueue requestQueue;

    ArrayList<listitem_comm_f> listcomment_f = new ArrayList<listitem_comm_f>();
    ListView  listV_comm_f_alert;

    String S_code_p_f, S_lat_f, S_lng_f;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_found);

        tv_code_p = findViewById(R.id.tv_code_p_f);
        tv_user_name_f = findViewById(R.id.tv_user_name_p_f);
        tv_date_p_f = findViewById(R.id.tv_date_p_f);
        tv_time_p_f = findViewById(R.id.tv_time_p_f);
        tv_city = findViewById(R.id.tv_city_f);
        tv_day = findViewById(R.id.tv_day_m);
        tv_month = findViewById(R.id.tv_month_m);
        tv_year = findViewById(R.id.tv_year_f);
        tv_gender = findViewById(R.id.tv_gender_f);
        tv_phone = findViewById(R.id.tv_phone_f);
        tv_place = findViewById(R.id.tv_place_f);
        tv_info = findViewById(R.id.tv_info_f);

        img_like_f_1=findViewById(R.id.img_like_f_1);
        tv_total_like_f=findViewById(R.id.tv_total_like_f);


        tv_place_map1 = findViewById(R.id.tv_place_map1);
        // tv_map_2 = findViewById(R.id.tv_place_map2);

        img_p_f = findViewById(R.id.img_p_f);

        Intent data_p_f = getIntent();

        tv_code_p.setText(data_p_f.getExtras().getString("text_code_p_f").trim());
        S_code_p_f = data_p_f.getExtras().getString("text_code_p_f");

        tv_date_p_f.setText(data_p_f.getExtras().getString("text_date_p_f").trim());
        tv_time_p_f.setText(data_p_f.getExtras().getString("text_time_p_f").trim());

        tv_city.setText(data_p_f.getExtras().getString("text_city_f").trim());
        tv_day.setText(data_p_f.getExtras().getString("text_day_f").trim());
        tv_month.setText(data_p_f.getExtras().getString("text_month_f").trim());
        tv_year.setText(data_p_f.getExtras().getString("text_year_f").trim());

        tv_gender.setText(data_p_f.getExtras().getString("text_gender_f").trim());
        tv_phone.setText(data_p_f.getExtras().getString("text_phone_f").trim());
        tv_place.setText(data_p_f.getExtras().getString("text_place_case_f"));
        tv_info.setText(data_p_f.getExtras().getString("text_info_case_f").trim());

        S_lat_f = data_p_f.getExtras().getString("text_lat_f");
        S_lng_f = data_p_f.getExtras().getString("text_lng_f");

        String simg = data_p_f.getExtras().getString("text_img_f");

        S_user_id_f = data_p_f.getExtras().getString("text_us_id_f");
        // S_user_name= data_p_f.getExtras().getString("text_user_name_f");
        tv_user_name_f.setText(data_p_f.getExtras().getString("text_user_name_f").trim());

        Picasso.with(getApplicationContext())
                .load("http://192.168.1.3/atfalna_app/img_found/" + simg)
                .into(img_p_f);

        gloablV = (GloablV) getApplicationContext();
        S_user_name_login = gloablV.getUser_name_login();
        S_user_id_login = gloablV.getUser_id_login();

        //  send  Comment_p_f
        ed_comm_p_f = findViewById(R.id.ed_comment_p_f);

        try{

            if (S_lat_f.equals("") && S_lng_f.equals("")) {
                tv_place_map1.setText("لم يتم التحديد");
            } else {
                tv_place_map1.setText("اضغط لرؤيت المكان");
            }


            tv_place_map1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (S_lat_f.equals("") && S_lng_f.equals("")) {
                        Toast.makeText(getApplicationContext(), " لم يتم تحديد المكان ", Toast.LENGTH_LONG).show();
                    } else {
                        Intent Inshow_map_f = new Intent(getApplicationContext(), Map_show_palce_f.class);

                        Inshow_map_f.putExtra("key_show_lat_f", S_lat_f);
                        Inshow_map_f.putExtra("key_show_lng_f", S_lng_f);

                        startActivity(Inshow_map_f);
                    }

                }
            });
        }catch (Exception ex){

            Toast.makeText(getApplicationContext()," لم يتم تحديد المكان "+ ex, Toast.LENGTH_LONG).show();
        }

        get_comment_p_f();//1
        get_a_like_f();
    }



    // Send_Data_Comment_to_Serveries
    ProgressDialog pDialog_send_comm;

    public void btn_send_comment_p_f(final View view) {

        pDialog_send_comm = new ProgressDialog(Post_found.this);
        pDialog_send_comm.show();
        pDialog_send_comm.setMessage("لحظة و ستظهر التعليقات");
        listcomment_f.clear();
        try {
            String comment_p_f = ed_comm_p_f.getText().toString().trim();
            String code_p_f = tv_code_p.getText().toString().trim();

            if (comment_p_f.equals("")) {
                Toast.makeText(getApplicationContext(), "لا يمكن أضافة تعليق فارغ", Toast.LENGTH_SHORT).show();
                pDialog_send_comm.dismiss();
            } else {

                Response.Listener<String> responseLisener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(Post_found.this, "تم أضافة التعليق ", Toast.LENGTH_SHORT).show();
                                ed_comm_p_f.setText("");
                                get_comment_p_f();
                                btn_show_comm_f_in_alert(view);
                                pDialog_send_comm.dismiss();


                            } else {
                                Toast.makeText(getApplicationContext(), "يوجد خطأ ( تاكد من البيانات )", Toast.LENGTH_SHORT).show();
                                get_comment_p_f();
                                pDialog_send_comm.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "error is : " + e, Toast.LENGTH_LONG).show();
                            pDialog_send_comm.dismiss();
                            get_comment_p_f();

                        }
                    }
                };
                Comment_Send_Data_F send_data_comment_f = new Comment_Send_Data_F(
                        comment_p_f, S_user_id_login, S_code_p_f, responseLisener);
                RequestQueue queue_comment_p_f = Volley.newRequestQueue(getApplicationContext());
                queue_comment_p_f.add(send_data_comment_f);
            }

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "error ex : " + ex, Toast.LENGTH_SHORT).show();
        }


    }

    public void get_comment_p_f() {
        //show comment f
        final TextView tv_total_comm_f = findViewById(R.id.tv_total_comm_f);
        String url_comm_f = "http://192.168.1.3/atfalna_app/show_all_comment_found.php?code_p_f=" + S_code_p_f;
        requestQueue = Volley.newRequestQueue(this);

        listcomment_f.clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_comm_f,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("allcomment_p_f");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject res = jsonArray.getJSONObject(i);
                                tv_total_comm_f.setText(String.valueOf(jsonArray.length()));
                                String code_comm = res.getString("code_comm");
                                String date_comm = res.getString("date_comm");
                                String time_comm = res.getString("time_comm");
                                String comment_f = res.getString("comment");
                                String us_id_comm = res.getString("us_id");
                                String user_name_comm = res.getString("user_name");
                                listcomment_f.add(new listitem_comm_f(code_comm,
                                        date_comm, time_comm,
                                        comment_f,
                                        us_id_comm, user_name_comm));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //  listData_comm_f();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "Error");
            }
        });
        requestQueue.add(jsonObjectRequest);
        // end comment
    }

    public void listData_comm_f_alert() {
        ListAdapter_comm ad_comm = new ListAdapter_comm(listcomment_f);
        listV_comm_f_alert.setAdapter(ad_comm);
    }

    class ListAdapter_comm extends BaseAdapter {

        ArrayList<listitem_comm_f> list_comm = new ArrayList<listitem_comm_f>();

        ListAdapter_comm(ArrayList<listitem_comm_f> listitme) {
            this.list_comm = listitme;
        }

        @Override
        public int getCount() {
            return list_comm.size();
        }

        @Override
        public Object getItem(int i) {
            return list_comm.get(i).comment_f;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View v_comm = layoutInflater.inflate(R.layout.row_itme_comment, null);

            TextView comment_f = v_comm.findViewById(R.id.tv_comment_f_row);
            TextView date_comm_f = v_comm.findViewById(R.id.tv_date_comm_f_row);
            TextView time_comm_f = v_comm.findViewById(R.id.tv_time_comm_f_row);
            TextView user_name_comm_f = v_comm.findViewById(R.id.tv_user_name_comm_f_row);

            String code_comm, us_id_comm;
            code_comm = list_comm.get(i).code_comm_f;
            us_id_comm = list_comm.get(i).us_id_f;
            comment_f.setText(list_comm.get(i).comment_f);
            date_comm_f.setText(list_comm.get(i).date_comm_f);
            time_comm_f.setText(list_comm.get(i).time_comm_f);
            user_name_comm_f.setText(list_comm.get(i).user_name_f);

            return v_comm;
        }
    }

    public void btn_show_comm_f_in_alert(View v) {

        LayoutInflater layoutInflater = LayoutInflater.from(Post_found.this);
        View vComm = layoutInflater.inflate(R.layout.itme_alertdialog_comment, null);

        listV_comm_f_alert = vComm.findViewById(R.id.listV_all_comment_f_alert);

        listData_comm_f_alert();

        AlertDialog.Builder alert = new AlertDialog.Builder(Post_found.this);
        alert.setCancelable(true);
        alert.setView(vComm);
        alert.show();

    }



    public void get_a_like_f() {
        String url_get_like_f = "http://192.168.1.3/atfalna_app/show_like_f.php?code_p_f=" + S_code_p_f;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_get_like_f,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        StringBuilder text = new StringBuilder();

                        try {
                            JSONArray jsonArray_likes = response.getJSONArray("a_likes");
                            tv_total_like_f.setText(String.valueOf(jsonArray_likes.length()));

                            for (int i = 0; i < jsonArray_likes.length(); i++) {
                                JSONObject respons = jsonArray_likes.getJSONObject(i);
                                text.append(respons.getString("us_id"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Post_found.this,"... خطا ..." +e, Toast.LENGTH_LONG).show();
                        }

                        if (text.toString().contains(S_user_id_login)) {
                            img_like_f_1.setEnabled(false);
                            img_like_f_1.setImageResource(R.drawable.ic_like2);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", "ERROR");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void btn_add_like_f(View view) {
        RequestQueue queue = Volley.newRequestQueue(Post_found.this);
        String url_add_Like = "http://192.168.1.3/atfalna_app/send_like_f.php?us_id_login="+S_user_id_login+"&code_p_f="+S_code_p_f ;
        StringRequest request = new StringRequest(Request.Method.GET, url_add_Like, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        Toast.makeText(Post_found.this, "شكرا على تضامنك", Toast.LENGTH_SHORT).show();
                          img_like_f_1.setEnabled(false);
                          img_like_f_1.setImageResource(R.drawable.ic_like2);
                          get_a_like_f();
                    } else {
                        Toast.makeText(Post_found.this, "يوجد خطا ...", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Post_found.this,"... خطا ..." +e, Toast.LENGTH_LONG).show();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(request);
    }




    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), All_P_F.class));
        CustomIntent.customType(Post_found.this, "right-to-left");
    }

}
