package com.example.sqldemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBDemo extends SQLiteOpenHelper {
    public DBDemo(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE EMP (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, SALARY INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS EMP");
        onCreate(db);
    }
    public Cursor fetchData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM EMP", null);
    }
    public int deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("EMP","id = ?", new String[]{id});
    }
    public int updateData(String id,String nm, String sn, Integer salary ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ID", id);
        cv.put("NAME", nm);
        cv.put("SURNAME", sn);
        cv.put("SALARY", salary);
        return db.update("EMP", cv," id = ?", new String[] {id});
    }
    public boolean addData(String nm, String sn, Integer salary){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", nm);
        cv.put("SURNAME", sn);
        cv.put("SALARY", salary);
        long result = db.insert("EMP", null, cv);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
