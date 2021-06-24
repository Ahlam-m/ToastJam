package com.ahlam.toastjam;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.GravityInt;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;


public class ToastJam {

    @SuppressLint("StaticFieldLeak")
    private static ToastJam toastjam;
    private final TextView view;
    private final LinearLayout parent;

    private final Context context;

    private @ColorInt int background_color;
    private @ColorInt int text_color;
    private @DrawableRes int tshape;
    private @GravityInt int tgravity;
    private double duration_sec;


    private Runnable runnable;
    private Handler toastjamHandler;

    private ToastJam(Context context) {
        this.context = context;
        parent = (LinearLayout) View.inflate(context, com.ahlam.toastjam.R.layout.toastjam_layout, null);
        view = (TextView) parent.findViewById(com.ahlam.toastjam.R.id.toastjam);

        //default values
        background_color = Color.parseColor("#B3333333");//gray with opacity = 70%
        text_color = Color.WHITE;
        tshape = com.ahlam.toastjam.R.drawable.round_square;
        tgravity = Gravity.BOTTOM;
        duration_sec = 3;
    }

    public static ToastJam setup(Context context, String msg) {
        toastjam = new ToastJam(context);
        toastjam.view.setText(msg);
        return toastjam;
    }

    public ToastJam setGravity(com.ahlam.toastjam.entities.TGravity gravity) {
        try {
            switch (gravity) {
                case TOP:
                    tgravity = Gravity.TOP;
                    break;
                case CENTER:
                    tgravity = Gravity.CENTER_VERTICAL;
                    break;
                default:
                    tgravity = Gravity.BOTTOM;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toastjam;
    }


    public ToastJam setColor(@ColorInt int color) {
        try {
            background_color = color;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toastjam;
    }


    public ToastJam setShape(com.ahlam.toastjam.entities.TShape shape) {
        try {
            switch (shape) {
                case SQUARE:
                    tshape = com.ahlam.toastjam.R.drawable.square;
                    break;
                case RAWNED:
                    tshape = com.ahlam.toastjam.R.drawable.rawned;
                    break;
                case ELLIPSE:
                    tshape = com.ahlam.toastjam.R.drawable.ellipse;
                    break;
                case ROUND_SQUARE:
                    tshape = com.ahlam.toastjam.R.drawable.round_square;
                    break;
                default:
                    tshape = com.ahlam.toastjam.R.drawable.oval;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toastjam;
    }

    public ToastJam setTextColor(@ColorInt int color) {
        try {
            text_color = color;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toastjam;
    }

    public ToastJam setDurationInSec(double dur) {
        duration_sec = dur;
        return toastjam;
    }

    private Drawable prepareBackground() {
        try {
            Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, tshape);
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            wrappedDrawable.setColorFilter(new PorterDuffColorFilter(background_color, PorterDuff.Mode.SRC_IN));
            return wrappedDrawable;

        } catch (Exception e) {
            e.printStackTrace();
            return AppCompatResources.getDrawable(context, com.ahlam.toastjam.R.drawable.round_square);
        }
    }

    public void start() {

        //create toastjam
        toastjam.view.setTextColor(text_color);
        toastjam.view.setBackground(prepareBackground());

        //parent gravity
        parent.setGravity(tgravity | Gravity.CENTER_HORIZONTAL);

        //add toastjam to the window
        final ViewGroup rootview = (ViewGroup) ((Activity) context).findViewById(android.R.id.content);
        // {1} //
        rootview.addView(parent);


        //hide toast after duration
        toastjamHandler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                //on duration pass -> hide it
                // {4} //
                AnimClass.hide(toastjam.view).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        // {5} //
                        rootview.removeView(parent);
                        toastjamHandler.removeCallbacks(runnable);
                    }
                });
            }
        };

        // {2} //
        AnimClass.show(toastjam.view).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //calculate duration (of full opacity) in millisecond
                long showtime = Math.round(duration_sec * 1000.0);
                // {3} //
                toastjamHandler.postDelayed(runnable, showtime);
            }
        });
    }
}
