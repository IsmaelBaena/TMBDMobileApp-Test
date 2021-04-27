package com.itb.tmbdmobileapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.itb.tmbdmobileapp.SupportFragmentManagement.AppFragmentPossibilities;
import com.itb.tmbdmobileapp.R;
import com.itb.tmbdmobileapp.SupportFragmentManagement.FragmentChanger;

import java.util.Objects;

public class WellcomeFragment extends Fragment {
    private NavigationView navigationView;

    private boolean isShowingNavDrawer = false;

    public static AppFragmentPossibilities currentFragment;
    public static Fragment fragment;
    private RecomendationsFragment recomendationsFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tmbd_content, container, false);
    }

    @SuppressLint("NonConstantResourceId")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentFragment = AppFragmentPossibilities.RecomendationsFragment;

        navigationView = view.findViewById(R.id.navigationView);
        MaterialToolbar toolbar = view.findViewById(R.id.topAppBar);

        toolbar.setOnClickListener(v -> changeDrawerVisibility());

        navigationView.setNavigationItemSelectedListener(item -> {
            if (currentFragment != AppFragmentPossibilities.RecomendationsFragment) {
                NavDirections navDirections = FragmentChanger.anyFragmentToRecomendations(currentFragment);
                Navigation.findNavController(fragment.requireView()).navigate(navDirections);
            }
            if (currentFragment == AppFragmentPossibilities.RecomendationsFragment) {
                recomendationsFragment = (RecomendationsFragment) fragment;

                switch (item.getItemId()){
                    case R.id.item_peliculas:
                        recomendationsFragment.currentState = RecomendationsFragment.State.films;
                        break;
                    case R.id.item_series:
                        recomendationsFragment.currentState = RecomendationsFragment.State.series;
                        break;
                    case R.id.item_actores:
                        recomendationsFragment.currentState = RecomendationsFragment.State.actors;
                        break;
                    case R.id.item_favoritos:
                        recomendationsFragment.currentState = RecomendationsFragment.State.favourites;
                        break;
                }
                recomendationsFragment.changeRecyclerViews();
            }
            return false;
        });

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.search) {
                if (currentFragment != AppFragmentPossibilities.SearchFragment) {
                    NavDirections navDirections = FragmentChanger.anyFragmentToSearch(currentFragment);
                    Navigation.findNavController(fragment.requireView()).navigate(navDirections); }
            }
            return false;
        });
    }

    public void changeDrawerVisibility() {
        int visibility;
        if (isShowingNavDrawer) visibility = View.INVISIBLE;
        else visibility = View.VISIBLE;
        navigationView.setVisibility(visibility);
        isShowingNavDrawer = !isShowingNavDrawer;
    }
}
