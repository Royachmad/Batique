package com.example.batique;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Teknik extends AppCompatActivity {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teknik);

        viewPager = findViewById(R.id.viewpager1);
        TeknikAdapter adapter = new TeknikAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
}
