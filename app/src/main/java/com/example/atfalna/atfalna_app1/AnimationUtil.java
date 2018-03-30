package com.example.atfalna.atfalna_app1;

/**
 * Created by amr1 on 3/27/2018.
 */

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

public class AnimationUtil {

    public static void animate(RecyclerView.ViewHolder holder , boolean goesDown){

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator animatorTranslateY = ObjectAnimator
                                      .ofFloat(holder.itemView,
                                    "translationY",
                                    goesDown==true ? 1000 : -1000, 1);
        animatorTranslateY.setDuration(1000);
        animatorSet.playTogether(animatorTranslateY);
        animatorSet.start();

    }
}