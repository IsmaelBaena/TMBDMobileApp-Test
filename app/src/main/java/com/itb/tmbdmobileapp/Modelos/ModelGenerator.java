package com.itb.tmbdmobileapp.Modelos;

import com.itb.tmbdmobileapp.R;

import java.util.ArrayList;
import java.util.List;

public class ModelGenerator {
    private static final String[] movieTitles = {"Al Filo del Mañana", "Into the wild", "Princesas", "Kids", "Pulp Fiction", "Amores perros", "Reservoir dogs", "Corre Lola Corre", "La lista de Schindler", "Tiburón", "La misión", "León", "Azul oscuro casi negro", "Tesis", "Annie Hall"};
    private static final String[] actorNames = {"Juan", "Marta", "María", "Pau", "Imsael", "Roger", "Jordi", "Mónica", "Beatriz", "Joel", "Iván", "Mary", "Alexandra", "Verónica", "Yaiza"};
    private static final int[] actorFotos = {R.drawable.actor1, R.drawable.actor2, R.drawable.actor3};
    private static final int[] filmFotos = {R.drawable.film1, R.drawable.film2, R.drawable.film3};

    public static List<MovieTest> films() {
        int valorEntero = (int)Math.floor(Math.random()*(5-(15+1))+(15));
        List<MovieTest> movies = new ArrayList<>();
        for (int x = 0; x < valorEntero; x++) {
            int puntuation = (int) (Math.floor(Math.random()*(1-100+1)+100)), description = R.string.lore_ipsum;
            int photo = filmFotos[(int)Math.floor(Math.random()* filmFotos.length)];
            String title = movieTitles[(int)Math.floor(Math.random()*movieTitles.length)];
            List<ActorTest> actors = null;
            movies.add(new MovieTest(photo, puntuation, description, title, actors));
        }
        return movies;
    }

    public static List<ActorTest> actors() {
        int valorEntero = (int) (Math.floor(Math.random()*(5-15+1)+15));
        List<ActorTest> actorTests = new ArrayList<>();
        for (int x = 0; x < valorEntero; x++) {
            int puntuation = (int) (Math.floor(Math.random()*(1-100+1)+100)), description = R.string.lore_ipsum;
            int photo = actorFotos[(int)Math.floor(Math.random()* actorFotos.length)];
            String title = actorNames[(int)Math.floor(Math.random()*actorNames.length)];
            List<MovieTest> actors = films();
            actorTests.add(new ActorTest(photo, puntuation, description, title, actors));
        }
        return actorTests;
    }


}
