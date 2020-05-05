package com.example.batique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AmbilGambar extends AppCompatActivity {
Button ambilgambar,kembali ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambil_gambar);

        ambilgambar = (Button) findViewById(R.id.ag_ambilgambar);

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

        kembali = findViewById(R.id.btnback);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
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
    private Bitmap getGrayscale(Bitmap src){

        //Custom color matrix to convert to GrayScale
        float[] matrix = new float[]{
                0.3f, 0.59f, 0.11f, 0, 0,
                0.3f, 0.59f, 0.11f, 0, 0,
                0.3f, 0.59f, 0.11f, 0, 0,
                0, 0, 0, 1, 0,};

        Bitmap dest = Bitmap.createBitmap(
                src.getWidth(),
                src.getHeight(),
                src.getConfig());

        Canvas canvas = new Canvas(dest);
        Paint paint = new Paint();
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        paint.setColorFilter(filter);
        canvas.drawBitmap(src, 0, 0, paint);

        return dest;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            ImageView GrayscaleImage = (ImageView) findViewById(R.id.blackimage);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            GrayscaleImage.setBackgroundDrawable(new BitmapDrawable(getGrayscale(imageBitmap)));
//            Uri returnUri;
//            returnUri = data.getData();
            Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
            //intent.putExtra("imageuri",returnUri.toString());
            intent.putExtras(extras);
//            startActivity(intent);
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
