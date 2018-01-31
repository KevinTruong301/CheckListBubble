package edu.fullerton.kevin.checklistbubble;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kevin on 1/29/2018.
 */

public class CheckListDB {
    public static String DB_NAME = "check.db";
    public static int DB_VERSION = 1;

    public static String CHECK_NAMES_TABLE = "CheckNames";

    public static String CHECK_NAMES_ID = "ID";
    public static int CHECK_NAMES_ID_COLUMN = 0;

    public static String CHECK_NAMES_NAME = "NAME";
    public static int CHECK_NAMES_NAME_COLUMN = 1;


    public static String CREATE_NAMES_TABLE =
            "CREATE TABLE " + CHECK_NAMES_TABLE + " (" +
                    CHECK_NAMES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CHECK_NAMES_NAME + " TEXT);";

    public static String CHECK_LIST_TABLE = "CheckNames";

    public static String CHECK_LIST_ID = "ID";
    public static int CHECK_LIST_ID_COLUMN = 0;

    public static String CHECK_LIST_NAME = "NAME";
    public static int CHECK_LIST_NAME_COLUMN = 1;

    public static String CHECK_LIST_ITEM = "ITEM";
    public static int CHECK_LIST_ITEM_COLUMN = 2;

    public static String CREATE_LIST_TABLE =
            "CREATE TABLE "+ CHECK_LIST_TABLE + " (" +
                    CHECK_LIST_ID + " INTEGER AUTO_INCREMENT," +
                    CHECK_LIST_NAME + " TEXT," +
                    CHECK_LIST_ITEM + " TEXT," +
                    "PRIMARY KEY(" + CHECK_LIST_ID + ")," +
                    "FOREIGN KEY("+ CHECK_LIST_NAME +") REFERENCES " + CHECK_NAMES_TABLE + "(" + CHECK_NAMES_NAME + "));";

    public static String DROP_NAMES_TABLE =
            "DROP TABLE IF EXISTS" + CHECK_NAMES_TABLE + ";";

    public static String DROP_LIST_TABLE =
            "DROP TABLE IF EXISTS" + CHECK_LIST_TABLE + ";";

    private static class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
            super(context, name, factory, version, errorHandler);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_LIST_TABLE);
            sqLiteDatabase.execSQL(CREATE_NAMES_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_LIST_TABLE);
            sqLiteDatabase.execSQL(DROP_NAMES_TABLE);
            onCreate(sqLiteDatabase);
        }
    }



}
