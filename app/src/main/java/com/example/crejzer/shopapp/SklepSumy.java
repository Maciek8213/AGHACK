package com.example.crejzer.shopapp;



/**
 * Created by Slasher on 2015-10-24.
 */
public class SklepSumy extends Sklep
{
    protected double suma;

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public void setProduktyZListy(String[] nazwa)
    {
        Produkt[] produkty =this.getProdukty();
        int ile_produktow = produkty.length;

        int pr = nazwa.length;

        Produkt[] prods = new Produkt[ile_produktow];
        int k=0;

        for(int i=0; i<ile_produktow; i++)
        {
            for(int j=0; j<pr; j++)
            {
                if(produkty[i].getNazwa() == nazwa[j])
                {
                    prods[k] = produkty[i];
                    k++;
                }
            }
        }
        this.produkty = prods;
    }
}
