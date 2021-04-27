package com.itb.tmbdmobileapp.Adapters;

import android.annotation.SuppressLint;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.itb.tmbdmobileapp.Modelos.ActorTest;
import com.itb.tmbdmobileapp.R;

import java.util.List;

public class ActorTestAdapter extends RecyclerView.Adapter<ActorTestAdapter.ActorTestHolder> {

    private final OnItemClickListener itemClickListener;
    private final List<ActorTest> actors;
    private final int layout;

    public interface  OnItemClickListener {
        void onItemClick(ActorTest actors);
    }

    public ActorTestAdapter(List<ActorTest> actors, int layout,  OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
        this.actors = actors;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ActorTestAdapter.ActorTestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ActorTestAdapter.ActorTestHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorTestAdapter.ActorTestHolder holder, int position) {
        holder.bind(actors.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return actors.size();
    }

    public static class ActorTestHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView puntuationText, title;
        ProgressBar progressBar;

        public ActorTestHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.photo);
            puntuationText = itemView.findViewById(R.id.progress_bar_num);
            title = itemView.findViewById(R.id.textViewTitle);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }

        @SuppressLint("SetTextI18n")
        public void bind(ActorTest actors, final OnItemClickListener listener) {
            image.setImageResource(actors.getPhoto());
            puntuationText.setText(actors.getPuntuation()+"");
            title.setText(actors.getName());
            progressBar.setProgress(actors.getPuntuation());

            itemView.setOnClickListener(v -> listener.onItemClick(actors));
        }
    }
}
