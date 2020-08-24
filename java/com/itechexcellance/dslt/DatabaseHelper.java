package com.itechexcellance.dslt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;


public class DatabaseHelper extends SQLiteOpenHelper {
    static final int DB_VERSION = 34;
    static final String dbName="demoDB";
    static final String sugarTable="Tracker";
    static final String colID="id";
    static final String colDate="date";
    static final String colFasting="fasting";
    static final String colPostB="postb";
    static final String colPostL="postl";






    public DatabaseHelper(Context context) {
        super(context, dbName, null,DB_VERSION);

        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

       /* db.execSQL("CREATE TABLE "+sugarTable+" ("+colID+ " INTEGER PRIMARY KEY , "+
                colDate+ " TEXT)");*/

        db.execSQL("CREATE TABLE "+sugarTable+" ("+colID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                colDate+" TEXT, "+colFasting+" TEXT, "+colPostB+" TEXT, "+colPostL+" TEXT);");

        //Inserts pre-defined departments
        //InsertDepts(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+sugarTable);
        onCreate(db);
    }
/*
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        db.execSQL("DROP TABLE IF EXISTS "+sugarTable);


        db.execSQL("DROP TRIGGER IF EXISTS dept_id_trigger");
        db.execSQL("DROP TRIGGER IF EXISTS dept_id_trigger22");
        db.execSQL("DROP TRIGGER IF EXISTS fk_empdept_deptid");
        db.execSQL("DROP VIEW IF EXISTS "+viewEmps);
        onCreate(db);
    }*/

   /* void AddRecord(Tracker dslt)
    {


        SQLiteDatabase db= this.getWritableDatabase();


        ContentValues cv=new ContentValues();

        cv.put(colDate, dslt.getDate());
        cv.put(colFasting, dslt.getFasting());
        cv.put(colPostB, dslt.getPostB());
        cv.put(colPostL, dslt.getPostL());
        //cv.put(colDept,2);

        db.insert(sugarTable,null,cv);
        db.close();


    }

    /*int getTrackerCount()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur= db.rawQuery("Select * from "+sugarTable, null);
        int x= cur.getCount();
        cur.close();
        return x;
    }*/


    // Adding new User Details
    void insertUserDetails(String date, String fasting, String postb, String postl){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(colDate, date);
        cValues.put(colFasting, fasting);
        cValues.put(colPostB, postb);
        cValues.put(colPostL, postl);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(sugarTable,null, cValues);
        db.close();
    }
    // Get User Details
    public ArrayList<HashMap<String, String>> GetRecord(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> recordList = new ArrayList<>();
        String query = "SELECT date, fasting, postb, postl FROM "+ sugarTable;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> record = new HashMap<>();
            record.put("date",cursor.getString(cursor.getColumnIndex(colDate)));
            record.put("fasting",cursor.getString(cursor.getColumnIndex(colFasting)));
            record.put("postb",cursor.getString(cursor.getColumnIndex(colPostB)));
            record.put("postl",cursor.getString(cursor.getColumnIndex(colPostL)));
            recordList.add(record);
        }
        return  recordList;
    }
    // Get User Details based on userid
    public ArrayList<HashMap<String, String>> GetUserByUserId(int recordid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> recordList = new ArrayList<>();
        String query = "SELECT date, fasting, postb, postl FROM "+ sugarTable;
        Cursor cursor = db.query(sugarTable, new String[]{colDate, colFasting, colPostB,colPostL}, colID+ "=?",new String[]{String.valueOf(recordid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> record = new HashMap<>();
            record.put("date",cursor.getString(cursor.getColumnIndex(colDate)));
            record.put("fasting",cursor.getString(cursor.getColumnIndex(colFasting)));
            record.put("postb",cursor.getString(cursor.getColumnIndex(colPostB)));
            record.put("postl",cursor.getString(cursor.getColumnIndex(colPostL)));
            recordList.add(record);
        }
        return  recordList;
    }




  /*  public int UpdateEmp(Tracker dslt)
        {
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues cv=new ContentValues();
            cv.put(colDate, dslt.getDate());
            cv.put(colFasting, dslt.getFasting());
            cv.put(colPostB, dslt.getPostB());
            cv.put(colPostL, dslt.getPostL());
            return db.update(sugarTable, cv, colID+"=?", new String []{String.valueOf(dslt.getID())});

    }*/

    /*public void DeleteEmp(Tracker dslt)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(sugarTable,colID+"=?", new String [] {String.valueOf(dslt.getID())});
        db.close();

    }*/

    // Update User Details
    public int UpdateUserDetails(String date, String fasting,String postb,String postl, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(colDate, date);
        cVals.put(colFasting, fasting);
        cVals.put(colPostB, postb);
        cVals.put(colPostL, postl);
        int count = db.update(sugarTable, cVals, colID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }

    // Delete User Details
    public void DeleteRecord(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(sugarTable, colID+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }

}
