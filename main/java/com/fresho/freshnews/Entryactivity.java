package com.fresho.freshnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Entryactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entryactivity);


           new Thread(){

               public void run(){

                   try{

                       Thread.sleep(2500);
                       Intent i = new Intent(Entryactivity.this,Chooseactivity.class);
                       startActivity(i);
                       finish();
                   }catch (Exception e){

                   }

               }


           }.start();

    }
}