package com.itechexcellance.dslt;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    EditText txtFasting;
    EditText txtPostB;
    EditText txtPostL;
    DatePickerDialog picker;
    Button btnSave;
    TextView tvw;
    String dd;
    EditText eText;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 0);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        eText = (EditText) findViewById(R.id.dslt_calander);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                // date picker dialog
                picker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        txtFasting = (EditText) findViewById(R.id.dslt_fasting);
        txtPostB = (EditText) findViewById(R.id.dslt_postbreakfast);
        txtPostL = (EditText) findViewById(R.id.dslt_postlunch);
        btnSave = (Button) findViewById(R.id.dslt_save1);

    }

    @Override
    public void onStart() {
        try {
            super.onStart();
            dbHelper = new DatabaseHelper(this);

        } catch (Exception ex) {
            CatchError(ex.toString());
        }
    }


    //picker = (DatePicker) findViewById(R.id.dslt_calander);


    public void btnSave_Click(View v) {
        boolean ok = true;
        try {

            //dd.setText(+picker.getDayOfMonth() + "/" + (picker.getMonth() + 1) + "/" + picker.getYear());
            String Date = eText.getText().toString();
            String Fasting = txtFasting.getText().toString();
            String PostB = txtPostB.getText().toString();
            String PostL = txtPostL.getText().toString();
            //Tracker dslt = new Tracker(Date, Fasting, PostB, PostL);
            DatabaseHelper dbHandler = new DatabaseHelper(MainActivity.this);
            dbHandler.insertUserDetails(Date,Fasting,PostB,PostL);
            intent = new Intent(MainActivity.this,RecordList.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Details Added Successfully", Toast.LENGTH_SHORT).show();
           // dbHelper.AddRecord(dslt);

        } catch (Exception ex) {
            ok = false;
            CatchError(ex.toString());
        }

    }


    private void CatchError(String Exception) {
        Dialog diag=new Dialog(this);
        diag.setTitle("Add new Record");
        TextView txt=new TextView(this);
        txt.setText(Exception);
        diag.setContentView(txt);
        diag.show();
    }
   private void NotifyEmpAdded() {
        Dialog diag = new Dialog(this);
        diag.setTitle("Add new Record");
        TextView txt = new TextView(this);
        txt.setText("Record Added Successfully");
        diag.setContentView(txt);
        diag.show();
        try {
            diag.wait(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            CatchError(e.toString());
        }
        diag.notify();
        diag.dismiss();
    }

}



