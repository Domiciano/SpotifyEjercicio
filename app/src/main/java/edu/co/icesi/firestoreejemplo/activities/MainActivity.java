package edu.co.icesi.firestoreejemplo.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;


import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.UUID;

import edu.co.icesi.firestoreejemplo.R;
import edu.co.icesi.firestoreejemplo.activities.spotify.Response;


public class MainActivity extends AppCompatActivity {


    private String userID = "sEvUC3ZuLECp1I576hGy";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //addSong(new Song(UUID.randomUUID().toString(), "Beta", "JBalvin"));

        //getMySongs();

        searchSong("Beat it");

    }

    public void searchSong(String title){

        new Thread(
                ()->{
                    HTTPSWebUtilDomi web = new HTTPSWebUtilDomi();
                    String json = web.GETrequest(title);

                    Gson gson = new Gson();
                    Response response = gson.fromJson(json, Response.class);


                    Log.e(">>>", response.getTracks().getItems()[0].getName());
                    Log.e(">>>", response.getTracks().getItems()[0].getArtists()[0].getName());


                    addSong(new Song(
                            UUID.randomUUID().toString(),
                            response.getTracks().getItems()[0].getName(),
                            response.getTracks().getItems()[0].getArtists()[0].getName())
                    );

                }
        ).start();


    }


    private void getMySongs() {
        db.collection("playlist").document(userID).collection("canciones").get().addOnCompleteListener(
                task->{
                    for(DocumentSnapshot doc : task.getResult()){
                        Song song = doc.toObject(Song.class);
                        Log.e(">>>", song.getName());
                        Log.e(">>>", song.getArtist());
                    }
                }
        );



    }

    public void addSong(Song song){
        db.collection("playlist")
                .document(userID).collection("canciones").document(song.getId()).set(song);

    }





}