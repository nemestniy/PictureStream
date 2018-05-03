package com.nemestniy.admin.picturestream;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nemestniy.admin.picturestream.API.Api;
import com.nemestniy.admin.picturestream.API.ExampleResponse;
import com.nemestniy.admin.picturestream.API.OnBottomReachedListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private ImageView image;

    private final String URL = "https://api.unsplash.com/";
    private final String ACCESS_KEY = "1bf67fa81b7d834ac542c7d80ba7f724858d870515e63bf8df91eae3cb022a97";

    private Gson gson = new GsonBuilder().create();
    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(URL)
            .build();

    private Api api = retrofit.create(Api.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.image);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));



        adapter = new RecyclerAdapter();
        adapter.setOnBottomReachedListener(new OnBottomReachedListener() {
            @Override
            public void onBottomReached(int position) {
                api.getPicture(ACCESS_KEY, 6).enqueue(new Callback<ArrayList<ExampleResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ExampleResponse>> call, Response<ArrayList<ExampleResponse>> response) {
                        adapter.addAll(response.body());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ExampleResponse>> call, Throwable t) {

                    }
                });
            }
        });
        recyclerView.setAdapter(adapter);

    }
}
