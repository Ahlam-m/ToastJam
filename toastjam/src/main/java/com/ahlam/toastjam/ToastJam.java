package com.ahlam.toastjam;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.PrecomputedText;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.GravityInt;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.ahlam.toastjam.entities.TGravity;
import com.ahlam.toastjam.entities.TShape;

import java.time.Duration;

import static android.content.Context.WINDOW_SERVICE;


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


    private ToastJam(Context context){
        this.context = context;
        parent = (LinearLayout) View.inflate(context, R.layout.toastjam_layout, null);
        view = (TextView) parent.findViewById(R.id.toastjam);

        //default values
        background_color = ContextCompat.getColor(context, R.color.background_color); //-867414964;//transparent gray
        text_color = Color.WHITE;
        tshape = R.drawable.round_square;
        tgravity = Gravity.BOTTOM;
    }


    public static ToastJam setup(Context context, String msg){
        toastjam = new ToastJam(context);
        toastjam.view.setText(msg);
        return toastjam;
    }

    public ToastJam setGravity(TGravity gravity){
        try {
        switch (gravity){
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
        } catch (Exception e){
            e.printStackTrace();
        }
        return toastjam;
    }


    public ToastJam setToastJamColor(@ColorInt int color){
        try{
            background_color = color;
        } catch (Exception e){
            e.printStackTrace();
        }
        return toastjam;
    }


    public ToastJam setShape(TShape shape){
        try {
            switch (shape) {
                case SQUARE:
                    tshape = R.drawable.square;
                    break;
                case RAWNED:
                    tshape = R.drawable.rawned;
                    break;
                default:
                    tshape = R.drawable.round_square;
                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return toastjam;
    }

    public ToastJam setTextColor(@ColorInt int color){
        try{
            text_color = color;
        } catch (Exception e){
            e.printStackTrace();
        }
        return toastjam;
    }

    private Drawable prepareBackground(){

        try {
            Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, tshape);
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            wrappedDrawable.setColorFilter(new PorterDuffColorFilter(background_color,PorterDuff.Mode.SRC_IN));
            //DrawableCompat.setTint(wrappedDrawable, background_color);
            return wrappedDrawable;
            //toastjam.view.getRootView().setBackground(wrappedDrawable);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return context.getResources().getDrawable(R.drawable.round_square);
    }

    public void show() {

        //create toastjam
        toastjam.view.setGravity(Gravity.CENTER);
        toastjam.view.setPadding(50, 30, 50, 30);
        toastjam.view.setTextColor(text_color);
        toastjam.view.setBackground(prepareBackground());


        ViewGroup rootview = (ViewGroup) ((Activity) context).findViewById(android.R.id.content);
        parent.setGravity(tgravity|Gravity.CENTER_HORIZONTAL);
        rootview.addView(parent);
    }

    private void hideToastJam(){

    }
}
