package com.example.numberguess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button guessButton, statButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        /*
        Transfers the user to the guessing game
         */
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GuessingGame.class);
                Toast.makeText(MainActivity.this,  "Must complete game to enter Database", Toast.LENGTH_LONG).show();
                startActivity(intent);

            }

        });

    }
    private void initData() {

            guessButton = findViewById(R.id.guessButton);

        }
    }

