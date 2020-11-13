package com.fresho.freshnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

public class Chooseactivity extends AppCompatActivity implements View.OnClickListener {


     ImageView imageView;
     AdView mAdView;
    public static final String channel1="channel1";
    public static final String channel2="channel2";

    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;

    ImageView imageView10;

    ImageView imageView11;

    ImageView imageView12;
NotificationManagerCompat notificationManagerCompat;
    Myreceiver myreceiver = new Myreceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseactivity);


         imageView = findViewById(R.id.top);
         imageView2 = findViewById(R.id.bit);
         imageView3 = findViewById(R.id.tech);
         imageView4 = findViewById(R.id.apple);
        imageView5 = findViewById(R.id.set);
        imageView6 = findViewById(R.id.go);
        imageView7 = findViewById(R.id.abc);
        imageView8 = findViewById(R.id.bbc);
        imageView9 = findViewById(R.id.book);
        imageView10 = findViewById(R.id.not);

        imageView11 = findViewById(R.id.en);
        imageView12 = findViewById(R.id.newfeed);

        imageView.setOnClickListener(this);
imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView5.setOnClickListener(this);
        imageView6.setOnClickListener(this);
        imageView7.setOnClickListener(this);
        imageView8.setOnClickListener(this);
        imageView9.setOnClickListener(this);
        imageView10.setOnClickListener(this);
        imageView11.setOnClickListener(this);
        imageView12.setOnClickListener(this);

        findViewById(R.id.bug).setOnClickListener(this);
findViewById(R.id.rate).setOnClickListener(this);
        ImageSlider imageSlider = findViewById(R.id.slider);
      //  ImageSlider imageSlidero = findViewById(R.id.slidero);

        List<SlideModel> list = new ArrayList<>();
        list.add(new SlideModel(R.drawable.sport,"Sport", ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner,"Health", ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.new_1,"Daily hunt", ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.buss,"Business", ScaleTypes.FIT));
      //  list.add(new SlideModel(R.drawable.conn,"Tech", ScaleTypes.FIT));
list.add(new SlideModel(R.drawable.robo,"Tech",ScaleTypes.FIT));

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            NotificationChannel notificationChannel = new NotificationChannel(channel1,"channel1", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Welcome");
            notificationChannel.enableVibration(true);



//
           NotificationChannel notificationChannel2 = new NotificationChannel(channel2,"channel2", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel2.setDescription("Welcome");
            notificationChannel2.enableVibration(true);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
          manager.createNotificationChannel(notificationChannel2);
        }

        imageSlider.setImageList(list,ScaleTypes.FIT);


      //  App app = new App(this);
        notificationManagerCompat=NotificationManagerCompat.from(this);
        //app.createnotification();

        wecomenotification();

        AdView adView = new AdView(this);

        adView.setAdSize(AdSize.BANNER);

        adView.setAdUnitId("ca-app-pub-5182697525433528/2837514811");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }

    private void wecomenotification() {


        Toast.makeText(this,"lol",Toast.LENGTH_LONG).show();
        Notification notification = new NotificationCompat.Builder(this,channel2)
                .setContentTitle("Welcome to fresh news")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_baseline_emoji_emotions_24)
                .setContentText("Get updated")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(2,notification);


    }

    private void opensetting() {

        Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE,getPackageName());
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.top:

                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
overridePendingTransition(R.anim.bottomtr,R.anim.toptrans);
                break;

            case R.id.bit:

                Intent intent1 = new Intent(this,Sportsactivity.class);
                intent1.putExtra("category","business");
                startActivity(intent1);

                break;
            case R.id.tech:

                Intent intent2 = new Intent(this,Sportsactivity.class);
                intent2.putExtra("category","sports");

                startActivity(intent2);

                break;

            case R.id.apple:

                Intent intent3 = new Intent(this,Sportsactivity.class);
                intent3.putExtra("category","health");

                startActivity(intent3);

                break;



            case R.id.set:

                Intent intent5 = new Intent(this,SetActivity.class);
                startActivity(intent5);
                overridePendingTransition(R.anim.zoomtrans,R.anim.animation);

                break;

            case R.id.go:

                Intent intent6 = new Intent(this,MainActivity.class);
                startActivity(intent6);

                break;

            case R.id.bug:

                Intent intent7 = new Intent(Intent.ACTION_SENDTO);
                intent7.setData(Uri.parse("mailto:nikkubisht12@gmail.com"));
                startActivity(intent7);

                break;


            case R.id.rate:
String packafename =getPackageName();

                Intent intent8 = new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id="+packafename));
                startActivity(intent8);

                break;


            case R.id.book:

                Intent intent9 = new Intent(this,Savednews.class);
                startActivity(intent9);
                overridePendingTransition(R.anim.bottomtr,R.anim.toptrans);
                break;


            case R.id.newfeed:

                Intent intent10 = new Intent(this,MainActivity.class);
                startActivity(intent10);
                overridePendingTransition(R.anim.bottomtr,R.anim.toptrans);
                break;


            case R.id.abc:

                Intent intent11 = new Intent(this,Sportsactivity.class);
                intent11.putExtra("category","technology");
                startActivity(intent11);

                break;

            case R.id.bbc:

                Intent intent12 = new Intent(this,Sportsactivity.class);
                intent12.putExtra("category","science");
                startActivity(intent12);

                break;
            case R.id.not:

                alertdialog();

                break;

            case R.id.en:

                Intent intent13 = new Intent(this,Sportsactivity.class);
                intent13.putExtra("category","entertainment");
                startActivity(intent13);

                break;

        }


    }

    private void alertdialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notification alert");
        builder.setMessage("Want to receive notification from News hour ");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                opensetting();

            }
        });

builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

        dialogInterface.dismiss();

    }
});

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    @Override
    protected void onStart() {
        super.onStart();


    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//
//notifi();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        notifi();


        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_POWER_CONNECTED);

        registerReceiver(myreceiver,intentFilter);

    }

    private void notifi() {


        Intent intent = new Intent(this,Chooseactivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.banner);
        Notification notification = new NotificationCompat.Builder(this,channel1)
                .setContentTitle("Catch you soon")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_baseline_thumb_up_24)
                .setLargeIcon(bitmap)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Please rate our app if you liked it!"))
                .setContentText("Hope you liked it")
                .setContentIntent(pendingIntent)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(1,notification);

    }
}