package com.example.atfalna.atfalna_app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Edit_p_f extends AppCompatActivity {

    EditText ed_day_edit_f , ed_year_edit_f , ed_place_edit_f , ed_info_edit_f ,
            ed_phone_edit_f , ed_month_edit_f , ed_city_edit_f ;
    String S_code_p_f ;
    GloablV gloablV ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_p_f);

        gloablV = (GloablV) getApplicationContext();

        intoview();

        Intent intent_edit_f = getIntent();

        S_code_p_f = intent_edit_f.getExtras().getString("kup_code_p_f");

        ed_city_edit_f   .setText(intent_edit_f.getExtras().getString("kup_city_f"  ).trim());

        ed_day_edit_f    .setText(intent_edit_f.getExtras().getString("kup_day_f"   ).trim());
        ed_month_edit_f  .setText(intent_edit_f.getExtras().getString("kup_month_f" ).trim());
        ed_year_edit_f   .setText(intent_edit_f.getExtras().getString("kup_year_f"  ).trim());

        ed_phone_edit_f  .setText(intent_edit_f.getExtras().getString("kup_phone_f" ).trim());
        ed_place_edit_f  .setText(intent_edit_f.getExtras().getString("kup_place_f" ).trim());
        ed_info_edit_f   .setText(intent_edit_f.getExtras().getString("kup_info_f"  ).trim());

    }
    public void intoview(){
        ed_day_edit_f   =findViewById(R.id.ed_day_edit_f);
        ed_year_edit_f  =findViewById(R.id.ed_year_edit_f);
        ed_place_edit_f =findViewById(R.id.ed_place_edit_f);
        ed_info_edit_f  =findViewById(R.id.ed_info_edit_f);
        ed_phone_edit_f =findViewById(R.id.ed_phone_edit_f);
        ed_month_edit_f =findViewById(R.id.ed_month_edit_f);
        ed_city_edit_f  =findViewById(R.id.ed_city_edit_f);

    }

}
