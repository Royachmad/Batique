package com.example.batique;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
Button jenisbatik, Carabatik, Teknikbatik, Alatbatik, AmbilGambar;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    int flag=0;
    Intent intent;
    public final int SELECT_FILE = 1;
    Bitmap imageBatik;
    ImageView gambarBatik;
    int bitmap_size = 40; // image quality 1 - 100;
    int max_resolution_image = 800;
    private Bitmap decoded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gambarBatik = findViewById(R.id.imageView);

        jenisbatik = findViewById(R.id.btnjenis);
        jenisbatik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,JenisBatik.class);
                startActivity(i);
            }
        });

        Teknikbatik = findViewById(R.id.btnteknik);
        Teknikbatik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Teknik.class);
                startActivity(i);
            }
        });

        Carabatik = findViewById(R.id.btncara);
        Carabatik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Rawat.class);
                startActivity(i);
            }
        });

        AmbilGambar = findViewById(R.id.btnkamera);
        AmbilGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this,Kamera.class);
//                startActivity(i);
          //      captureImage();
                selectImage();
            }
        });
        Alatbatik = findViewById(R.id.btnalat);
        Alatbatik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Alat.class);
                startActivity(i);
            }
        });
    }


    private void selectImage() {

        final CharSequence[] items = {"Ambil dari Kamera", "Ambil dari Gallery",
                "Keluar"};

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Ambil Gambar Batik");
        builder.setIcon(R.drawable.logo);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Ambil dari Kamera")) {
                    flag = 1;
                    dispatchTakePictureIntent();
                } else if (items[item].equals("Ambil dari Gallery")) {
                    flag = 2;
                    intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
                } else if (items[item].equals("Keluar")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (flag == 1) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                final Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageBatik = imageBitmap;
                imageBatik = getGrayscale(imageBatik);
                gambarBatik.setImageBitmap(imageBatik);
                   sendImage();
//
            }
        } else if (flag == 2) {
            Log.e("onActivityResult", "requestCode " + requestCode + ", resultCode " + resultCode);

            if (requestCode == SELECT_FILE && data != null && data.getData() != null) {
                try {
                    // mengambil gambar dari Gallery
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(MainActivity.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {

        }
        flag = 0;
    }


    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imageBatik = decoded;
        imageBatik = getGrayscale(imageBatik);
        gambarBatik.setImageBitmap(imageBatik);
        sendImage();
//
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

        return Bitmap.createScaledBitmap(dest, 105, 105, false);
    }

    public String sendImage(){ // tempat ngirim ke server
        String hasilKlasifikasi=""; // variabel untuk menangkap hasil perhitungan di server
        imageBatik = imageBatik; // variabel yang dikirimkan ke server

        return "Gambar batik tersebut tergolong batik: "+hasilKlasifikasi;
    }
}
