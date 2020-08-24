package com.itechexcellance.dslt;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class RecordList extends AppCompatActivity {
    DatabaseHelper dbHelper;
    static public GridView grid;
    TextView txtTest;
Intent intent;

    /** Called when the activity is first created. */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        
        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<HashMap<String, String>> recordList = db.GetRecord();
        ListView lv = (ListView) findViewById(R.id.record_list);
        ListAdapter adapter = new SimpleAdapter(RecordList.this, recordList, R.layout.list_row,new String[]{"date","fasting","postb","postl"}, new int[]{R.id.date, R.id.fasting, R.id.postb,R.id.postl});
        lv.setAdapter(adapter);
        Button back = (Button)findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(RecordList.this,DailySugarMain.class);
                startActivity(intent);
            }
        });
        
        
        

    }

}
