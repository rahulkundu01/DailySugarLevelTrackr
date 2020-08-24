package com.itechexcellance.dslt;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class Alerts {
    public static void ShowEmpAddedAlert(Context con)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(con);
        builder.setTitle("Add new Employee");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        DialogListner listner=new DialogListner();
        builder.setMessage("Employee Added successfully");
        builder.setPositiveButton("ok", listner);

        AlertDialog diag=builder.create();
        diag.show();
    }

    public static AlertDialog ShowEditDialog(final Context con,final Tracker emp)
    {
        AlertDialog.Builder b=new AlertDialog.Builder(con);
        b.setTitle("Employee Details");
        LayoutInflater li=LayoutInflater.from(con);
     //
        b.setIcon(android.R.drawable.ic_input_get);

        b.setPositiveButton("Modify", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub


                try
                {


                }
                catch(Exception ex)
                {
                    CatchError(con, ex.toString());
                }
            }
        });

        b.setNeutralButton("Delete", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                DatabaseHelper db=new DatabaseHelper(con);

            }
        });
        b.setNegativeButton("Cancel", null);

        return b.create();
        //diag.show();

    }

    static public void CatchError(Context con, String Exception)
    {
        Dialog diag=new Dialog(con);
        diag.setTitle("Error");
        TextView txt=new TextView(con);
        txt.setText(Exception);
        diag.setContentView(txt);
        diag.show();
    }


}



