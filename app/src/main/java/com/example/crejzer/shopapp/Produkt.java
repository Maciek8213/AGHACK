package com.example.crejzer.shopapp;

/**
 * Created by Slasher on 2015-10-24.
 */
public class Produkt
{
    private int id;
    private int id_sklepu;
    private String nazwa;
    private double cena;
    private int ilosc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSklepu() {
        return id_sklepu;
    }

    public void setIdSklepu(int id_sklepu) {
        this.id_sklepu = id_sklepu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }
}
