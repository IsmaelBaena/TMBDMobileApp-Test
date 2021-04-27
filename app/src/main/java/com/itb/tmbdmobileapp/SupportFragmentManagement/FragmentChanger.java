package com.itb.tmbdmobileapp.SupportFragmentManagement;

import androidx.navigation.NavDirections;

import com.itb.tmbdmobileapp.Fragments.ActorDetailsFragmentDirections;
import com.itb.tmbdmobileapp.Fragments.FilmAndSeriesDetailsFragmentDirections;
import com.itb.tmbdmobileapp.Fragments.RecomendationsFragmentDirections;
import com.itb.tmbdmobileapp.Fragments.SearchFragmentDirections;

public class FragmentChanger {

    public static NavDirections anyFragmentToRecomendations(AppFragmentPossibilities fragment) {
        NavDirections navDirections = null;

        switch (fragment) {
            case ActorDetailsFragment:
                navDirections = ActorDetailsFragmentDirections.actorDetailsToRecomendations();
                break;
            case FilmAndSeriesDetailsFragment:
                navDirections = FilmAndSeriesDetailsFragmentDirections.filmAndSeriesDetailsToRecomendations();
                break;
            case SearchFragment:
                navDirections = SearchFragmentDirections.searchToRecomendations();
                break;
        }

        return navDirections;
    }

    public static NavDirections anyFragmentToSearch(AppFragmentPossibilities fragment) {
        NavDirections navDirections = null;

        switch (fragment) {
            case ActorDetailsFragment:
                navDirections = ActorDetailsFragmentDirections.actorDetailsToSearch();
                break;
            case FilmAndSeriesDetailsFragment:
                navDirections = FilmAndSeriesDetailsFragmentDirections.filmAndSeriesDetailsToSearch();
                break;
            case RecomendationsFragment:
                navDirections = RecomendationsFragmentDirections.recomendationsToSearch();
                break;
        }

        return navDirections;
    }
}
