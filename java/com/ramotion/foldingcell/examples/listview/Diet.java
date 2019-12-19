package com.ramotion.foldingcell.examples.listview;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.TextView;

        import com.ramotion.foldingcell.examples.R;

        import java.text.SimpleDateFormat;
        import java.util.Date;

public class Diet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        final Button go_List=(Button)findViewById(R.id.entire);
        go_List.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Intent intent = new Intent (Diet.this, DietList.class);
                        startActivity(intent);
                    }
                }
        );
    }

    public void onClickButton(View view) {
        TextView text = (TextView)findViewById(R.id.kcal);
        int total = Integer.parseInt((text.getText()).toString());
        EditText edit = (EditText)findViewById(R.id.foodKcal);
        int cur = Integer.parseInt((edit.getText()).toString());
        edit.setText("");
        edit = (EditText)findViewById(R.id.foodName);
        String food = (edit.getText()).toString();
        edit.setText("");
        total += cur;
        text.setText(Integer.toString(total));

        switch(view.getId()) {
            case R.id.breakfast:
                break;
            case R.id.lunch:
                break;
            case R.id.dinner:
                break;
        }
    }

}
