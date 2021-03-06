package com.example.ari.candystore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_insert);
    }

    public void insert(View v){
        EditText nameEditText = (EditText) findViewById(R.id.input_name);
        EditText priceEditText = (EditText) findViewById(R.id.input_price);
        String name = nameEditText.getText().toString();
        String priceString = priceEditText.getText().toString();

        try{
            double price = Double.parseDouble(priceString);
            Candy candy = new Candy(0,name,price);
            dbManager.insert(candy);
            Toast.makeText(this, "Candy Added",Toast.LENGTH_SHORT).show();
        }catch(NumberFormatException nfe){
            Toast.makeText(this, "Price Error", Toast.LENGTH_SHORT).show();
        }
        nameEditText.setText("");
        priceEditText.setText("");
    }

    public void goBack(View v){
        this.finish();
    }
}
