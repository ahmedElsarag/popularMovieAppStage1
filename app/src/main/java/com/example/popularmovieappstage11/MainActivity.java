package com.example.popularmovieappstage11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    public static String BaseUrl = "https://api.themoviedb.org";
    List<MovieResults.ResultsBean> listOfMovies;
    ConstVariables var = new ConstVariables();
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);

        retrofitconnect(BaseUrl,var.category,var.apiKey,var.language,var.page);
    }


    public void retrofitconnect(String burl, String cate, String key, String lang, int pag) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(burl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<MovieResults> call = apiInterface.getMovies(cate, key, lang, pag);

        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults results = response.body();
                //the result array list in the movieResult class

                listOfMovies = Objects.requireNonNull(results).getResults();

                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                myAdapter = new MyAdapter(MainActivity.this, listOfMovies);
                recyclerView.setAdapter(myAdapter);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemSelected = item.getItemId();
        if(menuItemSelected == R.id.popular){
            var.category = "popular";
            progressBar.setVisibility(View.VISIBLE);
            retrofitconnect(BaseUrl,var.category,var.apiKey,var.language,var.page);
        }
        else{
            var.category = "top_rated";
            progressBar.setVisibility(View.VISIBLE);
            retrofitconnect(BaseUrl,var.category,var.apiKey,var.language,var.page);
        }
        return super.onOptionsItemSelected(item);
    }
}
