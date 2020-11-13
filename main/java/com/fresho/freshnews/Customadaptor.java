package com.fresho.freshnews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;


import java.util.ArrayList;

public class Customadaptor extends RecyclerView.Adapter<Customview> {

    Context context;
    ArrayList<Dataclass> arrayList;
fortransition fort;

    public Customadaptor(Context context, ArrayList<Dataclass> arrayList,fortransition fo) {
        this.context = context;
        this.arrayList = arrayList;
        this.fort = fo;
    }

    interface fortransition{

        void opensite(String url);
        void opencurrent(String url,int pos);
void share(String url);
    }

    @NonNull
    @Override
    public Customview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutinflator = LayoutInflater.from(context);
        View view1 = layoutinflator.inflate(R.layout.single, parent, false);
        Customview customview = new Customview(view1);


        return customview;
    }

    @Override
    public void onBindViewHolder(@NonNull Customview holder, final int position) {

        holder.getName().setText(arrayList.get(position).getName());
        holder.getTitle().setText(arrayList.get(position).getTitle());
        holder.getDescription().setText(arrayList.get(position).getTitle());
        holder.getTextView().setText(arrayList.get(position).getPublish());
        holder.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fort.opensite(arrayList.get(position).getUrl());
            }
        });
        holder.getButton1().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fort.opencurrent(arrayList.get(position).getImageurl(),position);
            }
        });
ImageView imageView = holder.getImageView();
Imageclass imageclass = new Imageclass(imageView,arrayList.get(position).getImageurl());
imageclass.start();

 holder.getImageView2().setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {

fort.share(arrayList.get(position).getUrl());
         // intent.setData(arrayList.get(position).getUrl());

     }
 });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    /* threads for image downloads */

    class Imageclass extends Thread {

        private String url;
        ImageView imageView;

        public Imageclass(ImageView imageView, String url) {

            this.imageView = imageView;
            this.url = url;

        }

        public void run() {

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            ImageRequest jsonObjectRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {

                    imageView.setImageBitmap(response);
                }


            },0,0,ImageView.ScaleType.FIT_XY, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error",error.toString());
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            });


            requestQueue.add(jsonObjectRequest);


        }

    }

}
