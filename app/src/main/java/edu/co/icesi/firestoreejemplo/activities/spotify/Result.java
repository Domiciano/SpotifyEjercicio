package edu.co.icesi.firestoreejemplo.activities.spotify;

public class Result {

    private Track[] items;


    public Result() {
    }

    public Result(Track[] items) {
        this.items = items;
    }

    public Track[] getItems() {
        return items;
    }

    public void setItems(Track[] items) {
        this.items = items;
    }
}
