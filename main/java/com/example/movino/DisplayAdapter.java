package com.example.movino;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class DisplayAdapter extends  RecyclerView.Adapter<DisplayAdapter.ViewHolder> {

    private final String imageUrl="https://image.tmdb.org/t/p/w500";
    Context context;
    MovieResponse listOfMovies;

    public DisplayAdapter(Context context, MovieResponse listOfMovies){
        this.context=context;
        this.listOfMovies=listOfMovies;
    }

    @NonNull
    @Override
    public DisplayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.movie_title,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayAdapter.ViewHolder holder, int position) {

        String image_path = imageUrl + (listOfMovies.getResults().get(position).getPoster_path());
        Picasso.get()
                .load(image_path)
                .resize(80, 100)
                .centerCrop()
                .into(holder.poster);
        holder.movieTitle.setText(listOfMovies.getResults().get(position).getTitle());
        holder.movieRating.setText((int) listOfMovies.getResults().get(position).getVote_average());
    }
    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle,movieRating;
        ImageView poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle=itemView.findViewById(R.id.movietitle);
            movieRating=itemView.findViewById(R.id.movierating);
            poster=itemView.findViewById(R.id.poster);
        }
    }
}
