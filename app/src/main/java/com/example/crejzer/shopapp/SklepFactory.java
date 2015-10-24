package com.example.crejzer.shopapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Slasher on 2015-10-24.
 */
public class SklepFactory  extends SQLiteOpenHelper{

    public Context context;
    public SklepFactory(Context context)
    {
        super(context, "sklepy.db", null, 1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(
                "create table sklepy(" +
                        "id integer primary key autoincrement," +
                        "nazwa text," +
                        "poz_x double," +
                        "poz_y double)"
        );
    }
    public Sklep getSklep(int id)
    {
        Sklep sklep = new Sklep();
        SQLiteDatabase db = getReadableDatabase();
        String[] kolumny = {"id","nazwa","poz_x","poz_y"};
        String args[] ={id+""};
        Cursor kursor = db.query("sklepy",kolumny," id=?", args, null, null,null,null);
        if(kursor!=null)
        {
            kursor.moveToFirst();
            sklep.setId(kursor.getInt(0));
            sklep.setNazwa(kursor.getString(1));
            sklep.setPozX(kursor.getDouble(2));
            sklep.setPozY(kursor.getDouble(3));


            ProduktFactory produkt_factory = new ProduktFactory(this.context);
            sklep.setProdukty(produkt_factory.getProduktyBySklepId(sklep.getId()));

        }

        return sklep;
    }
    public Sklep[] getSklepy()
    {
        SQLiteDatabase db = getReadableDatabase();

        String[] kolumny = {"id","nazwa","poz_x","poz_y"};
        Cursor kursor = db.query("sklepy", kolumny, null, null, null, null,null);
        final int ile_sklepow = kursor.getCount();
        Sklep sklepy[] = new Sklep[ile_sklepow];
        int  i=0;
        while(kursor.moveToNext())
        {
            Sklep sklep = new Sklep();
            sklep.setId(kursor.getInt(0));
            sklep.setNazwa(kursor.getString(1));
            sklep.setPozX(kursor.getDouble(2));
            sklep.setPozY(kursor.getDouble(3));
            sklepy[i] = sklep;
            i++;
        }
        return sklepy;

    }
    public void dodajSklep(String nazwa, double poz_x, double poz_y)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put("nazwa", nazwa);
        wartosci.put("poz_x", poz_x);
        wartosci.put("poz_y", poz_y);
        db.insertOrThrow("sklepy", null, wartosci);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
