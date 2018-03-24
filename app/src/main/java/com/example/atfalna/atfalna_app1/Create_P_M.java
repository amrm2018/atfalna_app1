package com.example.atfalna.atfalna_app1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.time.Year;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import maes.tech.intentanim.CustomIntent;

public class Create_P_M extends AppCompatActivity {

    ImageView img_m;
    String encodeimg_m;
    EditText ed_day_m, ed_year_m, ed_address_m, ed_age_now_m,
            ed_case_name_m, ed_nick_name_m, ed_phone_m,
            ed_note_m, ed_bouns_m;
    Spinner sp_month_m, sp_city_m,
            sp_color_eye_m, sp_color_hair_m, sp_color_body_m,
            sp_length_m, sp_wiegth_m;
    RadioButton rdo_male_m, rdo_female_m;
    String S_month_m, S_city_m,
            S_color_eye_m, S_color_hair_m, S_color_body_m,
            S_length_m, S_wiegth_m;
    GloablV gloablV;
    String S_email_us, S_user_id_m, S_user_name_m;

    TextView tv_show_user_id_m ,  Tv_show_lat_m , Tv_show_lng_m ;

    ImageButton imgb_add_place ,imgb_done_place ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_p_m);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_c_p_m); // get the reference of Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        img_m = findViewById(R.id.img_post_missing);

        ed_day_m = findViewById(R.id.ed_day_m);
        ed_year_m = findViewById(R.id.ed_year_m);
        ed_address_m = findViewById(R.id.ed_address_m);
        ed_age_now_m = findViewById(R.id.ed_age_new_m);
        ed_case_name_m = findViewById(R.id.ed_case_name_m);
        ed_nick_name_m = findViewById(R.id.ed_nickname_m);
        ed_phone_m = findViewById(R.id.ed_phone_m);
        ed_note_m = findViewById(R.id.ed_notes_m);
        ed_bouns_m = findViewById(R.id.ed_bouns_m);

        sp_month_m = findViewById(R.id.sp_month_m);
        sp_city_m = findViewById(R.id.sp_city_m);
        sp_color_eye_m = findViewById(R.id.sp_color_eye_m);
        sp_color_hair_m = findViewById(R.id.sp_color_hair_m);
        sp_color_body_m = findViewById(R.id.sp_color_body_m);
        sp_length_m = findViewById(R.id.sp_hength_m);
        sp_wiegth_m = findViewById(R.id.sp_wiegth_m);

        rdo_male_m = findViewById(R.id.rdo_male_m);
        rdo_female_m = findViewById(R.id.rdo_female_m);

        sp_month_m.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectspinnerMonth = adapterView.getItemAtPosition(i).toString();
                S_month_m = selectspinnerMonth;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });//--------sp_month

        sp_city_m.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectspinnerMonth = adapterView.getItemAtPosition(i).toString();
                S_city_m = selectspinnerMonth;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });//--------sp_city_m

        sp_color_eye_m.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectspinnerMonth = adapterView.getItemAtPosition(i).toString();
                S_color_eye_m = selectspinnerMonth;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });//--------sp_color_eye_m

        sp_color_hair_m.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectspinnerMonth = adapterView.getItemAtPosition(i).toString();
                S_color_hair_m = selectspinnerMonth;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });//--------sp_color_hair_m

        sp_color_body_m.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectspinnerMonth = adapterView.getItemAtPosition(i).toString();
                S_color_body_m = selectspinnerMonth;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });//--------sp_color_body_m

        sp_length_m.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectspinnerMonth = adapterView.getItemAtPosition(i).toString();
                S_length_m = selectspinnerMonth;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });//--------sp_length_m

        sp_wiegth_m.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectspinnerMonth = adapterView.getItemAtPosition(i).toString();
                S_wiegth_m = selectspinnerMonth;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });//--------sp_wiegth_m

        gloablV = (GloablV) getApplicationContext();
        S_email_us = gloablV.getEmail_user_login();
        S_user_id_m = gloablV.getUser_id_login();
        tv_show_user_id_m =findViewById(R.id.tv_show_user_id_m);
     //   tv_show_user_id_m.setText(S_user_id_m);

        S_user_name_m = gloablV.getUser_name_login();

        Tv_show_lat_m =findViewById(R.id.tv_show_lat_m);
        Tv_show_lat_m.setText(gloablV.getLat_m());

        Tv_show_lng_m =findViewById(R.id.tv_show_lng_m);
        Tv_show_lng_m.setText(gloablV.getLng_m());

        imgb_add_place =findViewById(R.id.imgb_add_place_m);
        imgb_done_place =findViewById(R.id.imgb_done_place_m);

        if (Tv_show_lat_m.getText().toString().equals("") && Tv_show_lng_m.getText().toString().equals("")) {
            imgb_add_place.setVisibility(View.VISIBLE);
            imgb_done_place.setVisibility(View.INVISIBLE);
        } else {
            imgb_done_place.setVisibility(View.VISIBLE);
            imgb_add_place.setVisibility(View.INVISIBLE);
        }

    }

    ProgressDialog pdialog;

    public void btn_create_post_m(View view) {

        pdialog = new ProgressDialog(this);
        pdialog.show();
        pdialog.setMessage("لحظة");

        // image_m
        Bitmap Bimg_m = ((BitmapDrawable) img_m.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bimg_m.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        encodeimg_m = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        // encodeimg_m  هو دا المتغير اللي شايل الصورة

        String S_gender_m;
        if (rdo_male_m.isChecked()) {
            S_gender_m = "ذكر";
        } else {
            S_gender_m = "أنثى";
        }
        String S_phone_m = ed_phone_m.getText().toString().trim();
        String S_day_m = ed_day_m.getText().toString().trim();
        String S_year_m = ed_year_m.getText().toString().trim();

        String S_address_m = ed_address_m.getText().toString().trim();
        String S_bouns_m = ed_bouns_m.getText().toString().trim();

        String S_case_name_m = ed_case_name_m.getText().toString().trim();
        String S_nickname_m = ed_nick_name_m.getText().toString().trim();
        String S_age_now_m = ed_age_now_m.getText().toString().trim();

        String S_note_m = ed_note_m.getText().toString().trim();

        String Lat_m =Tv_show_lat_m.getText().toString().trim();
        String Lng_m =Tv_show_lng_m.getText().toString().trim();


        if (S_day_m.isEmpty()) {
            ed_day_m.requestFocus();
            ed_day_m.setError("أكتب يوم");
            pdialog.dismiss();
        }
        else if (S_year_m.isEmpty()) {
            ed_year_m.requestFocus();
            ed_year_m.setError("أكتب السنة");
            pdialog.dismiss();
        }
        else  if (!validateyear(ed_year_m.getText().toString().trim())  ) {
            ed_year_m.requestFocus();
            ed_year_m.setError("أكتب السنة صح");
            pdialog.dismiss();
        }
        else if (S_address_m.isEmpty()) {
            ed_address_m.requestFocus();
            ed_address_m.setError("أكتب العنوان ");
            pdialog.dismiss();
        }
        else if (S_age_now_m.isEmpty()) {
            ed_age_now_m.requestFocus();
            ed_age_now_m.setError("أكتب عمرالحالة الان ");
            pdialog.dismiss();
        }
        else if (S_case_name_m.isEmpty()) {
            ed_case_name_m.requestFocus();
            ed_case_name_m.setError("أكتب اسم الحالة ");
            pdialog.dismiss();
        }
        else if (S_phone_m.isEmpty()) {
            ed_phone_m.requestFocus();
            ed_phone_m.setError("رقم التليفون");
            pdialog.dismiss();
        }
        else  if (!validatePhone(ed_phone_m.getText().toString().trim())  ) {
            ed_phone_m.requestFocus();
            ed_phone_m.setError("أكتب رقم التليفون صح");
            pdialog.dismiss();
        }
        else if (S_nickname_m.isEmpty()) {
            ed_nick_name_m.setText("");
            pdialog.dismiss();
        }
        else if (S_age_now_m.isEmpty()) {
            ed_age_now_m.requestFocus();
            ed_age_now_m.setError("أكتب عمرالحالة الان ");
            pdialog.dismiss();
        }

        else {
            Response.Listener<String> responseLisener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            Toast.makeText(getApplicationContext(), "تم نشر الحالة .. ِشكرا لك", Toast.LENGTH_SHORT).show();
                            pdialog.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "يوجد خطأ ( تاكد من البيانات المدخلة)", Toast.LENGTH_SHORT).show();
                            pdialog.dismiss();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            Create_P_M_Send_Data send_Data_missing = new Create_P_M_Send_Data(
                    S_city_m,
                    S_gender_m, S_phone_m,
                    S_day_m, S_month_m, S_year_m,
                    S_color_eye_m, S_color_hair_m, S_color_body_m,
                    S_address_m, S_bouns_m,
                    S_length_m, S_wiegth_m,
                    S_case_name_m, S_nickname_m, S_age_now_m,
                    S_note_m, encodeimg_m,
                    S_user_id_m, S_user_name_m,
                    Lat_m , Lng_m ,responseLisener);
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(send_Data_missing);

        }
    }

    private boolean validateyear(String year) {
        String YearVail = "^([12]{1})([90]{1})([890123]{1})([0-9]{1})$";
        Pattern pattern = Pattern.compile(YearVail);
        Matcher matcher = pattern.matcher(year);
        return matcher.matches();
    }

    private boolean validatePhone(String phones) {
        String phonepattery = "^([0]{1})([1]{1})([0125]{1})([0-9]{8})$";
        Pattern pattern = Pattern.compile(phonepattery);
        Matcher matcher = pattern.matcher(phones);
        return matcher.matches();
    }

    public void btn_pick_photo_m(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            img_m.setImageURI(uri); //  هتحوط الصورة فين
        }
    }

    public void go_select_place_m(View view) {
        startActivity( new Intent(getApplicationContext() , Create_P_M_Map.class));

    }
}
