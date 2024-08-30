package com.example.irrigation_system;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FutureActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterTommorow ;
    private RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_future);
        setVariable();
        initRecyclerView();
    }
    private void initRecyclerView(){
        ArrayList<FutureDomain> items = new ArrayList<>();

        items.add(new FutureDomain("sun", "storm", "storm",18,5));
        items.add(new FutureDomain("mon", "cloudy", "cloudy",12,2));
        items.add(new FutureDomain("tue", "windy", "wind",17,7));
        items.add(new FutureDomain("wed", "cloudy_sunny", "ms_cloudy",28,15));
        items.add(new FutureDomain("thu", "cloudy_sunny", "ms_cloudy",28,15));
        items.add(new FutureDomain("fri", "cloudy_sunny", "ms_cloudy",28,15));
        items.add(new FutureDomain("sat", "rainy", "rainy",10,2));

        recyclerView= findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterTommorow = new FutureAdapter(items);
        recyclerView.setAdapter(adapterTommorow);

    }
    private void setVariable(){
        ImageButton backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(view -> {
            startActivity(new Intent(FutureActivity.this, MainActivity.class));
        });

    }

}

