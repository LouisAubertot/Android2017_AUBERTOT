package com.example.weezy.myappn1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {


    private ListView lstDevise;
    private Portefeuille P;
    private static final int GESTION_DEVISE = 0;
    private static final int CREATION_DEVISE = 1;
    private static final int MODIFICATION_NOM_DEVISE = 2;
    private int indexListe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.P = new Portefeuille();
        Devise d1 = new Devise("euro", 500);
        Devise d2 = new Devise("SterlingPound", 1500);
        Devise d3 = new Devise("Pesos", 2500);
        Devise d4 = new Devise("Dollars", 4000);
        Devise d5 = new Devise("FrancSuisse", 50000);
        P.ajout(d1);
        P.ajout(d2);
        P.ajout(d3);
        P.ajout(d4);
        P.ajout(d5);


    }

    public void creerNouvelleDevise(View v) {
        Intent appelActivite = new Intent(this, MajNomDeviseActivity.class);
        startActivityForResult(appelActivite, MODIFICATION_NOM_DEVISE);
    }

    public void onStart() {
        super.onStart();

        this.lstDevise = this.findViewById(R.id.ma_liste);

        ArrayAdapter<Devise> adaptateur = new ArrayAdapter<Devise>(
                this,
                android.R.layout.simple_list_item_1,
                this.P.getValeurs());

        this.lstDevise.setAdapter(adaptateur);

        this.lstDevise.setOnItemClickListener(this);

        this.lstDevise.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Devise d = (Devise) this.lstDevise.getItemAtPosition(position);

        this.indexListe = position;

        Intent appelActivite = new Intent(this, DeviseActivity.class);
        appelActivite.putExtra("devise", d);
        startActivityForResult(appelActivite, GESTION_DEVISE);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

        this.indexListe = position;
        Devise d = (Devise) this.lstDevise.getItemAtPosition(this.indexListe);
        Intent appelActivite = new Intent(this, MajNomDeviseActivity.class);
        appelActivite.putExtra("nom", d.getNom());
        startActivityForResult(appelActivite, MODIFICATION_NOM_DEVISE);
        return true;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == MajNomDeviseActivity.OK || resultCode == DeviseActivity.OK) {
            if (requestCode == CREATION_DEVISE) {



            } else if (requestCode == MODIFICATION_NOM_DEVISE)
            {
                P.getValeurs().



            }
            else if (requestCode == GESTION_DEVISE)
            {


            }
        }

    }
}