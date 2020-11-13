package com.fresho.freshnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Savednews extends AppCompatActivity {

    Mynewsdatabase mynewsdatabase ;
    ArrayList<String> arrayList;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savednews);

        mynewsdatabase = new Mynewsdatabase(Savednews.this);
    linearLayout = findViewById(R.id.linearadd);
        arrayList = mynewsdatabase.getall();

        displayall();

    }

    private void displayall() {

        for(int i=0;i<arrayList.size();i++) {
            final int k=i;
            View view1 = getLayoutInflater().inflate(R.layout.sample, null);
            TextView textView = view1.findViewById(R.id.textView9);
            textView.setText(arrayList.get(i));
            ImageView imageView =  view1.findViewById(R.id.imageView2);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Savednews.this,Weber.class);
                    intent.putExtra("site",arrayList.get(k));
startActivity(intent);
                }
            });
            linearLayout.addView(view1);
        }
    }


}