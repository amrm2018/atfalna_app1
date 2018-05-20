package com.example.atfalna.atfalna_app1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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

import java.util.Objects;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    GloablV gloablV;
    String S_user_email ;
    int I_us_id ;
    String urlid;
    String sip = "http://192.168.1.4";

    TextView textView_user_name;
    ImageView img_refresh_data ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_user_name = findViewById(R.id.tv_user_name);
        img_refresh_data = findViewById(R.id.img_refresh_data);

        gloablV = (GloablV) getApplicationContext();
        S_user_email = gloablV.getEmail_user_login();
        gloablV.setIp_url(sip);

        urlid = sip + "/atfalna_app/show_userid.php?useremail=" + S_user_email;
        get_userid();//خاص بانه يجيب ال id بتاع ال user اللى يدخل الapp
        refresh();
    }

    public void get_userid() {

        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlid,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("user_id");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject res = jsonArray.getJSONObject(i);
                                String usid = res.getString("user_id");
                                String us_name = res.getString("user_name");
                                // textView_id.setText(usid);
                                textView_user_name.setText(us_name);
                                I_us_id =Integer.valueOf(usid);

                                gloablV.setUser_id_login(usid);
                                gloablV.setUser_name_login(us_name);
                                img_refresh_data.setVisibility(View.INVISIBLE);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "error is:" + e, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "Error");

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void btn_Logout(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("هل تريد الخروج ...")
                .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        getSharedPreferences("MyPref1", MODE_PRIVATE).edit().clear().commit();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        CustomIntent.customType(MainActivity.this, "right-to-left");
                    }
                }).setNegativeButton(" لا سابقي..", null).show();
    }

    public void go_create_p_f(View view) {

        if (I_us_id > 0){
        gloablV.setLat_f("");
        gloablV.setLng_f("");
        startActivity(new Intent(getApplicationContext(), Create_P_F.class));
        CustomIntent.customType(MainActivity.this, "left-to-right");
        }else
            Toast.makeText(getApplicationContext(), "خطا الاتصال بالانترنت", Toast.LENGTH_SHORT).show();
    }

    public void go_create_p_m(View view) {

        if (I_us_id > 0){
        gloablV.setLat_m("");
        gloablV.setLng_m("");
        startActivity(new Intent(getApplicationContext(), Create_P_M.class));
        CustomIntent.customType(MainActivity.this, "left-to-right");
        }else
            Toast.makeText(getApplicationContext(), "خطا الاتصال بالانترنت", Toast.LENGTH_SHORT).show();

    }

    public void go_all_p_f(View view) {
        startActivity(new Intent(getApplicationContext(), All_P_F_Rec_v.class));
        CustomIntent.customType(MainActivity.this, "left-to-right");
    }

    public void go_all_p_m(View view) {
        startActivity(new Intent(getApplicationContext(), All_P_M_Rec_v.class));
        CustomIntent.customType(MainActivity.this, "left-to-right");
    }

    public void go_profil(View view) {
        startActivity(new Intent(getApplicationContext(), all_p_f_usid.class));
        CustomIntent.customType(MainActivity.this, "bottom-to-up");
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("هل تريد الخروج ...")
                .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getSharedPreferences("MyPref1", MODE_PRIVATE).edit().clear().commit();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        CustomIntent.customType(MainActivity.this, "right-to-left");
                    }
                }).setNegativeButton(" لا سابقي..", null).show();
    }

    public void go_all_p_f_rec(View view) {
        startActivity(new Intent(getApplicationContext(), All_P_F_Rec_v.class));
        CustomIntent.customType(MainActivity.this, "left-to-right");
    }


    public void refresh_data(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        CustomIntent.customType(MainActivity.this, "fadein-to-fadeout");
        refresh();

    }
    public void refresh() {
        if ( I_us_id == 0 ){
            img_refresh_data.setVisibility(View.VISIBLE);
        }else
            img_refresh_data.setVisibility(View.INVISIBLE);

    }
}





/**
 * left-to-right
 * right-to-left
 * bottom-to-up
 * up-to-bottom
 * fadein-to-fadeout  اختفاء بهدوء
 * rotateout-to-rotatein
 **/