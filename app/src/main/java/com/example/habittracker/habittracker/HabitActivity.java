package com.example.habittracker.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
// TODO: Identify HabitEntry subclass from HabitContract class
import com.example.habittracker.habittracker.data.HabitContract.HabitEntry;
import com.example.habittracker.habittracker.data.HabitDbHelper;

import static android.R.attr.id;


public class HabitActivity extends AppCompatActivity {

    // Database helper that will provide us access to the database
    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabitDbHelper(this);

        String id = "0";

        insertHabit();
        query();
        getHabit(id);

    }

    /* query method
      * Helper method to reade from the database using Cursor
    */
    private void query()
    {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_STATUS,
                HabitEntry.COLUMN_HABIT_NEED };

        // Perform a query on the Habits table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,     // The table to query
                projection,                // The columns to return
                null,                      // The columns for the WHERE clause
                null,                      // The values for the WHERE clause
                null,                      // Don't group the rows
                null,                      // Don't filter by row groups
                null);                     // The sort order


        try {
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int statusColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_STATUS);
            int needColumnIndex = cursor.getColumnIndex((HabitEntry.COLUMN_HABIT_NEED));

            // Loop through all the returned rows in the cursor
            while (cursor.moveToNext())
            {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(idColumnIndex);
                int currentStatus = cursor.getInt(idColumnIndex);
                String currentNeed = cursor.getString(idColumnIndex);
            }
        }
        finally {
            // Close the cursor
            cursor.close();
        }
    }

    /* getHabit method
      * Helper method to read the database where id = 0
     */
    private Cursor getHabit(String id) {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_STATUS,
                HabitEntry.COLUMN_HABIT_STATUS
        };

        // Define 'where' habit of query
        String selection = HabitEntry._ID + "=?";
        // specify argument in placeholder order
        String[] selectionArg = new String[]{id};

        try {
            Cursor c = db.query(HabitEntry.TABLE_NAME, projection, selection, selectionArg, null, null, null);
            return c;

        } catch (Exception e){
            return null;
        }
    }

    /* insertHabit method
      * Helper method to write to the database
    */
    private void insertHabit()
    {
        // Create and/or open a database to write on it
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object                                        1
        ContentValues valuesOne = new ContentValues();
        valuesOne.put(HabitEntry.COLUMN_HABIT_NAME, "Jogging");
        valuesOne.put(HabitEntry.COLUMN_HABIT_STATUS, "1");
        valuesOne.put(HabitEntry.COLUMN_HABIT_NEED, "Necessary");

        // Insert new valuesOne to the DB
        db.insert(HabitEntry.TABLE_NAME, null, valuesOne);


        // Create a ContentValues object                                        2
        ContentValues valuesTwo = new ContentValues();
        valuesTwo.put(HabitEntry.COLUMN_HABIT_NAME, "Talking too much");
        valuesTwo.put(HabitEntry.COLUMN_HABIT_STATUS, "0");
        valuesTwo.put(HabitEntry.COLUMN_HABIT_NEED, "No Need");

        // Insert new valuesTwo to the DB
        db.insert(HabitEntry.TABLE_NAME, null, valuesTwo);
    }
}