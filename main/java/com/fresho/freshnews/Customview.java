package com.fresho.freshnews;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class Customview extends RecyclerView.ViewHolder {

ImageView imageView;
TextView name;

    TextView title;
    TextView description;
    Button button;
    Button button1;
    ImageView imageView2;
TextView textView;
    public Customview(@NonNull View itemView) {
        super(itemView);

         imageView = itemView.findViewById(R.id.imageView);
        name = itemView.findViewById(R.id.so);
        title = itemView.findViewById(R.id.ti);
        description = itemView.findViewById(R.id.des);
button = itemView.findViewById(R.id.button2);
button1 = itemView.findViewById(R.id.button3);
imageView2 = itemView.findViewById(R.id.sendoo);
textView = itemView.findViewById(R.id.textView2);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getName() {
        return name;
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getDescription() {
        return description;
    }

    public Button getButton() {
        return button;
    }
    public Button getButton1(){
        return button1;
    }

    public ImageView getImageView2(){

        return imageView2;

    }
    public TextView getTextView(){
        return this.textView;
    }
}
