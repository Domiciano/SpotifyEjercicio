package edu.co.icesi.firestoreejemplo.activities.spotify;

public class Track {

    private String name;
    private Artist[] artists;

    public Track() {
    }

    public Track(String name, Artist[] artists) {
        this.name = name;
        this.artists = artists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist[] getArtists() {
        return artists;
    }

    public void setArtists(Artist[] artists) {
        this.artists = artists;
    }
}
