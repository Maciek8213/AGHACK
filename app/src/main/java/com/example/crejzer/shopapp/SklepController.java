package com.example.crejzer.shopapp;

import android.content.Context;

/**
 * Created by Slasher on 2015-10-24.
 */
public class SklepController
{
    private SklepSumy[] sklepy;
    private String[] produkty_nazwa;


    public SklepController(Context context, String[] produkty_nazwa)
    {
        this.produkty_nazwa = produkty_nazwa;
        SklepFactory factory = new SklepFactory(context);
        Sklep[] sklepy = factory.getSklepy();
        int ile_sklepow = sklepy.length;
        this.sklepy = new SklepSumy[ile_sklepow];

        ProduktFactory pr_factory = new ProduktFactory(context);

        for(int i=0; i< ile_sklepow; i++)
        {
            sklepy[i].setProdukty(pr_factory.getProduktyBySklepId(sklepy[i].getId()));
            this.sklepy[i] = new SklepSumy();
            this.sklepy[i].setId(sklepy[i].getId());
            this.sklepy[i].setNazwa(sklepy[i].getNazwa());
            this.sklepy[i].setPozY(sklepy[i].getPozY());
            this.sklepy[i].setPozX(sklepy[i].getPozX());
            this.sklepy[i].setProdukty(pr_factory.getProduktyBySklepId(sklepy[i].getId()));

            this.sklepy[i].setSuma(sklepy[i].getCenyProduktow(this.produkty_nazwa));

            this.sklepy[i].setProduktyZListy(this.produkty_nazwa);
        }
        sortuj();


    }

    private void sortuj()
    {
        int ile = this.sklepy.length;
        for(int i=0; i< ile; i++)
        {
            for(int j=0; j< ile-1 ; j++)
            {
                if(this.sklepy[j].getSuma() > this.sklepy[j+1].getSuma())
                {
                    SklepSumy sklep = this.sklepy[j];
                    this.sklepy[j] = this.sklepy[j+1];
                    this.sklepy[j+1] = sklep;
                }
            }
        }
    }
    public SklepSumy[] getSklepySumy()
    {
        return this.sklepy;
    }

    public double getOdleglosc(double x, double y, double _x, double _y)
    {
        double odleglosc = Math.sqrt(Math.pow((x - _x),2) + Math.pow((y - _y),2));
        return odleglosc;

    }
}
