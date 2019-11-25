package com.example.movino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public final static String BASE_URL="https://api.themoviedb.org";
    public final static String apiKey="75133bb43ec6ae53d9e989cb53a8c7f8";
    public final static String language="en-US";
    public final static String TAG="tag";

    @BindView(R.id.recyclerView)
    RecyclerView movieList;

    DisplayAdapter adapter;
    MovieResponse result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Retrofit retrofit=new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

        ApiInterface apiInterface=retrofit.create(ApiInterface.class);

        apiInterface.getMovies(apiKey,language,1)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if(response.isSuccessful())
                        {
                            result=response.body();
                            Log.e(TAG, "onCreate: "+result );

                            adapter=new DisplayAdapter(getApplicationContext(),result);
                            RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),1);
                            movieList.setLayoutManager(layoutManager);
                            movieList.setAdapter(adapter);
                        }

                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) { }
                });

    }
}

