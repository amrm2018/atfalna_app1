package com.example.atfalna.atfalna_app1;

/**
 * Created by amr1 on 3/27/2018.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import maes.tech.intentanim.CustomIntent;


public class Rec_V_Ad_f_id extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int previousPosition = 0;

    String S_code_p_f ;
    private List<listitem_f_usid> List_Item;
    private Context context;

    public Rec_V_Ad_f_id(List<listitem_f_usid> list_Item, Context context) {
        List_Item = list_Item;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.row_item_p_f_us_id, viewGroup, false);
        return new MenuItemViewHolder(menu1);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MenuItemViewHolder menuItemHolder = (MenuItemViewHolder) holder;

        menuItemHolder.tv_username_f_row.setText(List_Item.get(position).getUser_name_f());
        menuItemHolder.tv_date_f_row.setText(List_Item.get(position).getDate_p_f());
        menuItemHolder.tv_code_post_f_row.setText(List_Item.get(position).getCode_p_f());
        S_code_p_f =menuItemHolder.tv_code_post_f_row.getText().toString();

        Picasso.with(context).load("http://192.168.1.4/atfalna_app/img_found/"+List_Item
                .get(position).getImg_f())
                .into(menuItemHolder.img_f_row);

        menuItemHolder.img_f_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, List_Item.get(position).getUser_name_f()
                        + List_Item.get(position).getCode_p_f() , Toast.LENGTH_SHORT).show();
            }
        });

        menuItemHolder.img_edit_f_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_edit_f =new Intent(context , Edit_p_f.class );

                intent_edit_f.putExtra("kup_code_p_f", List_Item.get(position).getCode_p_f());

                intent_edit_f.putExtra("kup_city_f"  ,   List_Item.get(position).getCity_f());

                intent_edit_f.putExtra("kup_day_f"   ,    List_Item.get(position).getDay_f());
                intent_edit_f.putExtra("kup_month_f" ,  List_Item.get(position).getMonth_f());
                intent_edit_f.putExtra("kup_year_f"  ,   List_Item.get(position).getYear_f());

                intent_edit_f.putExtra("kup_gender_f", List_Item.get(position).getGender_f());
                intent_edit_f.putExtra("kup_phone_f" ,  List_Item.get(position).getPhone_f());

                intent_edit_f.putExtra("kup_place_f" ,  List_Item.get(position).getPlace_case_f());
                intent_edit_f.putExtra("kup_info_f"  ,   List_Item.get(position).getInfo_case_f());

                intent_edit_f.putExtra("kup_gender_f",   List_Item.get(position).getGender_f());

                context.startActivity(intent_edit_f);
                CustomIntent.customType(context, "left-to-right");

            }
        });


        if (position > previousPosition) { //scrolling DOWN
            AnimationUtil.animate(menuItemHolder, true);
        } else { // scrolling UP
            AnimationUtil.animate(menuItemHolder, false);
        }
        previousPosition = position;
    }

    @Override
    public int getItemCount() {
        return (null != List_Item ? List_Item.size() : 0);
    }
    protected class MenuItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_username_f_row , tv_city_f_row ,tv_date_f_row  , tv_code_post_f_row;
        ImageView img_f_row , img_edit_f_row;

        public MenuItemViewHolder(View view) {
            super(view);
            tv_username_f_row =view.findViewById(R.id.tv_user_name_m_rec_v);
            tv_city_f_row =view.findViewById(R.id.tv_city_f_row);
            tv_date_f_row =view.findViewById(R.id.tv_date_m_rec_v);
            tv_code_post_f_row =view.findViewById(R.id.tv_code_post_f_row);
            img_f_row =view.findViewById(R.id.img_f_row);

            img_edit_f_row =view.findViewById(R.id.img_edit_f_row);

        }
    }
}
