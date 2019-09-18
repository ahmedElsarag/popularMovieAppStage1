package com.example.popularmovieappstage11;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<MovieResults.ResultsBean> list;
    ConstVariables var = new ConstVariables();

    public MyAdapter(Context context, List<MovieResults.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        myViewHolder.imageView.setAdjustViewBounds(true);
        Picasso.get().load(var.MOVIE_BASE_URL+ list.get(i).getPoster_path())
                .placeholder(R.drawable.image_placeholder)
                .into(myViewHolder.imageView);
        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                Intent intent = new Intent(context,MovieDetails.class);
                intent.putExtra(var.title,list.get(i).getOriginal_title());
                intent.putExtra(var.date,list.get(i).getRelease_date());
                intent.putExtra(var.vote,list.get(i).getVote_average());
                intent.putExtra(var.overview,list.get(i).getOverview());
                intent.putExtra(var.thumbnails,var.MOVIE_BASE_URL+list.get(i).getPoster_path());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
        }
    }
}
