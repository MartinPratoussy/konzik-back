package com.konzik.common.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "concerts")
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String date;
    private String artist; // TODO: make a whole Artist entity
    private String genre;
    private String location;
    private String city;
    private String country;

    @ManyToMany(mappedBy = "planning")
    private List<User> users = new ArrayList<>();

    public Concert() {

    }

    public Concert(String date, String artist, String genre, String location, String city, String country) {
        this.date = date;
        this.artist = artist;
        this.genre = genre;
        this.location = location;
        this.city = city;
        this.country = country;
    }

    public UUID getId() {
        return id;
    }

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

    @Override
    public String toString() {
        return "Concert{" +
                "id=" + id +
                ", date=" + date +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", location='" + location + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}