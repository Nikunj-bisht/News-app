package com.fresho.freshnews;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Detailnews extends AppCompatActivity {


    TextView textView1;

    TextView textView2;

    TextView textView3;
AdView mAdView;
    TextView textView4;
ImageView imageView;
ImageView imgbutton;
ImageView liky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailnews);

imageView = findViewById(R.id.curimg);
textView1 = findViewById(R.id.au);
textView2 = findViewById(R.id.tl);
textView3 = findViewById(R.id.de);
textView4= findViewById(R.id.con);
        imgbutton= findViewById(R.id.sha);
liky = findViewById(R.id.liker);


        final String imageurl = getIntent().getStringExtra("url");
        final Dataclass dataclass = (Dataclass) getIntent().getSerializableExtra("mynews");

        liky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Mynewsdatabase mynewsdatabase = new Mynewsdatabase(Detailnews.this);

mynewsdatabase.insert(dataclass.getPublish(),dataclass.getUrl());

liky.setVisibility(View.INVISIBLE);
Toast.makeText(Detailnews.this,"Added to liked news",Toast.LENGTH_LONG).show();
            }
        });

        new Thread(){

            public void run(){
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                ImageRequest jsonObjectRequest = new ImageRequest(imageurl, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {

                        imageView.setImageBitmap(response);
                    }


                },0,0, ImageView.ScaleType.FIT_XY, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error",error.toString());
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });


                requestQueue.add(jsonObjectRequest);

            }

        }.start();

textView1.setText(dataclass.getAuthor());
textView2.setText(dataclass.getTitle());
textView3.setText(dataclass.getDescription());
textView4.setText(dataclass.getContent());

imgbutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent i = new Intent(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT,dataclass.getUrl());
        i.setType("text/plain");
        startActivity(i);

    }
});
        AdView adView = new AdView(this);

        adView.setAdSize(AdSize.BANNER);

        adView.setAdUnitId("ca-app-pub-5182697525433528/2837514811");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.add);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onStop() {
        super.onStop();


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)){

            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:"+getPackageName()));
            startActivityForResult(intent,200);
        }

        else {
            startService(new Intent(this, Floatingservice.class));
        }
    }
}