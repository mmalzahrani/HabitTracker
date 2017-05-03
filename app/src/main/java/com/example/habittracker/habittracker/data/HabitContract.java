package com.example.habittracker.habittracker.data;

import android.provider.BaseColumns;

public class HabitContract {

    private HabitContract() {};

    // Inner class that defines constant values for the Habit database table.
    public static final class HabitEntry implements BaseColumns {

        /* Name of database table for Habit */
        public static final String TABLE_NAME = "habits";

        /*
         * Unique ID number for the Habit (only for use in the database table).
         * Type: INTEGER
        */
        public static final String _ID = BaseColumns._ID;

        /*
         * name of Habit.
         * Type: TEXT
        */
        public static final String COLUMN_HABIT_NAME = "name";

        /*
         * status of Habit. (Good or Bad)
         * Type: INTEGER
        */
        public static final String COLUMN_HABIT_STATUS = "status";

        /*
         * need of Habit.
         * Type: TEXT
        */
        public static final String COLUMN_HABIT_NEED = "need";


        /* Possible values for the status of the Habit. */
        public static final int STATUS_BAD = 0;
        public static final int STATUS_GOOD = 1;

    }
}
