package com.technogenis.iotbasedsmartwater;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 5000;
//    ImageView gifImageView;
      TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);

        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.myanimation);
//        gifImageView = findViewById(R.id.gifImageView);
//        Glide.with(this)
//                .asGif()
//                .load(R.drawable.aquarium_one)
//                .into(gifImageView);

        textView.setAnimation(myanim);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_TIME_OUT);


    }
}