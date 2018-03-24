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

public class Post_Missing extends AppCompatActivity {

    TextView tv_user_name_m, tv_date_m, tv_time_m, tv_city_m, tv_bonus_m,
            tv_phone_m, tv_gender_m, tv_day_m, tv_month_m, tv_year_m,
            tv_case_name_m, tv_nickname_m, tv_age_now_m, tv_color_body_m, tv_color_hair_m,
            tv_color_eye_m, tv_wiegth_m, tv_length_m, tv_address_m, tv_note_m ,
            tv_place_map_m ;

    ImageView img_p_m ;

    GloablV gloablV ;
    String S_code_p_m ,S_user_id_m , S_user_id_login , S_user_name_login ;

    // من هنا الجزء الخاص بالتعليقات
    RequestQueue requestQueue;

    ArrayList<listitem_comm_m> listcomment_m = new ArrayList<listitem_comm_m>();
    ListView listV_comm_m_alert;

    String S_lat_m, S_lng_m ;
    EditText ed_comm_p_m ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_missing);

        initView_P_M();

        Intent data_p_m = getIntent();

        S_code_p_m=data_p_m.getExtras().getString("text_code_p_m");
        tv_user_name_m.setText(data_p_m.getExtras().getString("text_user_name_m"));
        tv_date_m.setText(data_p_m.getExtras().getString("text_date_m"));
        tv_time_m.setText(data_p_m.getExtras().getString("text_time_m"));

        tv_city_m.setText(data_p_m.getExtras().getString("text_city_m"));
        tv_bonus_m.setText(data_p_m.getExtras().getString("text_bonus_m"));
        tv_phone_m.setText(data_p_m.getExtras().getString("text_phone_m"));
        tv_gender_m.setText(data_p_m.getExtras().getString("text_gender_m"));

        tv_day_m.setText(data_p_m.getExtras().getString("text_day_m"));
        tv_month_m.setText(data_p_m.getExtras().getString("text_month_m"));
        tv_year_m.setText(data_p_m.getExtras().getString("text_year_m"));

        tv_case_name_m.setText(data_p_m.getExtras().getString("text_case_name_m"));
        tv_nickname_m.setText(data_p_m.getExtras().getString("text_nickname_m"));
        tv_age_now_m.setText(data_p_m.getExtras().getString("text_age_now_m"));

        tv_color_body_m.setText(data_p_m.getExtras().getString("text_color_body_m"));
        tv_color_hair_m.setText(data_p_m.getExtras().getString("text_color_hair_m"));
        tv_color_eye_m.setText(data_p_m.getExtras().getString("text_color_eye_m"));

        tv_wiegth_m.setText(data_p_m.getExtras().getString("text_wiegth_m"));
        tv_length_m.setText(data_p_m.getExtras().getString("text_length_m"));

        tv_address_m.setText(data_p_m.getExtras().getString("text_address_m"));
        tv_note_m.setText(data_p_m.getExtras().getString("text_note_m"));



        String slat_m = data_p_m.getExtras().getString("text_lat_m");
        String slng_m = data_p_m.getExtras().getString("text_lng_m");



        String simg_m = data_p_m.getExtras().getString("text_img_m");

        S_user_id_m = data_p_m.getExtras().getString("text_us_id_m");

        Picasso.with(getApplicationContext())
                .load("http://192.168.1.4/atfalna_app/img_missing/" + simg_m)
                .into(img_p_m);


        gloablV = (GloablV) getApplicationContext();
        S_user_name_login = gloablV.getUser_name_login();
        S_user_id_login = gloablV.getUser_id_login();

        //  send  Comment_p_f
        ed_comm_p_m = findViewById(R.id.ed_comment_p_m);

        try{
            if (S_lat_m.equals("") && S_lng_m.equals("")) {
                tv_place_map_m.setText("لم يتم التحديد");
            } else {
                tv_place_map_m.setText("رؤيت المكان");
            }

            tv_place_map_m.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (S_lat_m.equals("") && S_lng_m.equals("")) {
                        Toast.makeText(getApplicationContext(), " لم يتم تحديد المكان ", Toast.LENGTH_LONG).show();
                    } else {
                        Intent Inshow_map = new Intent(getApplicationContext(), Map_show_palce_f.class);

                        Inshow_map.putExtra("key_show_lat_f", S_lat_m);
                        Inshow_map.putExtra("key_show_lng_f", S_lng_m);

                        startActivity(Inshow_map);
                    }

                }
            });
        }catch (Exception ex){

            Toast.makeText(getApplicationContext()," لم يتم تحديد المكان "+ ex, Toast.LENGTH_LONG).show();
        }

     //   get_comment_p_m();

    }

    public void initView_P_M() {
        tv_user_name_m = findViewById(R.id.tv_user_name_p_m);
        tv_date_m = findViewById(R.id.tv_date_m);
        tv_time_m = findViewById(R.id.tv_time_m);
        tv_city_m = findViewById(R.id.tv_city_m);
        tv_bonus_m = findViewById(R.id.tv_bonus_m);
        tv_phone_m = findViewById(R.id.tv_phone_m);
        tv_gender_m = findViewById(R.id.tv_gender_m);
        tv_day_m = findViewById(R.id.tv_day_m);
        tv_month_m = findViewById(R.id.tv_month_m);
        tv_year_m = findViewById(R.id.tv_year_m);
        tv_case_name_m = findViewById(R.id.tv_case_name_m);
        tv_nickname_m = findViewById(R.id.tv_nickname_m);
        tv_age_now_m = findViewById(R.id.tv_age_now_m);
        tv_color_body_m = findViewById(R.id.tv_color_body_m);
        tv_color_hair_m = findViewById(R.id.tv_color_hair_m);
        tv_color_eye_m = findViewById(R.id.tv_color_eye_m);
        tv_wiegth_m = findViewById(R.id.tv_wiegth_m);
        tv_length_m = findViewById(R.id.tv_length_m);
        tv_address_m = findViewById(R.id.tv_address_m);
        tv_note_m = findViewById(R.id.tv_note_m);
        tv_place_map_m=findViewById(R.id.tv_place_map_m);

        img_p_m =findViewById(R.id.img_m);

    }

    public void get_comment_p_m() {
        //show comment f
        final TextView tv_total_comm_m = findViewById(R.id.tv_total_comm_m);
        String url_comm_m = "http://192.168.1.4/atfalna_app/show_all_comment_missing.php?code_p_m=" + S_code_p_m;
        requestQueue = Volley.newRequestQueue(this);

        listcomment_m.clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_comm_m,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("allcomment_p_m");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject res = jsonArray.getJSONObject(i);
                                tv_total_comm_m.setText(String.valueOf(jsonArray.length()));
                                String code_comm = res.getString("code_comm");
                                String date_comm = res.getString("date_comm");
                                String time_comm = res.getString("time_comm");
                                String comment_m = res.getString("comment");
                                String us_id_comm = res.getString("us_id");
                                String user_name_comm = res.getString("user_name");
                                listcomment_m.add(new listitem_comm_m(code_comm,
                                        date_comm, time_comm,
                                        comment_m,
                                        us_id_comm, user_name_comm));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error ::"+ e , Toast.LENGTH_LONG).show();
                        }
                          listData_comm_m_alert();

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

    public void listData_comm_m_alert() {
        ListAdapter_comm ad_comm_m = new ListAdapter_comm(listcomment_m);
        listV_comm_m_alert.setAdapter(ad_comm_m);
    }

    class ListAdapter_comm extends BaseAdapter {

        ArrayList<listitem_comm_m> list_comm_m = new ArrayList<listitem_comm_m>();

        ListAdapter_comm(ArrayList<listitem_comm_m> listitme) {
            this.list_comm_m = listitme;
        }

        @Override
        public int getCount() {
            return list_comm_m.size();
        }

        @Override
        public Object getItem(int i) {
            return list_comm_m.get(i).comment_m;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View v_comm_m = layoutInflater.inflate(R.layout.row_itme_comment, null);

            TextView comment = v_comm_m.findViewById(R.id.tv_comment_f_row);
            TextView date_comm = v_comm_m.findViewById(R.id.tv_date_comm_f_row);
            TextView time_comm = v_comm_m.findViewById(R.id.tv_time_comm_f_row);
            TextView user_name_comm = v_comm_m.findViewById(R.id.tv_user_name_comm_f_row);

            String code_comm, us_id_comm;
            code_comm = list_comm_m.get(i).code_comm_m;
            us_id_comm = list_comm_m.get(i).us_id_m;
            comment.setText(list_comm_m.get(i).comment_m);
            date_comm.setText(list_comm_m.get(i).date_comm_m);
            time_comm.setText(list_comm_m.get(i).time_comm_m);
            user_name_comm.setText(list_comm_m.get(i).user_name_m);

            return v_comm_m;
        }
    }

    public void btn_show_comm_m_in_alert(View view) {

        LayoutInflater layoutInflater = LayoutInflater.from(Post_Missing.this);
        View vComm = layoutInflater.inflate(R.layout.item_alertdialog_comment_m, null);

        listV_comm_m_alert = vComm.findViewById(R.id.listV_all_comment_m_alert);

        listData_comm_m_alert();

        AlertDialog.Builder alert = new AlertDialog.Builder(Post_Missing.this);
        alert.setCancelable(true);
        alert.setView(vComm);

    }


    ProgressDialog pDialog_send_comm;
    public void btn_send_comment_p_m(final View view) {

        pDialog_send_comm = new ProgressDialog(Post_Missing.this);
        pDialog_send_comm.show();
        pDialog_send_comm.setMessage("لحظة و ستظهر التعليقات");
       // listcomment_m.clear();
        try {
            String comment_p_m = ed_comm_p_m.getText().toString().trim();
            String code_p_m = S_code_p_m ;

            if (comment_p_m.equals("")) {
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
                                Toast.makeText(Post_Missing.this, "تم أضافة التعليق ", Toast.LENGTH_SHORT).show();
                                ed_comm_p_m.setText("");
                               // get_comment_p_m();
                               // btn_show_comm_m_in_alert(view);
                                pDialog_send_comm.dismiss();

                            } else {
                                Toast.makeText(getApplicationContext(), "يوجد خطأ ( تاكد من البيانات )", Toast.LENGTH_SHORT).show();
                              //  get_comment_p_m();
                                pDialog_send_comm.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "error is : " + e, Toast.LENGTH_LONG).show();
                            pDialog_send_comm.dismiss();
                            get_comment_p_m();

                        }
                    }
                };
                Comment_Send_Data_M send_data_comment_m = new Comment_Send_Data_M(
                        comment_p_m, S_user_id_login, S_code_p_m, responseLisener);
                RequestQueue queue_comment_p_m = Volley.newRequestQueue(getApplicationContext());
                queue_comment_p_m.add(send_data_comment_m);
            }

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "error ex : " + ex, Toast.LENGTH_LONG).show();
        }


    }
}
