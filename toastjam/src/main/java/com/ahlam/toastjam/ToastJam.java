package com.ahlam.toastjam;

import android.annotation.SuppressLint;
import android.content.Context;

import android.graphics.drawable.Drawable;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.core.graphics.drawable.DrawableCompat;

import com.ahlam.toastjam.entities.TGravity;
import com.ahlam.toastjam.entities.TShape;


@SuppressWarnings("ConstantConditions")
public class ToastJam {


    ToastJam(){

        background_color = -867414964;//transparent gray
    }

    private  @ColorInt int background_color;


    private Toast toast;
    private static final ToastJam toastjam = new ToastJam();


    @SuppressLint("ShowToast")
    static public ToastJam createLong(Context context, String msg){
        toastjam.toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);

        return toastjam;
    }

    @SuppressLint("ShowToast")
    static public ToastJam createShort(Context context, String msg){
        toastjam.toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);

        return toastjam;
    }

    public ToastJam setGravity(int gravity){
        switch (gravity){
            case TGravity.TOP:
                toastjam.toast.setGravity(android.view.Gravity.TOP,0,300);
                break;
            case TGravity.CENTER:
                toastjam.toast.setGravity(android.view.Gravity.CENTER_VERTICAL,0,0);
                break;

            default:
                break;
        }
        return toastjam;
    }


    public ToastJam setBackgroundColor(@ColorInt int color){

        background_color = color;

        try {
            Drawable unwrappedDrawable = toastjam.toast.getView().getBackground();
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, color);
            toastjam.toast.getView().setBackground(wrappedDrawable);

        } catch (NullPointerException e){
            e.printStackTrace();
        }

        return toastjam;
    }



    public ToastJam setShape(int shape){

        try {
            Context context = toastjam.toast.getView().getContext().getApplicationContext();

            switch (shape) {
                case TShape.SQUARE:
                    toastjam.toast.getView().setBackground(context.getResources().getDrawable(R.drawable.square));
                    toastjam.toast.getView().setPadding(50, 30, 50, 30);
                    break;
                case TShape.ROUND_SQUARE:
                    toastjam.toast.getView().setBackground(context.getResources().getDrawable(R.drawable.round_square));
                    toastjam.toast.getView().setPadding(50, 30, 50, 30);
                    break;
                case TShape.RAWNED:
                    toastjam.toast.getView().setBackground(context.getResources().getDrawable(R.drawable.rawned));
                    toastjam.toast.getView().setPadding(50, 30, 50, 30);
                    break;
                default:
                    break;
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        setBackgroundColor(background_color);

        return toastjam;
    }

    public ToastJam setTextColor(@ColorInt int color){
        try{
            TextView v = (TextView) toastjam.toast.getView().findViewById(android.R.id.message);
            v.setTextColor(color);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return toastjam;
    }

    public void show(){
        toastjam.toast.show();
    }
}
