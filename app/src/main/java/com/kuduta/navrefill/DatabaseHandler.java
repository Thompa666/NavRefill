package com.kuduta.navrefill;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by osx on 7/5/2017 AD.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydbrefill.db";
    static final String TABLE_NAME = "mytablerefill";

    private static final int DATABASE_VERSION = 1;


    static final String COLUMN_1 = "rfdate";
    static final String COLUMN_2 = "rfnumphone";
    static final String COLUMN_3 = "rfmoney";
    static final String COLUMN_4 = "rfstatus";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "
                + TABLE_NAME
                + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_1 + " TEXT, "
                + COLUMN_2 + " TEXT, "
                + COLUMN_3 + " INTEGER,"
                + COLUMN_4 + " TEXT);");
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(TABLE_NAME, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Recreates the database with a new version
        onCreate(db);
    }

    // Adding new contact
    public long addRecord(String idate, String inumphone,int imoney, String istatus) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_1, idate); // Contact Name
        values.put(COLUMN_2, inumphone); // Contact Phone
        values.put(COLUMN_3, imoney); // Contact Phone
        values.put(COLUMN_4, istatus); // Contact Phone

        SQLiteDatabase db = this.getWritableDatabase();
        // Inserting Row
        long row = db.insert(DatabaseHandler.TABLE_NAME, null, values);
        Log.d(TABLE_NAME,"inserted at row " + row + " " + idate+ inumphone + imoney + istatus);
        db.close(); // Closing database connection
        return row;
    }

/*    public int calAmpunt(){2


    }*/
    public String getRecord(long result) {
        String data = null;

        SQLiteDatabase db = this.getWritableDatabase();
        //txtMsg.append("\n");
        // obtain a list of <recId, name, phone> from DB
        String[] columns = { "_id", "name", "age"};
        Cursor c = db.query(TABLE_NAME,
                columns,
                //null,
                "_id=?", new String[] { String.valueOf(result) },
                null, null, null, null);

        Log.d(TABLE_NAME,"recID "+ result + " count " + c.getCount() );

        if (c != null) {
            Log.d(TABLE_NAME,"recID "+ c);
            if (c.moveToFirst()) {
                int idCol= c.getColumnIndex("_id");
                int nameCol= c.getColumnIndex("name");
                int ageCol= c.getColumnIndex("age");
                String strId = Integer.toString(c.getInt(idCol));
                String strName = c.getString(nameCol);
                String strAge = Integer.toString(c.getInt(ageCol));
                data = strId + " "+ strName + " " + strAge + "\n";

            }
        }
        c.close();
        return data;
    }



    public int getRecordCount() {
        String countQuery = "SELECT _id FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(countQuery, null);
        return cur.getCount();

    }


    public int updateContact(long recID, String idate, String inumphone,int imoney, String istatus) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_1, idate); // Contact Name
        values.put(COLUMN_2, inumphone); // Contact Phone
        values.put(COLUMN_3, imoney); // Contact Phone
        values.put(COLUMN_4, istatus); // Contact Phone


        return db.update(TABLE_NAME, values, "_id = ?",
                new String[] { String.valueOf(recID) });
    }



    public void deleteRecord(long recID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "_id = ?",
                new String[] { String.valueOf(recID) });
        db.close();
    }

    public Cursor getAllData(){

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from "+ TABLE_NAME +"  order by _id DESC",null);
        return cursor;
    }



}
