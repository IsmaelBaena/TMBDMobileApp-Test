package com.itb.tmbdmobileapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.itb.tmbdmobileapp.Adapters.ActorTestAdapter;
import com.itb.tmbdmobileapp.Modelos.ModelGenerator;
import com.itb.tmbdmobileapp.Modelos.MovieTest;
import com.itb.tmbdmobileapp.R;
import com.itb.tmbdmobileapp.SupportFragmentManagement.AppFragmentPossibilities;

public class FilmAndSeriesDetailsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WellcomeFragment.fragment = this;
        WellcomeFragment.currentFragment = AppFragmentPossibilities.FilmAndSeriesDetailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_specific_movie_serie, container, false);
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MovieTest movieTest = (MovieTest) requireArguments().get("film");

        ImageView imageView = view.findViewById(R.id.photoSpecific);
        TextView title = view.findViewById(R.id.titleSpecific);
        TextView puntuationText = view.findViewById(R.id.progress_bar_num);
        TextView description = view.findViewById(R.id.textViewDescription);
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewActors);


        ActorTestAdapter adapter = new ActorTestAdapter(ModelGenerator.actors(), R.layout.item_view_grid, actors -> {
            NavDirections navDirections = FilmAndSeriesDetailsFragmentDirections.filmAndSeriesDetailsToActorDetails(actors);
            Navigation.findNavController(getView()).navigate(navDirections);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        imageView.setImageResource(movieTest.getPhoto());
        title.setText(movieTest.getTitle());
        puntuationText.setText(movieTest.getPuntuation()+"");
        description.setText(movieTest.getDescription());
        progressBar.setProgress(movieTest.getPuntuation());
    }
}
