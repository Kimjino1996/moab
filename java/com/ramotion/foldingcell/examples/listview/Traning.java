package com.ramotion.foldingcell.examples.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ramotion.foldingcell.examples.R;

public class Traning extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traning);
        final Button go_List=(Button)findViewById(R.id.entire);
        go_List.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Intent intent = new Intent (Traning.this, TrainningList.class);
                        startActivity(intent);
                    }
                }
        );
        final Button go_List2=(Button)findViewById(R.id.submit);
        go_List2.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        EditText t1=(EditText)findViewById(R.id.first);
                        EditText t2=(EditText)findViewById(R.id.second);
                        t1.setText("     ");
                        t2.setText("    ");
                    }
                }
        );
    }
}
