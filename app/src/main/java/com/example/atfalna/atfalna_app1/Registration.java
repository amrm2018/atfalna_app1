package com.example.atfalna.atfalna_app1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    EditText name , email , password1 , password2 , phone ;
    RadioButton RD_male , RD_female ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name =(EditText) findViewById(R.id.ed_username_reg2);
        email =(EditText) findViewById(R.id.ed_email_reg2);
        password1 =(EditText) findViewById(R.id.ed_pass1_reg2);
        password2 =(EditText) findViewById(R.id.ed_pass2_reg2);
        phone =(EditText) findViewById(R.id.ed_phone_reg2);

        RD_male=findViewById(R.id.rdo_male);
        RD_female=findViewById(R.id.rdo_female);

    }
    ProgressDialog dialog;
    //Send_Data_to_Serveries
    public void btn_Reg(View view) {

        dialog =new ProgressDialog(Registration.this);
        dialog.show();
        dialog.setMessage(" لحظة");

        String Ename = name.getText().toString();
        String Email = email.getText().toString().trim();
        String Pass = password1.getText().toString().trim();
        String Pass2 = password2.getText().toString().trim();
        String Phone = phone.getText().toString().trim();
        String Gender ;
        if (RD_male.isChecked()){
            Gender="ذكر";
        }else Gender="أنثي";

        if (!Pass2.equals(Pass))
        {
            password2.setError("االرقم السرى غير مطابق");
            password1.requestFocus();
            dialog.dismiss();
        }
        else if(Ename.isEmpty())
        {
            name.setError("أكتب اسمك هنا");
            name.requestFocus();
            dialog.dismiss();
        }
        else if (Email.isEmpty())
        {
            email.setError("البريد الالكترونى فارغ");
            email.requestFocus();
            dialog.dismiss();
        }
        else if (!validateEmail( email.getText().toString()))
        {
            email.setError("البريد الالكترونى غير صحيح");
            email.requestFocus();
            dialog.dismiss();
        }
        else if (Pass.isEmpty())
        {
            password1.setError("ارقم السرى فارغ");
            password1.requestFocus();
            dialog.dismiss();
        }
        else if (Phone.isEmpty())
        {
            phone.setError("ادخل رقم تليفونك");
            phone.requestFocus();
            dialog.dismiss();
        }
        else if (!validatePhone(phone.getText().toString()))
        {
            phone.setError("رقم الهاتف غير صحيح");
            phone.requestFocus();
            dialog.dismiss();
        }
        else
        {
            Response.Listener<String> responseLisener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            Toast.makeText( Registration.this , "تم التسجيل بنجاح,أذهب و سجل دخول", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Login.class));
                            dialog.dismiss();
                        } else {
                            Toast.makeText( Registration.this , "يوجد خطأ ( تاكد من البريد او التليفون )", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "error is : " + e, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            };
            Registration_Send_Data Registration_send_Data = new Registration_Send_Data(Ename, Email, Pass ,Gender, Phone, responseLisener);
            RequestQueue queue_reg = Volley.newRequestQueue(getApplicationContext());
            queue_reg.add(Registration_send_Data);
        }
    }

    private boolean validatePhone(String phones)
    {
        String phonepattery = "^([0]{1})([1]{1})([0125]{1})([0-9]{8})$";
        Pattern pattern = Pattern.compile(phonepattery);
        Matcher matcher = pattern.matcher(phones);
        return  matcher.matches();
    }

    private boolean validateEmail(String email)
    {
        String emailpattery = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{3,})$";
        Pattern pattern = Pattern.compile(emailpattery);
        Matcher matcher = pattern.matcher(email);
        return  matcher.matches();
    }


    public void go_login(View view) {
        finish();
    }
}
