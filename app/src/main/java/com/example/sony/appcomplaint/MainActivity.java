package com.example.sony.appcomplaint;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

//2017_25Mei by Muj
//coba untuk validasi network dan GPS
public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button gpsCekBtn = (Button) findViewById(R.id.gpsCekBtn);
        //cek gps aktif / tdk
        gpsCekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER) ){
                    Toast.makeText(getApplicationContext(), "GPS belum aktif", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)); // masuk ke setting
                }else {
                    Toast.makeText(getApplicationContext(), "GPS sudah aktif", Toast.LENGTH_SHORT).show();
                }

            }
        });


        final Button netwBtn = (Button)findViewById(R.id.netwCekBtn);
        //cek network
        netwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                //cek network data aktif
                if (networkInfo != null && networkInfo.isConnected()) {
                    Toast.makeText(getApplicationContext(), "network tersedia", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "aktifkan mobile network ", Toast.LENGTH_SHORT).show();
                    //masuk ke setting data usage
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.android.settings",
                            "com.android.settings.Settings$DataUsageSummaryActivity"));
                    startActivity(intent);
                }
            }
        });





    }
}
