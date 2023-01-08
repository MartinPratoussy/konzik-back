package com.konzik.Concert.payload.request;

import javax.validation.constraints.NotBlank;

public class AddConcertRequest {
    @NotBlank
    private String date;
    @NotBlank
    private String artist; // TODO: make a whole Artist entity
    @NotBlank
    private String genre;
    @NotBlank
    private String location;
    @NotBlank
    private String city;
    @NotBlank
    private String country;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
