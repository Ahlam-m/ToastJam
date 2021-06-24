package com.ahlam.toastjam;

import android.view.View;
import android.view.ViewPropertyAnimator;

class AnimClass {

    public static ViewPropertyAnimator show(View view){

        return view.animate()
                .alpha(1f)
                .setDuration(500);
    }

    public static ViewPropertyAnimator hide(View view){

        return view.animate()
                .alpha(0f)
                .setDuration(500);
    }

}
