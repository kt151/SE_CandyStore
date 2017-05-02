package com.example.ari.candystore;

/**
 * Created by Ari on 02/27/17.
 */

public class Candy {


    private int id;
    private String name;
    private double price;

    public Candy(int id, String name, double price) {
        setId(id);
        setName(name);
        setPrice(price);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price>=0.0)
        this.price = price;
    }

}
