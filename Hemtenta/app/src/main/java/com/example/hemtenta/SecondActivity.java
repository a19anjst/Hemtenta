package com.example.hemtenta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView textView = findViewById(R.id.fishtext);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String location = extras.getString("location");
            String size = extras.getString("size");
            String names = extras.getString("names");
            String prefs = extras.getString("prefs");
            String img = extras.getString("img");
            textView.setText("The " + names + " lives in the " + location + " and prefers " + prefs + ". It is usually " + size + " long.");

        }
        Button close = findViewById(R.id.go_back);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
