package com.example.popularmovieappstage11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class MovieDetails extends AppCompatActivity {

    TextView mtitle, mdate, mvote, moverview;
    ImageView mthumbnails;
    String title, date, overview, thumbnails;
    double vote;
    ConstVariables var = new ConstVariables();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mtitle = findViewById(R.id.mainTitle);
        mdate = findViewById(R.id.date);
        mvote = findViewById(R.id.vote);
        moverview = findViewById(R.id.overview);
        mthumbnails = findViewById(R.id.thumbnails);

        Intent intent = getIntent();
        title = Objects.requireNonNull(intent.getExtras()).getString(var.title);
        date = intent.getExtras().getString(var.date);
        overview = intent.getExtras().getString(var.overview);
        thumbnails = intent.getExtras().getString(var.thumbnails);
        vote = intent.getExtras().getDouble(var.vote);

        picaso(thumbnails,mthumbnails);

        mtitle.setText(title);
        mdate.setText(date);
        moverview.setText(overview);
        mvote.setText(String.valueOf(vote));


    }

    void picaso(String thumbnails, ImageView mthumbnails ){
        Picasso.get().load(thumbnails)
                .placeholder(R.drawable.image_placeholder)
                .into(mthumbnails);
    }
}
