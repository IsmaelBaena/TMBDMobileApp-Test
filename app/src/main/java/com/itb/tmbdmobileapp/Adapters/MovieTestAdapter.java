package com.itb.tmbdmobileapp.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.itb.tmbdmobileapp.Modelos.MovieTest;
import com.itb.tmbdmobileapp.R;

import java.util.List;

public class MovieTestAdapter extends RecyclerView.Adapter<MovieTestAdapter.MovieTestHolder> {

    private final OnItemClickListener itemClickListener;
    private final List<MovieTest> movies;
    private final int layout;

    public interface  OnItemClickListener {
        void onItemClick(MovieTest movieTest);
    }

    public MovieTestAdapter(List<MovieTest> movies, int layout, OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
        this.movies = movies;
        this.layout = layout;
    }

    @NonNull
    @Override
    public MovieTestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new MovieTestHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieTestHolder holder, int position) {
        holder.bind(movies.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieTestHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView puntuationText, title;
        ProgressBar progressBar;

        public MovieTestHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.photo);
            puntuationText = itemView.findViewById(R.id.progress_bar_num);
            title = itemView.findViewById(R.id.textViewTitle);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }

        @SuppressLint("SetTextI18n")
        public void bind(MovieTest movie, final OnItemClickListener listener) {
            image.setImageResource(movie.getPhoto());
            puntuationText.setText(movie.getPuntuation()+"");
            title.setText(movie.getTitle());
            progressBar.setProgress(movie.getPuntuation());

            itemView.setOnClickListener(v -> listener.onItemClick(movie));
        }
    }
}
