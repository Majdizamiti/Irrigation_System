package com.example.irrigation_system;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


// Définition de la classe Home_page qui hérite de la classe AppCompatActivity
public class Home_page extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private TextView etat_vanne1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button auto = findViewById(R.id.auto);
        // Obtenir une référence à la base de données Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Récupérer les références des éléments de l'interface utilisateur
        Context context = getApplicationContext();
        Button vanne1on = findViewById(R.id.vanne1on);
        Button vanne1off = findViewById(R.id.vanne1off);
        etat_vanne1 = findViewById(R.id.etat_vanne1);


        // Créer les références des nœuds dans la base de données Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myTempRef = database.getReference("temperature");
        DatabaseReference myHumidityRef = database.getReference("humidity");
        DatabaseReference mylightRef = database.getReference("light_intensity");
        DatabaseReference mysoilmoinstureRef = database.getReference("soil_moisture");
        DatabaseReference mywater1Ref = database.getReference("water_level_1");
        DatabaseReference myvanne1Ref = database.getReference("vanne1");
        // Récupérer les références des éléments de l'interface utilisateur pour afficher les valeurs
        TextView temperaturevalue = findViewById(R.id.temperaturevalue);
        TextView humidityvalue = findViewById(R.id.humidityvalue);
        TextView lightvalue = findViewById(R.id.lightvalue);
        TextView soilmoinsturevalue = findViewById(R.id.soilmoinsturevalue);
        TextView water_1value = findViewById(R.id.water_1value);

        // Ajouter des écouteurs de valeur aux références de la base de données pour mettre à jour l'interface utilisateur en temps réel

        // Méthode pour récupérer la valeur de température depuis Firebase et l'afficher dans le TextView correspondant
        myTempRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Lorsque le donnée change, récupérer la nouvelle valeur et la définir dans l'élément TextView correspondant
                String value = dataSnapshot.getValue().toString();
                temperaturevalue.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                // En cas d'erreur lors de la récupération des données, afficher le message d'erreur dans les logs
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

        // Méthode pour récupérer la valeur de humidité depuis Firebase et l'afficher dans le TextView correspondant
        myHumidityRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getValue() != null) {
                // Lorsque le donnée change, récupérer la nouvelle valeur et la définir dans l'élément TextView correspondant
                String value = dataSnapshot.getValue().toString();
                humidityvalue.setText(value);
            }else {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                // En cas d'erreur lors de la récupération des données, afficher le message d'erreur dans les logs
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

        // Méthode pour récupérer la valeur de lumiére depuis Firebase et l'afficher dans le TextView correspondant
        mylightRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getValue() != null) {
                // Lorsque le donnée change, récupérer la nouvelle valeur et la définir dans l'élément TextView correspondant
                String value = dataSnapshot.getValue().toString();
                lightvalue.setText(value);
            }else {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                // En cas d'erreur lors de la récupération des données, afficher le message d'erreur dans les logs
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

        // Méthode pour récupérer la valeur de humidité du sol depuis Firebase et l'afficher dans le TextView correspondant
        mysoilmoinstureRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getValue() != null) {
                // Lorsque le donnée change, récupérer la nouvelle valeur et la définir dans l'élément TextView correspondant
                String value = dataSnapshot.getValue().toString();
                soilmoinsturevalue.setText(value);


            }else {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                // En cas d'erreur lors de la récupération des données, afficher le message d'erreur dans les logs
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

        // Méthode pour récupérer la valeur de niveau d'eau 1 depuis Firebase et l'afficher dans le TextView correspondant
        mywater1Ref.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getValue() != null) {
                // Lorsque le donnée change, récupérer la nouvelle valeur et la définir dans l'élément TextView correspondant
                String value = dataSnapshot.getValue().toString();
                water_1value.setText(value);
            }else {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                // En cas d'erreur lors de la récupération des données, afficher le message d'erreur dans les logs
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });


        // Gestion du clic sur le bouton "vanne1on"
        vanne1on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Définition de la valeur "1" pour la clé "vanne1" dans la base de données
                mDatabase.child("vanne1").setValue(1);

                

            }
        });

        // Gestion du clic sur le bouton "vanne1off"
        vanne1off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Définition de la valeur "0" pour la clé "vanne1" dans la base de données
                mDatabase.child("vanne1").setValue(0);

            }
        });


        // Ajout d'un ValueEventListener à la référence "myvanne1Ref" dans la base de données
        myvanne1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Récupération de la valeur de la référence "myvanne1Ref" sous forme de chaîne de caractères
                String value = dataSnapshot.getValue().toString();

                // Vérification de la valeur et mise à jour de l'état de la vanne 1 en conséquence
                if (value.equals("1")) {
                    etat_vanne1.setText("ON");
                } else if (value.equals("0")) {
                    etat_vanne1.setText("OFF");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                // Affichage d'une erreur en cas de problème lors de la lecture des données
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });


        // Ajout d'un ValueEventListener à la référence "mywater1Ref" dans la base de données
        mywater1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getValue() != null) {

                // Récupération de la valeur de la référence "mywater1Ref" sous forme d'entier
                int waterLevel1 = dataSnapshot.getValue(Integer.class);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Gestion de l'annulation de l'événement
            }
        });
        // Gestion du clic sur le bouton "auto"
        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Appel de la méthode "irrigation_automatique()"
                irrigation_automatique();
            }
        });

        mywater1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Récupération de la valeur de la référence "mywater1Ref" sous forme d'entier
                int waterLevel1 = dataSnapshot.getValue(Integer.class);
                // Vérification du niveau d'eau et envoi d'une notification si le niveau est inférieur à 250
                if (waterLevel1 < 250) {
                    //appel a la methode sendNotification avec les parametéres (context, "Water level 1 low", "You must fill the first tank", 1)
                    sendNotification(Home_page.this, "Water level 1 low", "You must fill the tank", 1);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Gestion de l'annulation de l'événement
            }
        });

    }
    // Méthode pour envoyer une notification
    public void sendNotification(Context context, String title, String message, int notificationId) {
        // Obtention du gestionnaire de notifications
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // Création du canal de notification pour les versions d'Android supérieures ou égales à Oreo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "default_channel_id";
            CharSequence channelName = "Default Channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId,
                    channelName, importance);
            notificationManager.createNotificationChannel(channel);
        }
        // Création du constructeur de la notification
        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(context, "default_channel_id")
                .setSmallIcon(R.drawable.asis)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        // Envoi de la notification avec l'ID spécifié
        notificationManager.notify(notificationId, notificationBuilder.build());
    }
    // Méthode pour l'irrigation automatique
    public void irrigation_automatique() {
        // Création d'une intention pour démarrer une nouvelle activité ("formulaire")
        Intent intent = new Intent(this, Formulaire.class) ;
        startActivity(intent);
    }
}

