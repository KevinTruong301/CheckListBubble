package edu.fullerton.kevin.checklistbubble;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Kevin on 1/29/2018.
 */

public class CheckListDB {
    public static String DB_NAME = "check.db";
    public static int DB_VERSION = 1;

    public static String CHECK_NAMES_TABLE = "CheckNames";

    public static String CHECK_NAMES_ID = "Id";
    public static int CHECK_NAMES_ID_COLUMN = 0;

    public static String CHECK_NAMES_NAME = "Name";
    public static int CHECK_NAMES_NAME_COLUMN = 1;


    public static String CREATE_NAMES_TABLE =
            "CREATE TABLE " + CHECK_NAMES_TABLE + " (" +
                    CHECK_NAMES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CHECK_NAMES_NAME + " TEXT);";

    public static String CHECK_LIST_TABLE = "CheckList";

    public static String CHECK_LIST_ID = "Id";
    public static int CHECK_LIST_ID_COLUMN = 0;

    public static String CHECK_LIST_NAME_ID = "NameId";
    public static int CHECK_LIST_NAME_COLUMN = 1;

    public static String CHECK_LIST_ITEM = "Item";
    public static int CHECK_LIST_ITEM_COLUMN = 2;

    public static String CREATE_LIST_TABLE =
            "CREATE TABLE "+ CHECK_LIST_TABLE + " (" +
                    CHECK_LIST_ID + " INTEGER AUTO_INCREMENT," +
                    CHECK_LIST_NAME_ID + " INTEGER," +
                    CHECK_LIST_ITEM + " TEXT," +
                    "PRIMARY KEY(" + CHECK_LIST_ID + ")," +
                    "FOREIGN KEY("+ CHECK_LIST_NAME_ID +") REFERENCES " + CHECK_NAMES_TABLE + "(" + CHECK_NAMES_NAME + "));";

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

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public CheckListDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    public void openWriteableDb(){
        db = dbHelper.getWritableDatabase();
    }

    public void openReadableDb(){
        db = dbHelper.getReadableDatabase();
    }

    public void closeDb(){
        if(db != null){
            db.close();
        }
    }

    public CheckList getInfoFromCursorNames(Cursor cursor){
        try{
            CheckList list = new CheckList(
                    cursor.getInt(CHECK_NAMES_ID_COLUMN),
                    cursor.getString(CHECK_NAMES_NAME_COLUMN)

            );
            return list;

        }catch(Exception e){
            return null;
        }
    }

    public ArrayList<CheckList> getNames(){
        openReadableDb();

        Cursor cursor = db.query(CHECK_NAMES_TABLE, null, null, null, null, null, null);
        ArrayList<CheckList> list = new ArrayList<CheckList>();
        while (cursor.moveToNext()){
            list.add(getInfoFromCursorNames(cursor));
        }
        closeDb();
        return list;
    }

    /*
    * SELECT CheckNames.NAME, CheckList.ITEM
    * FROM CheckNames, CheckList
    * WHERE CheckNames.ID = CheckList.ID AND CheckNames.ID = ?;
    * */

    public ArrayList<CheckList> getList(int id){
        String select = "CheckNames.NAME, CheckList.ITEM";
        String table = "CheckNames, CheckList";
        String where = "CheckNames.ID = CheckList.NameId AND CheckNames.ID = " + id;
        openReadableDb();
        ArrayList<CheckList> lists = new ArrayList<CheckList>();

        Cursor cursor = db.query(table,null, select, null, null, where, null, null);

        while(cursor.moveToNext()){
            lists.add(getInfoFromCursorNames(cursor));
        }

        closeDb();
        return lists;
    }

    public void insertName(String name){
        ContentValues cv = new ContentValues();
        cv.put(CHECK_NAMES_NAME, name);
        this.openWriteableDb();
        db.insert(CHECK_NAMES_TABLE, null, cv);
        this.closeDb();
    }

    public void insertList(String item){
        ContentValues cv = new ContentValues();
        cv.put(CHECK_LIST_ITEM, item);
        openWriteableDb();
        db.insert(CHECK_LIST_TABLE,null, cv);
        closeDb();
    }

    public void deleteName(int id){
        String where = "id = " + id;
        openWriteableDb();
        db.delete(CHECK_NAMES_TABLE, where, null);
        closeDb();
    }

    public void deleteListItem(int id){
        String where = "id = " + id;
        openWriteableDb();
        db.delete(CHECK_LIST_TABLE, where, null);
        closeDb();
    }

}
