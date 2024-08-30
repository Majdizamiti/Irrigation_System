package com.example.irrigation_system;

// Importation de la classe AppCompatActivity du package androidx

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
// Définition de la classe formulaire qui hérite de la classe AppCompatActivity
public class Formulaire extends AppCompatActivity {
    // Déclaration de la variable mDatabase de type DatabaseReference
    private DatabaseReference mDatabase;
    // Définition de la méthode onCreate, qui est exécutée lors de la création de l'activité
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);
        // Récupérer une référence à la base de données Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // Récupérer les références des champs de saisie dans le formulaire
        EditText temperatureform = findViewById(R.id.temperatureform);
        EditText humiditéform = findViewById(R.id.humiditéform);
        EditText lumiéreform = findViewById(R.id.lumiéreform);
        EditText humidité_du_solform = findViewById(R.id.humidité_du_solform);
        EditText niveau_eau_1form = findViewById(R.id.niveau_eau_1form);
        Button reset = findViewById(R.id.reset);
        Button ok = findViewById(R.id.ok);
        // Définir un écouteur de clic pour le bouton "reset"
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Réinitialiser les champs de saisie en les vidant
                temperatureform.setText("");
                humiditéform.setText("");
                lumiéreform.setText("");
                humidité_du_solform.setText("");
                niveau_eau_1form.setText("");
                //niveau_eau_2form.setText("");
            }
        });
        // Définir un écouteur de clic pour le bouton "ok"
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les valeurs saisies dans les champs de saisie
                String value1 = temperatureform.getText().toString();
                String value2 = humiditéform.getText().toString();
                String value3 = lumiéreform.getText().toString();
                String value4 = humidité_du_solform.getText().toString();
                String value5 = niveau_eau_1form.getText().toString();
               // String value6 = niveau_eau_2form.getText().toString();
                // Enregistrer les valeurs dans la base de données Firebase
                mDatabase.child("autoValue").setValue(1);
                mDatabase.child("vanne1").setValue(0);
                mDatabase.child("vanne2").setValue(0);
                // Vérifier si le champ de température est vide
                if (temperatureform.getText().toString().isEmpty()) {
                    // Si le champ est vide, envoyer une valeur de température vide à Firebase
                    mDatabase.child("temperature_auto").setValue(0);
                    Toast.makeText(Formulaire.this, "Empty temperature value sent", Toast.LENGTH_SHORT).show();
                } else {
                    // Envoyer la valeur de température saisie à Firebase
                    mDatabase.child("temperature_auto").setValue(value1);
                    Toast.makeText(Formulaire.this, "temperature value sent successfully", Toast.LENGTH_SHORT).show();
                }
                // Vérifier si le champ d'humidité est vide
                if (humiditéform.getText().toString().isEmpty()) {
                    // Si le champ est vide, envoyer une valeur d'humidité vide à Firebase
                    mDatabase.child("humidity_auto").setValue(0);
                    Toast.makeText(Formulaire.this, "Empty humidity value sent",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // Envoyer la valeur de humidité saisie à Firebase
                    mDatabase.child("humidity_auto").setValue(value2);
                    Toast.makeText(Formulaire.this, "humidity value sent successfully", Toast.LENGTH_SHORT).show();
                }
                if (lumiéreform.getText().toString().isEmpty()) {
                    // Si le champ est vide, envoyer une valeur de lumiére vide à Firebase
                    mDatabase.child("light_auto").setValue(0);
                    Toast.makeText(Formulaire.this, "Empty light value sent",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // Envoyer la valeur de lumiére saisie à Firebase
                    mDatabase.child("light_auto").setValue(value3);
                    Toast.makeText(Formulaire.this, "light value sent successfully", Toast.LENGTH_SHORT).show();
                }
                if (humidité_du_solform.getText().toString().isEmpty()) {
                    // Si le champ est vide, envoyer une valeur d'humidité du sol vide à Firebase
                    mDatabase.child("soilmoisture_auto").setValue(0);
                    Toast.makeText(Formulaire.this, "Empty soil moisture value sent", Toast.LENGTH_SHORT).show();
                } else {
                    // Envoyer la valeur de humidité du sol saisie à Firebase
                    mDatabase.child("soilmoisture_auto").setValue(value4);
                    Toast.makeText(Formulaire.this, "soil moisture value sent successfully", Toast.LENGTH_SHORT).show();
                }
                if (niveau_eau_1form.getText().toString().isEmpty()) {
                    // Si le champ est vide, envoyer une valeur de niveau eau 1 vide à Firebase
                    mDatabase.child("waterlevel1_auto").setValue(0);
                    Toast.makeText(Formulaire.this, "Empty water level1 value sent", Toast.LENGTH_SHORT).show();
                } else {
                    // Envoyer la valeur de niveau eau 1 saisie à Firebase
                    mDatabase.child("waterlevel1_auto").setValue(value5);
                    Toast.makeText(Formulaire.this, "water level1 value sent successfully", Toast.LENGTH_SHORT).show();
                }
                /*if (niveau_eau_2form.getText().toString().isEmpty()) {
                    // Si le champ est vide, envoyer une valeur de niveau eau2 vide à Firebase
                    mDatabase.child("waterlevel2_auto").setValue(0);
                    Toast.makeText(Formulaire.this, "Empty water level2 value sent", Toast.LENGTH_SHORT).show();
                } else {
                    // Envoyer la valeur de niveau eau 2 saisie à Firebase
                    mDatabase.child("waterlevel2_auto").setValue(value6);
                    Toast.makeText(Formulaire.this, "water level2 value sent successfully", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }
}