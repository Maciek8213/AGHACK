//package com.example.crejzer.shopapp;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
///**
// * Created by Slasher on 2015-10-24.
// */
//public class ZarzadcaBazy extends SQLiteOpenHelper
//{
//    public ZarzadcaBazy(Context context)
//    {
//        super(context, "dane.db", null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db)
//    {
//        db.execSQL(
//                "create table produkty(" +
//                        "id integer primary key autoincrement," +
//                        "nazwa text," +
//                        "opis text)"
//        );
//    }
//
//    public void dodajProdukt(String nazwa, String opis)
//    {
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues wartosci = new ContentValues();
//        wartosci.put("nazwa", nazwa);
//        wartosci.put("opis",opis);
//        db.insertOrThrow("produkty", null, wartosci);
//
//    }
//    public Cursor dajWszystkie()
//    {
//        String[] kolumny ={"id", "nazwa","opis"};
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor kursor = db.query("produkty", kolumny, null, null, null, null,null);
//        return kursor;
//    }
//
//    public void kasujProdukt(int id)
//    {
//
//        SQLiteDatabase db = getWritableDatabase();
//        String[] argumenty ={""+id};
//        db.delete("produkty","id=?", argumenty);
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
//    {
//
//    }
//}
