package com.example.sqlitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBDemo extends SQLiteOpenHelper {
    private static final String DB_NAME = "employees.db";
    private static final String TABLE_NM = "employees";
    private static final String COL1 = "ID";
    private static final String COL2 = "NAME";
    private static final String COL3 = "SURNAME";
    private static final String COL4 = "SALARY";
    private static final String COL5 = "EXPERIENCE";
    //public static SQLiteDatabase db;
    public DBDemo(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE_NM +"(" +
                        COL1 +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        COL2 +" TEXT,"+
                        COL3 +" TEXT,"+
                        COL4 +" INTEGER,"+
                        COL5 +" INTEGER);";
        System.out.println(sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+TABLE_NM;
        db.execSQL(sql);
        onCreate(db);
    }
}
