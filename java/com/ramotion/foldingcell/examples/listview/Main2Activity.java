package com.ramotion.foldingcell.examples.listview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.ramotion.foldingcell.FoldingCell;
import com.ramotion.foldingcell.examples.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Example of using Folding Cell with ListView and ListAdapter
 */
public class Main2Activity extends AppCompatActivity {
    private static final int REQUEST_ACCESS_FINE_LOCATION = 2001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ACCESS_FINE_LOCATION);

        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTheme(android.R.style.Theme_NoTitleBar);

        final ImageButton go_act1=(ImageButton)findViewById(R.id.food);
        go_act1.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Intent intent1 = new Intent (Main2Activity.this, Diet.class);
                        startActivity(intent1);
                    }
                }
        );

        final ImageButton go_act2=(ImageButton)findViewById(R.id.health);
        go_act2.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Intent intent2 = new Intent (Main2Activity.this, Traning.class);
                        startActivity(intent2);
                    }
                }
        );

        final ImageButton go_act3=(ImageButton)findViewById(R.id.weather);
        go_act3.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Intent intent3 =new Intent(Main2Activity.this, Weather.class);
                        startActivity(intent3);
                    }
                }
        );
        final ImageButton go_act4=(ImageButton)findViewById(R.id.pedometer);
        go_act4.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Intent intent4 =new Intent(Main2Activity.this, Pedometer.class);
                        startActivity(intent4);
                    }
                }
        );
        final ImageButton go_act5=(ImageButton)findViewById(R.id.stopwatch);
        go_act5.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Intent intent5 =new Intent(Main2Activity.this, Stopwatch.class);
                        startActivity(intent5);
                    }
                }
        ); // get our list view
        ListView theListView = findViewById(R.id.mainListView);

        // prepare elements to display
        final ArrayList<Item> items = Item.getTestingList();

        // add custom btn handler to first list item
        items.get(0).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "CUSTOM HANDLER FOR FIRST BUTTON", Toast.LENGTH_SHORT).show();
            }
        });

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(this, items);

        // add default btn handler for each request btn on each item if custom handler not found
        adapter.setDefaultRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "DEFAULT HANDLER FOR ALL BUTTONS", Toast.LENGTH_SHORT).show();
            }
        });

        // set elements to adapter
        theListView.setAdapter(adapter);

        // set on click event listener to list view
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                adapter.registerToggle(pos);
            }
        });

    }
}
