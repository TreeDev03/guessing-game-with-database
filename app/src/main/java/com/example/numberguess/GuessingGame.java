
package com.example.numberguess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;


public class GuessingGame extends AppCompatActivity {
    private TextView date;
    private TextView correct;
    private Button add;
    private EditText playerName;


    int result;

    static int getRandomNumber(int max, int min) {

        return (int) ((Math.random()
                * (max - min)) + min);
    }

    public void makeToast(String str) {
        Toast.makeText(
                GuessingGame.this,
                str,
                Toast.LENGTH_SHORT)
                .show();
    }


/*
Checks if the number the user entered is equal to the random number
 */

    public void clickFunction(View view) {

        intData();

        int userGuessing = 0;
        EditText variable
                = (EditText) findViewById(R.id.editGuess);
        try {
            userGuessing
                    = Integer.parseInt(variable
                    .getText()
                    .toString());


        } catch(NumberFormatException e) {
            Toast.makeText(GuessingGame.this, "Please Enter Integer ", Toast.LENGTH_SHORT).show();
        }



        if (userGuessing < result) {
            makeToast("Think of Higher Number"
            );


        } else    if (userGuessing > 20 || userGuessing == 0) {
            makeToast("Enter Number In Range Please");

        }  else if (userGuessing > result) {
                makeToast("Think of a lower Number");

            }

          else {

            /*
            Checks if the game is finished.
            Gets all the values and adds them to the database.
             */

            add.setVisibility(View.VISIBLE);

            DateTime dt = new DateTime();

            LocalTime time = LocalTime.now(); //gives System time into localTime object
            System.out.println(time); // 10:19:58

            makeToast(
                    "Congratulations,"
                            + " You Got the Number");


            correct.setText(String.valueOf(result + " was the correct number"));
            date.setText(String.valueOf(dt.getMonthOfYear() + "/" + dt.getDayOfMonth() + "/" + dt.getYear() + "\n" + time));

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(GuessingGame.this);
                    myDB.addBook(playerName.getText().toString().trim(),
                            date.getText().toString().trim(),
                            correct.getText().toString().trim());


                }
            });

        }
    }

    @Override
    protected void onCreate(
            Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        int min = 1;
        int max = 20;
        result = getRandomNumber(min, max);


    }

    public void intData() {

        date = findViewById(R.id.textViewDate);
        correct = findViewById(R.id.textViewCorrect);

        add = findViewById(R.id.addButton);
        playerName = findViewById(R.id.playerName);
    }

}

