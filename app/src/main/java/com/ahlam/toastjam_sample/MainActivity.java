package com.ahlam.toastjam_sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import com.ahlam.toastjam.entities.TGravity;
import com.ahlam.toastjam.entities.TShape;
import com.ahlam.toastjam.ToastJam;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            ToastJam.setup(this, "Hi there !")
                    //.setGravity(TGravity.BOTTOM)
                    //.setShape(TShape.RAWNED)
                    //.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                    //.setColor(ContextCompat.getColor(this, R.color.background_color))
                    .show();
    }
}
