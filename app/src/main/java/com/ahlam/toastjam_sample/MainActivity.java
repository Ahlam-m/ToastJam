package com.ahlam.toastjam_sample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ahlam.toastjam.entities.TGravity;
import com.ahlam.toastjam.entities.TShape;
import com.ahlam.toastjam.ToastJam;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToastJam.createLong(this, "Hi there !")
                .setGravity(TGravity.BOTTOM)
                .setShape(TShape.ROUND_SQUARE)
                .setTextColor(getResources().getColor(R.color.text_color))
                .setBackgroundColor(getResources().getColor(R.color.background_color))
                .show();
    }
}
