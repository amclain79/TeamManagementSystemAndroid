package edu.uta.cse5324.team1.teammanagementsystemandroid;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class Informer {
    public static void inform(String msg, View view){
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);

        TextView snackbarTV = snackbar.getView().findViewById(R.id.snackbar_text);
        snackbarTV.setTextSize( 24 );
        snackbarTV.setTypeface(snackbarTV.getTypeface(), Typeface.BOLD);

        snackbar.show();
    }
}
