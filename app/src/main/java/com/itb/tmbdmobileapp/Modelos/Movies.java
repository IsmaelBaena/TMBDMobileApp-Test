package com.itb.tmbdmobileapp.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Movies {

    private int id;
    private String overview, title, poster_path, release_date;

    private List<People> people = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();
}
