package com.example.ari.candystore;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseManager dbManager;
    private double total;
    private ScrollView scrollView;
    private int buttonwidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbManager = new DatabaseManager(this);
        total = 0.0;
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        buttonwidth = size.x/2;
        updateView();
    }

    public void onResume(){
        super.onResume();
        updateView();
    }

    public void updateView() {
        ArrayList<Candy> candies = dbManager.selectAll();

        scrollView.removeAllViewsInLayout();
        if(candies.size()>0)
        {
            GridLayout grid = new GridLayout(this);
            grid.setRowCount((candies.size()+1)/2);
            grid.setColumnCount(2);

            CandyButton[] buttons = new CandyButton[candies.size()];
            ButtonHandler bh = new ButtonHandler();

            int i=0;
            for(Candy candy: candies)
            {
                buttons[i]=new CandyButton(this,candy);
                buttons[i].setText(candy.getName()+ "\n" + candy.getPrice());
                buttons[i].setOnClickListener(bh);
                grid.addView(buttons[i], buttonwidth, GridLayout.LayoutParams.WRAP_CONTENT);
                i++;
            }
            scrollView.addView(grid);
        }
    }

    private class ButtonHandler implements View.OnClickListener{
        public void onClick(View v){
            total += ((CandyButton)v).getPrice();
            String pay = NumberFormat.getCurrencyInstance().format(total);
            Toast.makeText(MainActivity.this, pay, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();
        switch(id){
            case R.id.action_add:
                Intent i = new Intent(this, InsertActivity.class);
                this.startActivity(i);
                overridePendingTransition(R.anim.slide_from_left, 0);
                return true;
            case R.id.action_update:
                Intent u = new Intent(this, UpdateActivity.class);
                this.startActivity(u);
                overridePendingTransition(R.anim.slide_from_left, 0);
                return true;
            case R.id.action_delete:
                Intent d = new Intent(this, DeleteActivity.class);
                this.startActivity(d);
                overridePendingTransition(R.anim.slide_from_left, 0);
                return true;
            case R.id.action_reset:
                total = 0.0;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
