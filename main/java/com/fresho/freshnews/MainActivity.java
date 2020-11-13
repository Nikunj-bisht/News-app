package com.fresho.freshnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements Customadaptor.fortransition {

    Button button;
    TextView textView;
    private Spinner spinner;
    ArrayAdapter<String> arrayAdapter;

private ArrayList<Dataclass> data = new ArrayList<Dataclass>();
    private ArrayList<String> arrayList = new ArrayList<>();
    private HashMap<String,String> hashMap = new HashMap<String, String>();
String country="";
RecyclerView recyclerView;
String categ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        hashMap.put("Select country","null");
        hashMap.put("India", "in");

        hashMap.put("U.S.A", "us");
        hashMap.put("Australia", "au");
        hashMap.put("Russia", "ru");
        hashMap.put("Brazil", "br");
        hashMap.put("China", "cn");
        //  hashMap.put("","fr");
        //  hashMap.put("","de");
        hashMap.put("Canada", "ca");
        hashMap.put("Philippines", "ph");


        arrayList.add("Select country");
        arrayList.add("India");
        arrayList.add("U.S.A");
        arrayList.add("Australia");
        arrayList.add("Russia");
        arrayList.add("Brazil");
        arrayList.add("China");
        arrayList.add("Canada");
        arrayList.add("Philippines");


country="India";
        //  textView = findViewById(R.id.textView);
        spinner = findViewById(R.id.spinner);
        recyclerView = findViewById(R.id.recycle);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(0);
        button = findViewById(R.id.button);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

              //  textView.setText(arrayList.get(i));
                country = arrayList.get(i);
button.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //    final Requestclass requestclass = new Requestclass();
        //    textView = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(country.equals("Select country")){

                    Toast.makeText(MainActivity.this,"Please select a country first",Toast.LENGTH_LONG).show();
                }
                else {
                    makerequest();
                }
            }

        });
        makerequest();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void makerequest(){
        final String url = "https://newsapi.org/v2/top-headlines?country=" + hashMap.get(country)+ "&pageSize=60&apiKey=a17375e1fa9e429e92b26297075b5528";

       // 0bc77ee5e0814c4897b0d6f314ffa1b4

        //    JSONArray jsonArray = new JSONArray();

        if(data.size()>0){

            data.clear();

            recyclerView.removeAllViewsInLayout();

        }

            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        JSONArray jsonArray1 =  response.getJSONArray("articles");
                       // textView.setText(jsonArray1.toString());

                        findViewById(R.id.progressBar).setVisibility(View.GONE);
int n = jsonArray1.length();
                        if(n>0){

                            for(int i=0;i<n;i++){

                                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                                String name = jsonObject1.getJSONObject("source").getString("name");
                                String author = jsonObject1.getString("author");
                                String title = jsonObject1.getString("title");
                                String description = jsonObject1.getString("description");
                                String url = jsonObject1.getString("url");
                                String imageurl = jsonObject1.getString("urlToImage");
                                String  date= jsonObject1.getString("publishedAt");
                                String content = jsonObject1.getString("content");

                                Dataclass dataclass = new Dataclass(name,author,title,description,url,imageurl,date,content);
                                data.add(dataclass);
                            }
                            Customadaptor customadaptor = new Customadaptor(MainActivity.this,data,MainActivity.this);

recyclerView.setAdapter(customadaptor);
button.setVisibility(View.GONE);
//recyclerView.getBackground().setVisible(false,false);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        }else{
                            update();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        update();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

update();

                    // Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
            });


            requestQueue.add(jsonObjectRequest);

            //    requestclass.callreq(url,textView,MainActivity.this,jsonArray);

        }


    @Override
    public void opensite(String url) {

Intent intent = new Intent(MainActivity.this,Weber.class);

intent.putExtra("site",url);
startActivity(intent);


    }

    @Override
    public void opencurrent(String url, int pos) {

        Intent intent = new Intent(MainActivity.this,Detailnews.class);
        intent.putExtra("mynews",data.get(pos));
        intent.putExtra("url",url);
        startActivity(intent);
    }

    @Override
    public void share(String url) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,url);
        intent.setType("text/plain");
startActivity(intent);
    }

    public void update(){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Alert!");
        builder.setMessage("Unable to fetch data from server either  you are not connected to Internet if connected try to update the app!");
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String packafename =getPackageName();

                Intent intent8 = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+packafename));
                startActivity(intent8);

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
}

