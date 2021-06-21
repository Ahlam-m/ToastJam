package com.ahlam.toastjam_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import com.ahlam.toastjam.entities.TGravity;
import com.ahlam.toastjam.entities.TShape;
import com.ahlam.toastjam.ToastJam;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            ToastJam.setup(this, "Hi there !")
                    .setGravity(TGravity.TOP)
                    //.setShape(TShape.RAWNED)
                    .setTextColor(getResources().getColor(R.color.background_color))
                    .setBackgroundColor(R.color.colorAccent)
                    .show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
