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

public class rec_v_ad_all_p_m extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int previousPosition = 0;
    String S_code_p_f;
    private List<listitem_m> List_Item;
    private Context context;

    public rec_v_ad_all_p_m(List<listitem_m> list_Item, Context context) {
        List_Item = list_Item;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.row_itme_p_m, viewGroup, false);
        return new rec_v_ad_all_p_m.MenuItemViewHolder(menu1);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        rec_v_ad_all_p_m.MenuItemViewHolder menuItemHolder = (rec_v_ad_all_p_m.MenuItemViewHolder) holder;

        menuItemHolder.tv_code_post_m_rec_v.setText(List_Item.get(position).getCode_p_m());
        menuItemHolder.tv_user_name_m_rec_v.setText(List_Item.get(position).getUser_name_m());
        menuItemHolder.tv_date_m_rec_v.setText(List_Item.get(position).getDate_p_m());
        menuItemHolder.tv_city_m_rec_v.setText(List_Item.get(position).getCity_m());
        menuItemHolder.tv_case_name_m_recy_v.setText(List_Item.get(position).getCase_name());
        menuItemHolder.tv_bonus_m_recy_v.setText(List_Item.get(position).getBonus_m() + "جنية");
        menuItemHolder.tv_phone_m_recy_v.setText(List_Item.get(position).getPhone_m());
        S_code_p_f = menuItemHolder.tv_code_post_m_rec_v.getText().toString();

        Picasso.with(context).load("http://192.168.1.4/atfalna_app/img_missing/" + List_Item
                .get(position).getImg_m())
                .into(menuItemHolder.img_m_rec_v);

//        menuItemHolder.img_f_rec_v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, List_Item.get(position).getUser_name_f()
//                        + List_Item.get(position).getCode_p_f(), Toast.LENGTH_SHORT).show();
//            }
//        });

        menuItemHolder.img_m_rec_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openPost_m = new Intent(context , Post_Missing.class);

                openPost_m.putExtra("text_code_m", List_Item.get(position).code_p_m);
                openPost_m.putExtra("text_date_m", List_Item.get(position).date_p_m);
                openPost_m.putExtra("text_time_m", List_Item.get(position).time_p_m);

                openPost_m.putExtra("text_city_m", List_Item.get(position).city_m);
                openPost_m.putExtra("text_gender_m", List_Item.get(position).gender_m);
                openPost_m.putExtra("text_phone_m", List_Item.get(position).phone_m);

                openPost_m.putExtra("text_day_m", List_Item.get(position).day_m);
                openPost_m.putExtra("text_month_m", List_Item.get(position).month_m);
                openPost_m.putExtra("text_year_m", List_Item.get(position).year_m);

                openPost_m.putExtra("text_color_eye_m", List_Item.get(position).color_eye);
                openPost_m.putExtra("text_color_hair_m", List_Item.get(position).color_hair);
                openPost_m.putExtra("text_color_body_m", List_Item.get(position).color_body);

                openPost_m.putExtra("text_address_m", List_Item.get(position).address_m);
                openPost_m.putExtra("text_bonus_m", List_Item.get(position).bonus_m);

                openPost_m.putExtra("text_length_m", List_Item.get(position).length_m);
                openPost_m.putExtra("text_wiegth_m", List_Item.get(position).wiegth_m);

                openPost_m.putExtra("text_case_name_m", List_Item.get(position).case_name);
                openPost_m.putExtra("text_nickname_m", List_Item.get(position).nickname);

                openPost_m.putExtra("text_age_now_m", List_Item.get(position).age_now);
                openPost_m.putExtra("text_note_m", List_Item.get(position).note_m);

                openPost_m.putExtra("text_img_m", List_Item.get(position).img_m);

                openPost_m.putExtra("text_us_id_m", List_Item.get(position).us_id_m);
                openPost_m.putExtra("text_user_name_m", List_Item.get(position).user_name_m);

                openPost_m.putExtra("text_lat_p_m", List_Item.get(position).lat_m);
                openPost_m.putExtra("text_lng_p_m", List_Item.get(position).lng_m);

                context.startActivity(openPost_m);
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
        TextView tv_user_name_m_rec_v, tv_city_m_rec_v, tv_date_m_rec_v, tv_code_post_m_rec_v ,
                 tv_case_name_m_recy_v ,tv_bonus_m_recy_v ,tv_phone_m_recy_v
                ;
        ImageView img_m_rec_v ;

        public MenuItemViewHolder(View view) {
            super(view);
            tv_user_name_m_rec_v = view.findViewById(R.id.tv_user_name_m_rec_v);
            tv_city_m_rec_v = view.findViewById(R.id.tv_city_m_rec_v);
            tv_date_m_rec_v = view.findViewById(R.id.tv_date_m_rec_v);
            tv_code_post_m_rec_v = view.findViewById(R.id.tv_code_post_m_list);
            tv_case_name_m_recy_v = view.findViewById(R.id.tv_name_case_m_rec_v);
            tv_bonus_m_recy_v = view.findViewById(R.id.tv_bonus_m_rec_v);
            tv_phone_m_recy_v = view.findViewById(R.id.tv_phone_m_rec_v);

            img_m_rec_v = view.findViewById(R.id.img_m_rec_v);



        }
    }
}
