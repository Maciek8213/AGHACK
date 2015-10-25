package com.example.crejzer.shopapp;



import android.util.Log;

/**
 * Created by Slasher on 2015-10-24.
 */
public class Sklep
{
    private int id;
    private String nazwa;
    private double poz_x;
    private double poz_y;
    public Produkt[] produkty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getPozX() {
        return poz_x;
    }

    public void setPozX(double poz_x) {
        this.poz_x = poz_x;
    }

    public double getPozY() {
        return poz_y;
    }

    public void setPozY(double poz_y) {
        this.poz_y = poz_y;
    }

    public Produkt[] getProdukty() {
        return produkty;
    }

    public void setProdukty(Produkt[] produkty) {
        this.produkty = produkty;
    }
    private double getCenaProduktu(String nazwa)
    {
        int ile_produktow = this.produkty.length;

        for(int i=0; i<ile_produktow; i++)
        {
            if(this.produkty[i].getNazwa().equals( nazwa) )
            {
                return this.produkty[i].getCena();
            }
        }

        return 0;
    }
    public double getCenyProduktow(String[] nazwy)
    {
        double suma =0;
        int ile_produktow = nazwy.length;

        for(int i=0; i< ile_produktow; i++)
        {

            suma+=this.getCenaProduktu(nazwy[i]);
            if(this.getCenaProduktu(nazwy[i])==0)
            {
                return 0;
            }

        }

        return suma;

    }
}
