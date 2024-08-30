package com.example.irrigation_system;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

// Définition de la classe login qui hérite de la classe AppCompatActivity
public class login extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword;
    Button signIn;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Récupération des références des éléments de l'interface utilisateur
        TextView admin_1 = findViewById(R.id.admin_1);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        signIn = findViewById(R.id.sign_in);


        LinearLayout linearLayout = findViewById(R.id.loginpage);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(10);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();





        // Ajout d'un listener sur le bouton "Sign In"
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                // Vérification si l'email est vide
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Vérification si le mot de passe est vide
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tentative de connexion avec l'email et le mot de passe fournis
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    // Connexion réussie, redirection vers la page d'accueil
                                    Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {

                                    // Échec de l'authentification
                                    Toast.makeText(login.this, "Authentification failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }


        });

        // Ajout d'un listener sur le texte "admin_1"
        admin_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Ouverture d'une intention pour afficher la page Google Gmail
                String url = "https://www.google.com/intl/fr/gmail/about/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

    }



}
