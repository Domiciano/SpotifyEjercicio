package edu.co.icesi.firestoreejemplo.activities.spotify;

public class Response {
    private Result tracks;

    public Response(Result tracks) {
        this.tracks = tracks;
    }

    public Response() {
    }

    public Result getTracks() {
        return tracks;
    }

    public void setTracks(Result tracks) {
        this.tracks = tracks;
    }
}
