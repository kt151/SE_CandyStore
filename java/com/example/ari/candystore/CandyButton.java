package com.example.ari.candystore;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Ari on 02/27/17.
 */

public class CandyButton extends Button{
    private Candy candy;

    public CandyButton(Context context, Candy newCandy){
        super(context);
        candy = newCandy;
    }

    public double getPrice(){
        return candy.getPrice();
    }
}
