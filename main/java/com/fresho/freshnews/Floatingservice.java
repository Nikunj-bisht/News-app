package com.fresho.freshnews;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class Floatingservice extends Service {

    View floating;
WindowManager windowManager;
View root;
    @Override
    public void onCreate() {
        super.onCreate();
        floating= LayoutInflater.from(this).inflate(R.layout.float_view,null);
     windowManager =(WindowManager) getSystemService(WINDOW_SERVICE);

     final WindowManager.LayoutParams wl= new WindowManager.LayoutParams(
             WindowManager.LayoutParams.WRAP_CONTENT,
             WindowManager.LayoutParams.WRAP_CONTENT,
WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
             WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
             PixelFormat.TRANSLUCENT
             );
     wl.gravity= Gravity.TOP | Gravity.LEFT;
     wl.x=0;
     wl.y=100;


     windowManager.addView(floating,wl);


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
