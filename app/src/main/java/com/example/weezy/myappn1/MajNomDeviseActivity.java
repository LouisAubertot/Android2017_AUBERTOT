package com.example.weezy.myappn1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.EditText;


public class MajNomDeviseActivity extends AppCompatActivity {

    public static final int OK = 1;
    private static final int CANCEL = 2;

    private String nomDevise;
    private EditText etNom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maj_nom_devise);


        this.nomDevise = this.getIntent().getExtras().getString("nom");
    }

    public void ok(View v) {


        String nomDevise = this.etNom.getText().toString().trim();
        if (nomDevise.length() == 0) {
            Toast.makeText(this, "le nom est vide !", Toast.LENGTH_LONG).show();
        } else {
            Intent donnees = new Intent();
            donnees.putExtra("nom", this.etNom.getText().toString().trim());
            this.setResult(OK, donnees);

            this.finish();
        }
    }



    public void modifClick(View v)
    {
        this.ok(null);
    }


    public void retourClick(View view) {
        this.annuler(null);
    }


    public void annuler(View v) {
        this.setResult(CANCEL);
        this.finish();
    }

    @Override
    public void onBackPressed() {
        this.annuler(null);

    }

    public void onStart() {
        super.onStart();

        this.etNom = this.findViewById(R.id.etNom);
    }


}


