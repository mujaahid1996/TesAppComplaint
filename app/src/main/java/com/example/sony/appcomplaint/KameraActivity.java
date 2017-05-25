package com.example.sony.appcomplaint;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

//2017_25Mei by Muj
//mengambil gambar dari camera dan disimpan ke folder Picture
public class KameraActivity extends AppCompatActivity {

    File pictureFile;
    private static int code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamera);

        Button takepict = (Button)findViewById(R.id.takePictBtn);
        //2017_25Mei by Muj
        //ambil gambar melalui camera
        takepict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pictureFile = new File(Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES ) , "tess.jpg" );

                Uri uri= Uri.fromFile(pictureFile);

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri );

                startActivityForResult(intent, code);


            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( requestCode == code ) {

            switch (resultCode){
                case Activity.RESULT_OK:
                    //2017_25Mei by Muj
                    //cek bahwa file tersimpan
                    if( pictureFile.exists() ){
                        Toast.makeText(getApplicationContext()
                                , "gambar tersimpan di " + pictureFile.getAbsolutePath()
                                , Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "gagal disimpan", Toast.LENGTH_SHORT).show();
                    }

                    break;

                case Activity.RESULT_CANCELED:
                    break;

                default:
                    break;
            }

        }

    }
}
