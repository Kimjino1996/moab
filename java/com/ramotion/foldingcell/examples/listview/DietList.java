package com.ramotion.foldingcell.examples.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ramotion.foldingcell.examples.R;

import java.util.ArrayList;

public class DietList extends AppCompatActivity {

    private ListView m_oListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_list);

        final Button go_Write=(Button)findViewById(R.id.write);
        go_Write.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Intent intent = new Intent (DietList.this, Diet.class);
                        startActivity(intent);
                    }
                }
        );

        ArrayList<DietItem> oData = new ArrayList<>();
        DietItem oItem = new DietItem();
        oItem.strTitle = "샐러드 / 117Kcal";
        oItem.strDate = "2019-12-17 아침";
        oData.add(oItem);

        DietItem oItem2 = new DietItem();
        oItem2.strTitle = "식빵 + 딸기잼 / 173Kcal";
        oItem2.strDate = "2019-12-17 아침";
        oData.add(oItem2);

        DietItem oItem3 = new DietItem();
        oItem3.strTitle = "볶음밥 / 582Kcal";
        oItem3.strDate = "2019-12-17 점심";
        oData.add(oItem3);

        DietItem oItem4 = new DietItem();
        oItem4.strTitle = "집밥 / 422Kcal";
        oItem4.strDate = "2019-12-17 저녁";
        oData.add(oItem4);

        DietItem oItem5 = new DietItem();
        oItem5.strTitle = "햄버거 세트 / 613Kcal";
        oItem5.strDate = "2019-12-18 점심";
        oData.add(oItem5);

        DietItem oItem6 = new DietItem();
        oItem6.strTitle = "백반 / 479Kcal";
        oItem6.strDate = "2019-12-18 저녁";
        oData.add(oItem6);

        DietItem oItem7 = new DietItem();
        oItem7.strTitle = "파스타 / 325Kcal";
        oItem7.strDate = "2019-12-19 아침";
        oData.add(oItem7);

        DietItem oItem8 = new DietItem();
        oItem8.strTitle = "불고기 / 619Kcal";
        oItem8.strDate = "2019-12-19 점심";
        oData.add(oItem8);

        DietItem oItem9 = new DietItem();
        oItem9.strTitle = "라면 / 550Kcal";
        oItem9.strDate = "2019-12-19 저녁";
        oData.add(oItem9);

        m_oListView = (ListView)findViewById(R.id.dietList);
        final DietListAdapter oAdapter = new DietListAdapter(oData);
        m_oListView.setAdapter(oAdapter);
    }
}
