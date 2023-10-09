package com.example.xoliuchat;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class photo_profile extends AppCompatActivity implements View.OnClickListener{
    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    ImageView image6;
    ImageView image7;
    ImageView image8;
    ImageView image9;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_profile);

        image1 = (ImageView) findViewById(R.id.image_1);
        image2 = (ImageView) findViewById(R.id.image_2);
        image3 = (ImageView) findViewById(R.id.image_3);
        image4 = (ImageView) findViewById(R.id.image_4);
        image5 = (ImageView) findViewById(R.id.image_5);
        image6 = (ImageView) findViewById(R.id.image_6);
        image7 = (ImageView) findViewById(R.id.image_7);
        image8 = (ImageView) findViewById(R.id.image_8);
        image9 = (ImageView) findViewById(R.id.image_9);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
        image6.setOnClickListener(this);
        image7.setOnClickListener(this);
        image8.setOnClickListener(this);
        image9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.image_1){
            MainActivity.IMAGE_ID = 1;
        } else if (id == R.id.image_2) {
            MainActivity.IMAGE_ID = 2;
        }else if (id == R.id.image_3) {
            MainActivity.IMAGE_ID = 3;
        }else if (id == R.id.image_4) {
            MainActivity.IMAGE_ID = 4;
        }else if (id == R.id.image_5) {
            MainActivity.IMAGE_ID = 5;
        }else if (id == R.id.image_6) {
            MainActivity.IMAGE_ID = 6;
        }else if (id == R.id.image_7) {
            MainActivity.IMAGE_ID = 7;
        }else if (id == R.id.image_8) {
            MainActivity.IMAGE_ID = 8;
        }else {
            MainActivity.IMAGE_ID = 9;
        }
//        Intent intent = new Intent(photo_profile.this, MainActivity.class);
//        startActivity(intent);
        finish();
    }
}