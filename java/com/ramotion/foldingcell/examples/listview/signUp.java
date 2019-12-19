package com.ramotion.foldingcell.examples.listview;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;

import com.ramotion.foldingcell.examples.R;

public class signUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void signBtnClick(View view) {
        EditText txt = null;
        txt = (EditText)findViewById(R.id.insertId);
        String id = txt.getText().toString();
        txt = (EditText)findViewById(R.id.insertPw);
        String pw = txt.getText().toString();

        String sql = "INSERT INTO account (account_id, account_pw)" +
                "VALUES ( '" + id + "','" + pw +"');";
        SQLiteDatabase db = openOrCreateDatabase(
                "account.db",
                SQLiteDatabase.CREATE_IF_NECESSARY,
                null);
        db.execSQL("CREATE TABLE IF NOT EXISTS account"
                +"(account_id TEXT PRIMARY KEY, account_pw TEXT);");
        db.execSQL(sql);

        finish();

    }
}