package com.example.crejzer.shopapp;

/**
 * Created by lokalne on 2015-10-25.
 */
public class Place {
    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double x;

    public Place(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getY() {
        return y;
    }

    public Double y;
}
