package com.example.crejzer.shopapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Slasher on 2015-10-24.
 */
public class ProduktFactory extends SQLiteOpenHelper
{
    public ProduktFactory(Context context)
    {
        super(context, "produkty.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(
                "create table produkty(" +
                        "id integer primary key autoincrement," +
                        "id_sklepu integer," +
                        "nazwa text," +
                        "cena double," +
                        "ilosc integer)"
        );
    }

    public Produkt getProdukt(int id)
    {
        Produkt produkt = new Produkt();
        SQLiteDatabase db = getReadableDatabase();
        String[] kolumny = {"id","id_sklepu","nazwa","cena","ilosc"};
        String args[] ={id+""};
        Cursor kursor = db.query("produkty",kolumny," id=?", args, null, null,null,null);
        if(kursor!=null)
        {
            kursor.moveToFirst();
            produkt.setId(kursor.getInt(0));
            produkt.setIdSklepu(kursor.getInt(1));
            produkt.setNazwa(kursor.getString(2));
            produkt.setCena(kursor.getInt(3));
            produkt.setIlosc(kursor.getInt(4));

        }
        return produkt;

    }
    public Produkt[] getProduktyBySklepId(int id_sklepu)
    {
        String[] kolumny ={"id", "id_sklepu","nazwa","cena","ilosc"};
        SQLiteDatabase db = getReadableDatabase();
        String args[] ={id_sklepu+""};
        Cursor kursor = db.query("produkty", kolumny, " id_sklepu=?", args, null, null,null);
        final int ile_produktow = kursor.getCount();
        Produkt produkty[] = new Produkt[ile_produktow];
        int  i=0;
        while(kursor.moveToNext())
        {
            Produkt produkt = new Produkt();
            produkt.setId(kursor.getInt(0));
            produkt.setIdSklepu(kursor.getInt(1));
            produkt.setNazwa(kursor.getString(2));
            produkt.setCena(kursor.getInt(3));
            produkt.setIlosc(kursor.getInt(4));
            produkty[i] = produkt;
            i++;



        }
        return produkty;

    }
    public void dodajProdukt(int id_sklepu, String nazwa, int cena, int ilosc)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put("id_sklepu", id_sklepu);
        wartosci.put("nazwa",nazwa);
        wartosci.put("cena", cena);
        wartosci.put("ilosc", ilosc);
        db.insertOrThrow("produkty", null, wartosci);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void kasujProdukty(int id_sklepu)
    {

        SQLiteDatabase db = getWritableDatabase();
        db.delete("produkty","id_sklepu=?", new String[]{id_sklepu+""});

    }
}
