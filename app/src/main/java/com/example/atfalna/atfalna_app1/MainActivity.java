package com.example.atfalna.atfalna_app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import maes.tech.intentanim.CustomIntent;

import static maes.tech.intentanim.CustomIntent.customType;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    GloablV gloablV ;

    String S_user_email , S_user_id , S_user_name ;

    String urlid ;

    TextView textView_id ,textView_email , textView_user_name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // textView_email=findViewById(R.id.tv_show_email_main);

       // textView_id=findViewById(R.id.tv_show_id_main);
        textView_user_name=findViewById(R.id.tv_user_name);

        gloablV = (GloablV)getApplicationContext();
        S_user_email = gloablV.getEmail_user_login();
        //textView_email.setText(gloablV.getEmail_user_login());


        urlid ="http://192.168.1.4/atfalna_app/show_userid.php?useremail="+ S_user_email;
        get_userid();//خاص بانه يجيب ال id بتاع ال user اللى يدخل الapp

    }

    public void get_userid() {

        requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, urlid,
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

                                gloablV.setUser_id_login(usid);
                                gloablV.setUser_name_login(us_name);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "error is:"+ e , Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley" , "Error");

            }
        });requestQueue.add(jsonObjectRequest);
    }

    public void btn_Logout(View view) {
        getSharedPreferences("MyPref1",MODE_PRIVATE).edit().clear().commit();
        startActivity(new Intent(getApplicationContext(),Login.class));
        CustomIntent.customType(MainActivity.this,"right-to-left");
    }

    public void go_create_p_f(View view) {

        gloablV.setLat_f("");
        gloablV.setLng_f("");

        startActivity(new Intent(getApplicationContext(), Create_P_F.class));
       CustomIntent.customType(MainActivity.this,"bottom-to-up");

    }

    public void go_create_p_m(View view) {

        gloablV.setLat_m("");
        gloablV.setLng_m("");
        startActivity( new Intent(getApplicationContext(),Create_P_M.class));
        CustomIntent.customType(MainActivity.this,"bottom-to-up");
    }

    public void go_all_p_f(View view) {
        startActivity( new Intent(getApplicationContext(),All_P_F.class));
        CustomIntent.customType(MainActivity.this,"left-to-right");
    }

    public void go_all_p_m(View view) {
        startActivity( new Intent(getApplicationContext(),All_P_M.class));
        CustomIntent.customType(MainActivity.this,"left-to-right");
    }

    public void go_profil(View view) {
        startActivity( new Intent(getApplicationContext(),Profile.class));
        CustomIntent.customType(MainActivity.this,"up-to-bottom");
    }
}


/**
        left-to-right
        right-to-left
        bottom-to-up
        up-to-bottom
        fadein-to-fadeout  اختفاء بهدوء
        rotateout-to-rotatein

 **/