package com.itb.tmbdmobileapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.itb.tmbdmobileapp.Adapters.MovieTestAdapter;
import com.itb.tmbdmobileapp.Modelos.ActorTest;
import com.itb.tmbdmobileapp.Modelos.ModelGenerator;
import com.itb.tmbdmobileapp.Modelos.MovieTest;
import com.itb.tmbdmobileapp.R;
import com.itb.tmbdmobileapp.SupportFragmentManagement.AppFragmentPossibilities;
import java.util.List;
import java.util.Objects;

public class RecomendationsFragment extends Fragment {
    public enum State {recomendations, films, series, actors, favourites}
    public State currentState;
    private TextView textView1, textView2, textView3;
    private RecyclerView recyclerView1, recyclerView2, recyclerView3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WellcomeFragment.fragment = this;
        WellcomeFragment.currentFragment = AppFragmentPossibilities.RecomendationsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }

    @SuppressLint("NonConstantResourceId")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView1 = view.findViewById(R.id.textView_1);
        textView2 = view.findViewById(R.id.textView_2);
        textView3 = view.findViewById(R.id.textView_3);
        recyclerView1 = view.findViewById(R.id.recyclerView_1);
        recyclerView2 = view.findViewById(R.id.recyclerView_2);
        recyclerView3 = view.findViewById(R.id.recyclerView_3);

        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        currentState = State.recomendations;
        changeRecyclerViews();
    }

    public void changeRecyclerViews() {
        setTextViews();

        switch (currentState) {
            case recomendations:
            case favourites:
                setAllTypesAdapter();
                break;
            case films:
            case series:
                setOnlyFilmsAdapter();
                break;
            case actors:
                setOnlyActorAdapter();
                break;
        }
    }

    private void setAllTypesAdapter() {
        List<MovieTest> movies = ModelGenerator.films();
        List<MovieTest> series = ModelGenerator.films();
        List<ActorTest> actors = ModelGenerator.actors();

        MovieTestAdapter movieTestAdapter = new MovieTestAdapter(movies, R.layout.item_view_grid,this::goToFilmsDetail);
        MovieTestAdapter seriesTestAdapter = new MovieTestAdapter(series, R.layout.item_view_grid,this::goToFilmsDetail);
        ActorTestAdapter actorTestAdapter = new ActorTestAdapter(actors, R.layout.item_view_grid,this::goToActorDetail);

        recyclerView1.setAdapter(movieTestAdapter);
        recyclerView2.setAdapter(seriesTestAdapter);
        recyclerView3.setAdapter(actorTestAdapter);
    }

    private void setOnlyFilmsAdapter() {
        List<MovieTest> mostPopular = ModelGenerator.films();
        List<MovieTest> best = ModelGenerator.films();
        List<MovieTest> newFilms = ModelGenerator.films();

        MovieTestAdapter mostPopularTestAdapter = new MovieTestAdapter(mostPopular, R.layout.item_view_grid, this::goToFilmsDetail);
        MovieTestAdapter bestTestAdapter = new MovieTestAdapter(best, R.layout.item_view_grid, this::goToFilmsDetail);
        MovieTestAdapter newTestAdapter = new MovieTestAdapter(newFilms, R.layout.item_view_grid, this::goToFilmsDetail);

        recyclerView1.setAdapter(mostPopularTestAdapter);
        recyclerView2.setAdapter(bestTestAdapter);
        recyclerView3.setAdapter(newTestAdapter);
    }

    private void setOnlyActorAdapter() {
        List<ActorTest> mostPopular = ModelGenerator.actors();
        List<ActorTest> best = ModelGenerator.actors();
        List<ActorTest> newFilms = ModelGenerator.actors();

        ActorTestAdapter mostPopularTestAdapter = new ActorTestAdapter(mostPopular, R.layout.item_view_grid, this::goToActorDetail);
        ActorTestAdapter bestTestAdapter = new ActorTestAdapter(best, R.layout.item_view_grid,this::goToActorDetail);
        ActorTestAdapter newTestAdapter = new ActorTestAdapter(newFilms, R.layout.item_view_grid,this::goToActorDetail);

        recyclerView1.setAdapter(mostPopularTestAdapter);
        recyclerView2.setAdapter(bestTestAdapter);
        recyclerView3.setAdapter(newTestAdapter);
    }

    private void setTextViews() {
        String text1 = "", text2 = "", text3 = "";
        switch (currentState) {
            case recomendations:
                text1 = "Recommendated Films";
                text2 = "Recommendated Series";
                text3 = "Recommendated Actors";
                break;
            case favourites:
                text1 = "Favourite Films";
                text2 = "Favourite Series";
                text3 = "Favourite Actors";
                break;
            case films:
                text1 = "Most popular Films";
                text2 = "The Bests Films";
                text3 = "New Films";
                break;
            case actors:
                text1 = "Most popular actors";
                text2 = "The Bests actors";
                text3 = "New actors";
                break;
            case series:
                text1 = "Most popular series";
                text2 = "The Bests series";
                text3 = "New series";
                break;
        }

        textView1.setText(text1);
        textView2.setText(text2);
        textView3.setText(text3);
    }

    private void goToFilmsDetail(MovieTest movie) {
        NavDirections navDirections = RecomendationsFragmentDirections.recomendationsToFilmAndSeriesDetails(movie);
        Navigation.findNavController(requireView()).navigate(navDirections);
    }

    private void goToActorDetail(ActorTest actor) {
        NavDirections navDirections = RecomendationsFragmentDirections.recomendationsToActorDetails(actor);
        Navigation.findNavController(requireView()).navigate(navDirections);
    }
}
