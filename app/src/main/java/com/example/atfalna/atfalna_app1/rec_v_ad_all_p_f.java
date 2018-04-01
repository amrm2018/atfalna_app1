package com.example.atfalna.atfalna_app1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import maes.tech.intentanim.CustomIntent;

/**
 * Created by amr1 on 4/1/2018.
 */

public class rec_v_ad_all_p_f extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int previousPosition = 0;
    String S_code_p_f;
    private List<listitem_f> List_Item;
    private Context context;

    public rec_v_ad_all_p_f(List<listitem_f> list_Item, Context context) {
        List_Item = list_Item;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.row_itme_p_f, viewGroup, false);
        return new rec_v_ad_all_p_f.MenuItemViewHolder(menu1);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        rec_v_ad_all_p_f.MenuItemViewHolder menuItemHolder = (rec_v_ad_all_p_f.MenuItemViewHolder) holder;

        menuItemHolder.tv_user_name_f_rec_v.setText(List_Item.get(position).getUser_name_f());
        menuItemHolder.tv_date_f_rec_v.setText(List_Item.get(position).getDate_p_f());
        menuItemHolder.tv_code_post_f_rec_v.setText(List_Item.get(position).getCode_p_f());
        S_code_p_f = menuItemHolder.tv_code_post_f_rec_v.getText().toString();

        Picasso.with(context).load("http://192.168.1.3/atfalna_app/img_found/" + List_Item
                .get(position).getImg_f())
                .into(menuItemHolder.img_f_rec_v);

//        menuItemHolder.img_f_rec_v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, List_Item.get(position).getUser_name_f()
//                        + List_Item.get(position).getCode_p_f(), Toast.LENGTH_SHORT).show();
//            }
//        });

        menuItemHolder.img_f_rec_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openPost = new Intent(context , Post_found.class);

                openPost.putExtra("text_code_p_f", List_Item.get(position).getCode_p_f());
                openPost.putExtra("text_date_p_f", List_Item.get(position).getDate_p_f());
                openPost.putExtra("text_time_p_f", List_Item.get(position).getTime_p_f());

                openPost.putExtra("text_city_f"  , List_Item.get(position).getCity_f());

                openPost.putExtra("text_day_f", List_Item.get(position).getDay_f());
                openPost.putExtra("text_month_f", List_Item.get(position).getMonth_f());
                openPost.putExtra("text_year_f", List_Item.get(position).getYear_f());

                openPost.putExtra("text_gender_f", List_Item.get(position).getGender_f());
                openPost.putExtra("text_phone_f", List_Item.get(position).getPhone_f());

                openPost.putExtra("text_place_case_f", List_Item.get(position).getPlace_case_f());
                openPost.putExtra("text_info_case_f", List_Item.get(position).getInfo_case_f());

                openPost.putExtra("text_img_f", List_Item.get(position).getImg_f());

                openPost.putExtra("text_us_id_f", List_Item.get(position).getUs_id_f());
                openPost.putExtra("text_user_name_f", List_Item.get(position).getUser_name_f());

                openPost.putExtra("text_lat_f", List_Item.get(position).getLat_f());
                openPost.putExtra("text_lng_f", List_Item.get(position).getLng_f());

                context.startActivity(openPost);
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
        TextView tv_user_name_f_rec_v, tv_city_f_rec_v, tv_date_f_rec_v, tv_code_post_f_rec_v;
        ImageView img_f_rec_v;

        public MenuItemViewHolder(View view) {
            super(view);
            tv_user_name_f_rec_v = view.findViewById(R.id.tv_user_name_m_rec_v);
            tv_city_f_rec_v = view.findViewById(R.id.tv_city_f_rec_v);
            tv_date_f_rec_v = view.findViewById(R.id.tv_date_m_rec_v);
            tv_code_post_f_rec_v = view.findViewById(R.id.tv_code_post_f_rec_v);
            img_f_rec_v = view.findViewById(R.id.img_f_rec_v);



        }
    }
}
