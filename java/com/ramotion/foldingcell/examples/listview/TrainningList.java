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

public class TrainningList extends AppCompatActivity {

    private ListView m_oListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainning_list);

        final Button go_Write=(Button)findViewById(R.id.write);
        go_Write.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        Intent intent = new Intent (TrainningList.this, Traning.class);
                        startActivity(intent);
                    }
                }
        );

        ArrayList<TrainningItem> oData = new ArrayList<>();
        TrainningItem oItem = new TrainningItem();
        oItem.strTitle = "푸쉬업";
        oItem.strDate = " 5*10 ";
        oData.add(oItem);

        TrainningItem oItem2 = new TrainningItem();
        oItem2.strTitle = "크런치";
        oItem2.strDate = " 5*10 ";
        oData.add(oItem2);

        TrainningItem oItem3 = new TrainningItem();
        oItem3.strTitle = "싯업";
        oItem3.strDate = " 4*10 ";
        oData.add(oItem3);

        TrainningItem oItem4 = new TrainningItem();
        oItem4.strTitle = "플랭크";
        oItem4.strDate = " 5*10 ";
        oData.add(oItem4);

        TrainningItem oItem5 = new TrainningItem();
        oItem5.strTitle = "사이드 레터럴 레이즈";
        oItem5.strDate = " 7*10 ";
        oData.add(oItem5);

        TrainningItem oItem6 = new TrainningItem();
        oItem6.strTitle = "벤치프레스";
        oItem6.strDate = " 5*5 ";
        oData.add(oItem6);

        TrainningItem oItem7 = new TrainningItem();
        oItem7.strTitle = "데드리프트";
        oItem7.strDate = " 4*5 ";
        oData.add(oItem7);

        TrainningItem oItem8 = new TrainningItem();
        oItem8.strTitle = "랫풀다운";
        oItem8.strDate = " 7*10 ";
        oData.add(oItem8);

        TrainningItem oItem9 = new TrainningItem();
        oItem9.strTitle = "풀업";
        oItem9.strDate = "5 * 5";
        oData.add(oItem9);

        m_oListView = (ListView)findViewById(R.id.trainningList);
        final TrainningListAdapter oAdapter = new TrainningListAdapter(oData);
        m_oListView.setAdapter(oAdapter);
    }
}
