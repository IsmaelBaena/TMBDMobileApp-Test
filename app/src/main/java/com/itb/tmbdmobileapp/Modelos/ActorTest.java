package com.itb.tmbdmobileapp.Modelos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ActorTest implements Parcelable {
    private int photo, puntuation, description;
    private String name;

    private List<MovieTest> movieTests;

    public ActorTest(int photo, int puntuation, int description, String name, List<MovieTest> movieTests) {
        this.photo = photo;
        this.puntuation = puntuation;
        this.description = description;
        this.name = name;
        this.movieTests = movieTests;
    }

    protected ActorTest(Parcel in) {
        photo = in.readInt();
        puntuation = in.readInt();
        description = in.readInt();
        name = in.readString();
        movieTests = in.createTypedArrayList(MovieTest.CREATOR);
    }

    public static final Creator<ActorTest> CREATOR = new Creator<ActorTest>() {
        @Override
        public ActorTest createFromParcel(Parcel in) {
            return new ActorTest(in);
        }

        @Override
        public ActorTest[] newArray(int size) {
            return new ActorTest[size];
        }
    };

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getPuntuation() {
        return puntuation;
    }

    public void setPuntuation(int puntuation) {
        this.puntuation = puntuation;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovieTest> getMovieTests() {
        return movieTests;
    }

    public void setMovieTests(List<MovieTest> movieTests) {
        this.movieTests = movieTests;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(photo);
        dest.writeInt(puntuation);
        dest.writeInt(description);
        dest.writeString(name);
        dest.writeTypedList(movieTests);
    }
}
