package com.ramotion.foldingcell.examples.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.ramotion.foldingcell.examples.R;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginClick(View view) {
        //Intent intent = getIntent();
        EditText id = (EditText)findViewById(R.id.id);
        EditText pw = (EditText)findViewById(R.id.pw);
        int check = loadDB(id.getText().toString(),pw.getText().toString());
        System.out.println(check);
        if(check == 1) {
            Toast.makeText(getApplicationContext(),"성공!",Toast.LENGTH_LONG).show();
            Intent intent2 = new Intent (MainActivity.this, Main2Activity.class);
            startActivity(intent2);
        }else {
            Toast.makeText(getApplicationContext(),"실패!",Toast.LENGTH_LONG).show();
        }

    }

    public void singUpClick(View view) {
        Intent intent = new Intent(this, signUp.class);
        startActivity(intent);
    }

    public int loadDB(String equalID, String equalPW){
        SQLiteDatabase db = openOrCreateDatabase(
                "account.db",
                SQLiteDatabase.CREATE_IF_NECESSARY,
                null );
        db.execSQL("CREATE TABLE IF NOT EXISTS account "
                + "(account_id TEXT PRIMARY KEY, account_pw TEXT);");

        Cursor c = db.rawQuery("SELECT * FROM account;", null);
        startManagingCursor(c);
        c.moveToFirst();
        while(true){
            if(equalID.equals(c.getString(0))){
                if(equalPW.equals(c.getString(1))){
                    return 1;
                }
            }
            if(c.moveToNext()==false)
                break;
        }
        if(db != null){
            db.close();
            return 0;
        }
        return 0;
    }


}