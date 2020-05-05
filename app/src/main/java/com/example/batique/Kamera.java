package com.example.batique;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Kamera extends AppCompatActivity {
    Button ambilgambar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ambilgambar = (Button) findViewById(R.id.btnkamera);

        ambilgambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Checking camera availability

                if (!isDeviceSupportCamera()) {
                    Toast.makeText(getApplicationContext(),"Camera di device anda tidak tersedia", Toast.LENGTH_LONG).show();
                }else {
                    captureImage();
                }
            }
        });
    }

    private void captureImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //cameraIntent.setType("image/*");

        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null)
        {
            startActivityForResult(takePictureIntent, 100);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            Uri returnUri;
//            returnUri = data.getData();
            Intent intent = new Intent(getApplicationContext(),HasilGambar.class);
            //intent.putExtra("imageuri",returnUri.toString());
            intent.putExtras(extras);
            startActivity(intent);
        }
    }


    /*
     * mengecek pada perangkat mobile memiliki kamera atau tidak
     * */
    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_ANY)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

}
