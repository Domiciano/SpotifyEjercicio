package edu.co.icesi.firestoreejemplo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import java.util.ArrayList;

import edu.co.icesi.firestoreejemplo.R;
import edu.co.icesi.firestoreejemplo.models.User;

public class HomeActivity extends AppCompatActivity {

    private User user;

    private ListView userListView;
    private ArrayList<User> users;
    private ArrayAdapter<User> adapter;
    private Button logoutBTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //Load user from SP
        User loadedUser = loadUser();
        if(loadedUser == null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }else{
            this.user = loadedUser;
        }



        FirebaseMessaging.getInstance().subscribeToTopic(user.getId());


        userListView = findViewById(R.id.userListView);
        users = new ArrayList<>();
        adapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        userListView.setAdapter(adapter);

        logoutBTN = findViewById(R.id.logoutBTN);


        FirebaseFirestore.getInstance().collection("users").get().addOnCompleteListener(
                task ->{
                    for(DocumentSnapshot doc : task.getResult()){
                        User user = doc.toObject(User.class);
                        users.add(user);
                        adapter.notifyDataSetChanged();
                    }
                }
        );

        userListView.setOnItemClickListener((parent, view, position, id) -> {
            User contact = users.get(position);
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("user", user);
            intent.putExtra("contact", contact);
            startActivity(intent);
        });

        logoutBTN.setOnClickListener(v->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            FirebaseMessaging.getInstance().unsubscribeFromTopic(user.getId());
            getSharedPreferences("appmoviles", MODE_PRIVATE).edit().clear().apply();
        });

    }

    private User loadUser() {
        String json = getSharedPreferences("appmoviles", MODE_PRIVATE).getString("user", "NO_USER");
        if(json.equals("NO_USER")){
            return null;
        }else{
            return new Gson().fromJson(json, User.class);
        }
    }
}