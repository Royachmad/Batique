package com.example.batique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JenisBatik extends AppCompatActivity {
Button btnback, btngrising,btnlk,btnmb, btnpk,btnpb,btnsr, btnsa, btnsm,btntm,btntg, btnwt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_batik);
        btngrising = findViewById(R.id.btngr);
        btngrising.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Grising.class);
                startActivity(i);
            }
        });
        btnlk = findViewById(R.id.btnlk);
        btnlk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Lereng_keong.class);
                startActivity(i);
            }
        });
        btnmb = findViewById(R.id.btnmb);
        btnmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Madu_broto.class);
                startActivity(i);
            }
        });
        btnpk = findViewById(R.id.btnpk);
        btnpk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Parang_kusumo.class);
                startActivity(i);
            }
        });
        btnpb = findViewById(R.id.btnpb);
        btnpb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Pisan_bali.class);
                startActivity(i);
            }
        });
        btnsr = findViewById(R.id.btnsr);
        btnsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Semen_rejo.class);
                startActivity(i);
            }
        });
        btnsa = findViewById(R.id.btnsa);
        btnsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Sido_asih.class);
                startActivity(i);
            }
        });
        btnsm = findViewById(R.id.btnsm);
        btnsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Sido_mukti.class);
                startActivity(i);
            }
        });
        btntm = findViewById(R.id.btntm);
        btntm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Tambal.class);
                startActivity(i);
            }
        });

        btntg = findViewById(R.id.btntg);
        btntg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Truntum_gurdo.class);
                startActivity(i);
            }
        });
        btnwt = findViewById(R.id.btnwt);
        btnwt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Wahyu_tumurun.class);
                startActivity(i);
            }
        });
        btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
