package com.fresho.freshnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class SetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

          findViewById(R.id.sha).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  Intent intent = new Intent(Intent.ACTION_SEND);
                  intent.putExtra(Intent.EXTRA_TEXT,"Hey check out my app:https://play.google.com/store/apps/details?id="+BuildConfig.APPLICATION_ID);
                  intent.setType("text/plain");
                  startActivity(intent);

              }
          });
        final EditText editText = findViewById(R.id.eddy);
          findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  if(!editText.getText().toString().equals("")) {
                      Toast.makeText(SetActivity.this, "Name has been saved as " +editText.getText().toString(), Toast.LENGTH_LONG).show();
                  }
              }
          });

        findViewById(R.id.rate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String packafename =getPackageName();

                Intent intent8 = new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id="+packafename));
                startActivity(intent8);

            }
        });

        Switch s = findViewById(R.id.switch1);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE,getPackageName());
                startActivity(intent);
            }
        });


    }
}