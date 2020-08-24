package com.itechexcellance.dslt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.TextView;

public class DailySugarMain extends TabActivity {
    DatabaseHelper dbHelper;
    GridView grid;
    TextView txtTest;
    /** Called when the activity is first created. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_sugar_main);
        SetupTabs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add(1, 1, 1, "Add Record");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            //Add employee
            case 1:
                Intent addIntent=new Intent(this,DailySugarMain.class);
                startActivity(addIntent);
                break;
        }
        super.onOptionsItemSelected(item);
        return false;
    }

    void SetupTabs()
    {

        TabHost host=getTabHost();

        TabHost.TabSpec spec=host.newTabSpec("tag1");
        Intent in1=new Intent(this, MainActivity.class);
        spec.setIndicator("Add Record");
        spec.setContent(in1);



        TabHost.TabSpec spec2=host.newTabSpec("tag2");
        Intent in2=new Intent(this, RecordList.class);

        spec2.setIndicator("Sugar Level");
        spec2.setContent(in2);

        host.addTab(spec);
        host.addTab(spec2);


    }



}