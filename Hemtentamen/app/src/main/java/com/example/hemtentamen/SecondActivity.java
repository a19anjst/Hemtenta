package com.example.hemtentamen;

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
        TextView textView = findViewById(R.id.godtext);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String location = extras.getString("location");
            String size = extras.getString("size");
            String names = extras.getString("names");
            String prefs = extras.getString("prefs");
            textView.setText("The " + names + " lives in " + location + " and prefers " + prefs + ". It is usually " + size + " long.");
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
