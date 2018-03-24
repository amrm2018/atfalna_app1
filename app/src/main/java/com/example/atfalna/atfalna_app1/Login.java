package com.example.atfalna.atfalna_app1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import maes.tech.intentanim.CustomIntent;

public class Login extends AppCompatActivity {

    EditText Login_email, Login_password;
    CheckBox chk_remember;

    GloablV gloablV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        gloablV = (GloablV) getApplicationContext();

        SharedPreferences pref = getSharedPreferences("MyPref1", MODE_PRIVATE);
        String email = pref.getString("email", null);
        String password = pref.getString("password", null);
        if (email != null && password != null) {
            gloablV.setEmail_user_login(email);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        chk_remember = findViewById(R.id.chk_remember);

        Login_email = findViewById(R.id.ed_email_log2);
        Login_password = findViewById(R.id.ed_pass_log2);


    }


    ProgressDialog dialog;

    //Login
    public void Login_btn_Login(View view) {

        dialog = new ProgressDialog(Login.this);
        dialog.show();
        dialog.setMessage(" لحظة");

        final String Log_in_email = Login_email.getText().toString().trim();
        final String Log_in_password = Login_password.getText().toString().trim();

        if (Log_in_email.isEmpty()) {
            Login_email.setError("البريد الالكترونى فارغ");
            Login_email.requestFocus();
            dialog.dismiss();
        } else if (!validateEmail(Login_email.getText().toString())) {
            Login_email.setError("البريد الالكترونى غير صحيح");
            Login_email.requestFocus();
            dialog.dismiss();
        } else if (Log_in_password.isEmpty()) {
            Login_password.setError("ارقم السرى فارغ");
            Login_password.requestFocus();
            dialog.dismiss();
        } else {
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        if (success) {
                            gloablV.setEmail_user_login(Log_in_email);
                            if (chk_remember.isChecked()) {
                                getSharedPreferences("MyPref1", MODE_PRIVATE)
                                        .edit().putString("email", Log_in_email)
                                        .putString("password", Log_in_password)
                                        .apply();
                            }
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            dialog.dismiss();
                        } else {
                            Toast.makeText(Login.this, "البيانات غير صحيحة", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "error is : " + e, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }
            };

            Login_Send_Data login_send_data = new Login_Send_Data(Log_in_email, Log_in_password, responseListener);
            RequestQueue queue_login = Volley.newRequestQueue(Login.this);
            queue_login.add(login_send_data);

        }
    }

    private boolean validateEmail(String email) {
        String emailpattery = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{3,})$";
        Pattern pattern = Pattern.compile(emailpattery);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void go_main(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void go_registration(View view) {
        startActivity(new Intent(getApplicationContext(), Registration.class));
    }


    @Override
    public void onBackPressed() {

        Login_email.requestFocus();

//        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
//
//        builder .setMessage("هل تريد الخروج ...")
//                .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                }).setNegativeButton(" لا", null).show();
    }


}
