package com.ahlam.toastjam;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

import android.graphics.drawable.Drawable;
import android.text.PrecomputedText;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.ColorInt;
import androidx.core.graphics.drawable.DrawableCompat;

import com.ahlam.toastjam.entities.TGravity;
import com.ahlam.toastjam.entities.TShape;

import java.time.Duration;

import static android.content.Context.WINDOW_SERVICE;


public class ToastJam {

    @SuppressLint("StaticFieldLeak")
    private static ToastJam toastjam;
    private final TextView view;

    private final Context context;
    private @ColorInt int background_color;
    private TShape tshape;

    private ToastJam(Context context){
        this.context = context;
        view = (TextView) View.inflate(context, R.layout.toastjam_layout, null);

        background_color = -867414964;//transparent gray
        tshape = TShape.ROUND_SQUARE;
    }


    public static ToastJam setup(Context context, String msg){
        toastjam = new ToastJam(context);
        toastjam.view.setText(msg);
        return toastjam;
    }

    public ToastJam setGravity(TGravity gravity){
        switch (gravity){
            case TOP:
                toastjam.view.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL);
                break;
            case CENTER:
                toastjam.view.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
                break;

            default:
                toastjam.view.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);
                break;
        }
        return toastjam;
    }


    public ToastJam setBackgroundColor(@ColorInt int color){

        background_color = color;

        /*try {
            view.setBackgroundColor(color);
        } catch (NullPointerException e){
            e.printStackTrace();
        }*/

        try {
            Drawable unwrappedDrawable = toastjam.view.getBackground();
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, color);
            toastjam.view.setBackground(wrappedDrawable);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return toastjam;
    }



    public ToastJam setShape(TShape shape){

        try {
            switch (shape) {
                case SQUARE:
                    toastjam.view.setBackground(context.getResources().getDrawable(R.drawable.square));
                    toastjam.view.setPadding(50, 30, 50, 30);
                    break;
                case ROUND_SQUARE:
                    toastjam.view.setBackground(context.getResources().getDrawable(R.drawable.round_square));
                    toastjam.view.setPadding(50, 30, 50, 30);
                    break;
                case RAWNED:
                    toastjam.view.setBackground(context.getResources().getDrawable(R.drawable.rawned));
                    toastjam.view.setPadding(50, 30, 50, 30);
                    break;
                default:
                    break;
            }
            setBackgroundColor(background_color);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return toastjam;
    }

    public ToastJam setTextColor(@ColorInt int color){
        try{
            toastjam.view.setTextColor(color);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return toastjam;
    }

    public void show() throws InterruptedException {

        RelativeLayout relativeLayout = new RelativeLayout(context);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        ((Activity) context).addContentView(view, params);

        Thread.sleep(2);
        //((ViewGroup) ((Activity) context).getWindow().getDecorView()).removeView(view);

        //((Activity) context).getParent().getWindowManager().removeView(view);
        WindowManager windowManager = (WindowManager) ((Activity) context).getSystemService(WINDOW_SERVICE);
//        windowManager.removeView(view);



        //((Activity) context).addContentView(relativeLayout, new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //WindowManager windowManager = (WindowManager) ((Activity) context).getSystemService(WINDOW_SERVICE);
        //windowManager.addView(view, new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
