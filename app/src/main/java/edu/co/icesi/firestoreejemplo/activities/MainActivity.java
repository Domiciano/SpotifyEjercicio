package edu.co.icesi.firestoreejemplo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import java.util.UUID;

import edu.co.icesi.firestoreejemplo.R;
import edu.co.icesi.firestoreejemplo.models.User;
import edu.co.icesi.firestoreejemplo.util.NotificationUtil;

public class MainActivity extends AppCompatActivity {


    private TextInputEditText usernameET;
    private TextInputEditText passET;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameET = findViewById(R.id.usernameET);
        passET = findViewById(R.id.passET);
        loginBtn = findViewById(R.id.loginBtn);


        loginBtn.setOnClickListener(this::login);


        NotificationUtil.showNotification(this, "AppMoviles","Hola mundo");

        FirebaseMessaging.getInstance().subscribeToTopic("promo");

    }

    private void login(View view) {
        String username = usernameET.getText().toString();
        String pass = passET.getText().toString();
        User user = new User(UUID.randomUUID().toString(), username, pass);

        Query query = FirebaseFirestore.getInstance().collection("users").whereEqualTo("username", username);
        query.get().addOnCompleteListener(
                task->{

                    //Si el usuario no existe crearlo e iniciar sesion con él
                    if(task.getResult().size() == 0){
                        FirebaseFirestore.getInstance().collection("users").document(user.getId()).set(user);
                        Intent intent = new Intent(this, HomeActivity.class);
                        saveUser(user);
                        startActivity(intent);
                    }



                    //Si ya existe, descargar el usuario e iniciar sesion con el
                    else{
                        User existingUser = null;
                        for(DocumentSnapshot doc : task.getResult()){
                            existingUser = doc.toObject(User.class);
                            break;
                        }
                        if(existingUser.getPassword().equals(pass)){
                            Intent intent = new Intent(this, HomeActivity.class);
                            saveUser(existingUser);
                            startActivity(intent);
                        }else{
                            Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_LONG).show();
                        }

                    }

                }
        );




        //
    }

    private void saveUser(User user) {
        String json = new Gson().toJson(user);
        getSharedPreferences("appmoviles", MODE_PRIVATE).edit().putString("user", json).apply();

    }
}