package com.itb.tmbdmobileapp.Modelos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MovieTest implements Parcelable {
    private int photo, puntuation, description;
    private String title;

    private List<ActorTest> actores;

    public MovieTest(int photo, int puntuation, int description, String title, List<ActorTest> actores) {
        this.photo = photo;
        this.puntuation = puntuation;
        this.description = description;
        this.title = title;
        this.actores = actores;
    }

    protected MovieTest(Parcel in) {
        photo = in.readInt();
        puntuation = in.readInt();
        description = in.readInt();
        title = in.readString();
        actores = in.createTypedArrayList(ActorTest.CREATOR);
    }

    public static final Creator<MovieTest> CREATOR = new Creator<MovieTest>() {
        @Override
        public MovieTest createFromParcel(Parcel in) {
            return new MovieTest(in);
        }

        @Override
        public MovieTest[] newArray(int size) {
            return new MovieTest[size];
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ActorTest> getActores() {
        return actores;
    }

    public void setActores(List<ActorTest> actores) {
        this.actores = actores;
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
        dest.writeString(title);
        dest.writeTypedList(actores);
    }
}
