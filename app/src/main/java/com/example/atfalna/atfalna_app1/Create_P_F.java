package com.example.atfalna.atfalna_app1;

import android.annotation.SuppressLint;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import maes.tech.intentanim.CustomIntent;

public class Create_P_F extends AppCompatActivity {


    EditText ED_day, ED_year, ED_phone, ED_place_the_case, ED_info_the_case;
    Spinner SP_month, SP_city;
    RadioButton RD_male, RD_female;
    TextView TV_show_month, TV_show_City, TV_show_user_email, tv_show_user_name_f, tv_show_user_id_f ,
    Tv_show_lat_f , Tv_show_lng_f ;
    GloablV gloablV;

    // send image
    ImageView imgV_f;
    String encodeimg_f  , S_user_id ;

    ImageButton imgb_add_place ,imgb_done_place ;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_p_f);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_c_p_f); // get the reference of Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ED_day = findViewById(R.id.ed_day);
        ED_year = findViewById(R.id.ed_year);
        ED_phone = findViewById(R.id.ed_phone);
        ED_place_the_case = findViewById(R.id.ed_place_the_case);
        ED_info_the_case = findViewById(R.id.ed_info_the_case);

        RD_male = findViewById(R.id.rdo_male);
        RD_female = findViewById(R.id.rdo_female);

        SP_month = findViewById(R.id.spinner_month);
        SP_city = findViewById(R.id.sp_city);
        TV_show_month = findViewById(R.id.tv_show_spinner_month);
        TV_show_City = findViewById(R.id.tv_show_spinner_city);

        SP_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectspinnerMonth = adapterView.getItemAtPosition(i).toString();
                TV_show_month.setText(selectspinnerMonth);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });//--------SP_month

        SP_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectspinnerCity = adapterView.getItemAtPosition(i).toString();
                TV_show_City.setText(selectspinnerCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });//----------SP_city

        gloablV = (GloablV) getApplicationContext();
        TV_show_user_email = findViewById(R.id.tv_show_email_user);
        TV_show_user_email.setText(gloablV.getEmail_user_login());

        tv_show_user_name_f = findViewById(R.id.tv_show_user_name_f);
        tv_show_user_name_f.setText(gloablV.getUser_name_login());

        tv_show_user_id_f = findViewById(R.id.tv_show_user_id_f);
        tv_show_user_id_f.setText(gloablV.getUser_id_login());

        S_user_id = gloablV.getUser_id_login();
//        if (S_user_id.equals("")){
//            Toast.makeText(getApplicationContext(), "لا يوجد اتصال بالانترنت", Toast.LENGTH_SHORT).show();
//        }

        //-- send image
        imgV_f = findViewById(R.id.img_post_found);

        Tv_show_lat_f =findViewById(R.id.tv_show_lat_f);
        Tv_show_lat_f.setText(gloablV.getLat_f());

        Tv_show_lng_f =findViewById(R.id.tv_show_lng_f);
        Tv_show_lng_f.setText(gloablV.getLng_f());

        imgb_add_place =findViewById(R.id.imgb_add_place);
        imgb_done_place =findViewById(R.id.imgb_done_place);

        if (Tv_show_lat_f.getText().toString().equals("") && Tv_show_lng_f.getText().toString().equals("")) {
            imgb_add_place.setVisibility(View.VISIBLE);
            imgb_done_place.setVisibility(View.INVISIBLE);
        } else {
            imgb_done_place.setVisibility(View.VISIBLE);
            imgb_add_place.setVisibility(View.INVISIBLE);
        }



    }

    public void initView() {

    }

    //---------------------------
    ProgressDialog dialog;

    public void btn_create_post_found(View view) {

        dialog = new ProgressDialog(this);
        dialog.show();
        dialog.setMessage("لحظة");

        // image
        Bitmap Bimg = ((BitmapDrawable) imgV_f.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bimg.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        encodeimg_f = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        // encodeimg  هو دا المتغير اللي شايل الصورة

        String City = TV_show_City.getText().toString();

        String Day = ED_day.getText().toString().trim();
        String Month = TV_show_month.getText().toString();
        String Year = ED_year.getText().toString().trim();

        String Gender_case;
        if (RD_male.isChecked()) {
            Gender_case = "ذكر";
        } else
            Gender_case = "أنثى";
        String Phone = ED_phone.getText().toString().trim();

        String Place = ED_place_the_case.getText().toString();
        String Info = ED_info_the_case.getText().toString();
        // int Us_id_f = Integer.parseInt( tv_show_user_id_f.getText().toString());
        String Us_id_f = tv_show_user_id_f.getText().toString();
        String User_name_f = tv_show_user_name_f.getText().toString().trim();



        String Lat_f =Tv_show_lat_f.getText().toString().trim();
        String Lng_f =Tv_show_lng_f.getText().toString().trim();



        if (Day.isEmpty()) {
            ED_day.setError("اكتب اليوم");
            dialog.dismiss();
        } else if (Year.isEmpty()) {
            ED_year.setError("اكتب السنة");
            dialog.dismiss();
        } else if (!validateyear(ED_year.getText().toString().trim())) {
            ED_year.setError("رقم السنة غير صحيح");
            ED_year.requestFocus();
            dialog.dismiss();
        } else if (Place.isEmpty()) {
            ED_place_the_case.setError("أكتب مكان الحالة بالتفصيل");
            ED_place_the_case.requestFocus();
            dialog.dismiss();
        }
        else if (imgV_f.getDrawable() == null) {
            Toast.makeText(getApplicationContext(), "أختار صورة للحالة", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
        else {
            Response.Listener<String> responseLisener_p_f = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            Toast.makeText(getApplicationContext(), "تم نشر الاعلان", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "يوجد خطأ ( تاكد من البيانات)", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            Create_P_F_Send_Data send_Data_found = new Create_P_F_Send_Data(
                    encodeimg_f,
                    City,
                    Day, Month, Year,
                    Gender_case, Phone,
                    Place, Info,
                    Us_id_f, User_name_f,
                    Lat_f, Lng_f,
                    responseLisener_p_f);
            RequestQueue queue_p_f = Volley.newRequestQueue(getApplicationContext());
            queue_p_f.add(send_Data_found);

        }
    }

    private boolean validateyear(String year) {
        String YearVail = "^([12]{1})([90]{1})([8901]{1})([0-9]{1})$";
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

    public void btn_pick_photo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            imgV_f.setImageURI(uri); //  هتحوط الصورة فين
        }
    }

    public void go_select_place_f(View view) {
        startActivity( new Intent(getApplicationContext() , Create_P_F_Map.class));

    }
}
