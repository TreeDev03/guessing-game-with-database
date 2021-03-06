package com.example.numberguess;



import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

    class MyDatabaseHelper extends SQLiteOpenHelper {

/*
DataBase table headings
 */

        private Context context;
        private static final String DATABASE_NAME = "Stats";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_NAME = "Stats";
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_Name = "player_name";
        private static final String COLUMN_Date = "date";
        private static final String COLUMN_Correct = "correct_number";

        MyDatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME +
                    " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_Name + " TEXT, " +
                    COLUMN_Date + " TEXT, " +
                    COLUMN_Correct + " INTEGER);";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

        /*
        Takes the values from the GuessingGame class and adds them to the data base by using their ID
         */

        void addBook(String playerName, String date, String correct){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_Name, playerName);
            cv.put(COLUMN_Date, date);
            cv.put(COLUMN_Correct, correct);
            long result = db.insert(TABLE_NAME,null, cv);
            if(result == -1){
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        }

    }
