package com.example.weezy.myappn1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener, View.OnLongClickListener  {


    private ListView lstDevise;
    private Portefeuille P;
    private static final int GESTION_DEVISE = 0;
    private static final int CREATION_DEVISE = 1;
    private static final int MODIFICATION_NOM_DEVISE = 2;
    private int indexListe;
    private GridLayout grdDevise;
    private TextView txtPortefeuille;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        if (savedInstanceState==null) {
            this.P = SerialisationPortefeuilleDAO.getInstance(this).charge();
        } else {
            this.P = (Portefeuille) savedInstanceState.getSerializable("portefeuille");
            this.indexListe = savedInstanceState.getInt("index");
        }





        this.P = new Portefeuille();
        Devise d1 = new Devise("euro", 3500);
        Devise d2 = new Devise("Livres", 1500);
        Devise d3 = new Devise("Pesos", 2500);
        Devise d4 = new Devise("Dollars", 4000);
        Devise d5 = new Devise("Francs", 5500);
        Devise d6 = new Devise("Yen", 2000);
        P.ajout(d1);
        P.ajout(d2);
        P.ajout(d3);
        P.ajout(d4);
        P.ajout(d5);
        P.ajout(d6);



    }

    public void creerNouvelleDevise(View v) {
        Intent appelActivite = new Intent(this, MajNomDeviseActivity.class);
        startActivityForResult(appelActivite, MODIFICATION_NOM_DEVISE);
    }

    public void onStart() {
        super.onStart();

        if (this.getWindowManager().getDefaultDisplay().getRotation() == Surface.ROTATION_90 || this.getWindowManager().getDefaultDisplay().getRotation() == Surface.ROTATION_270)
        {
            this.lstDevise = this.findViewById(R.id.ma_liste);

            ArrayAdapter<Devise> adaptateur = new ArrayAdapter<Devise>(
                    this,
                    android.R.layout.simple_list_item_1,
                    this.P.getValeurs());

            this.lstDevise.setAdapter(adaptateur);

            this.lstDevise.setOnItemClickListener(this);

            this.lstDevise.setOnItemLongClickListener(this);
        }

        else
        {
            this.grdDevise = this.findViewById(R.id.ma_grille);
            this.txtPortefeuille = this.findViewById(R.id.ma_portefeuille);
            this.construitInterfacePortrait();

        }

    }


    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState((outState));

        outState.putSerializable("devise",  this.P);
    }





    private void construitInterfacePortrait()
    {
            this.grdDevise.removeAllViews();


            this.txtPortefeuille.setText("");
            for (int i=0; i < this.P.getValeurs().size(); i++)
            {
                Devise d = this.P.getValeurs().get(i);
                Button bDevise = new Button(this);
                bDevise.setText(d.getNom());
                bDevise.setId(i);

                bDevise.setOnClickListener(this);
                bDevise.setOnLongClickListener(this);

                bDevise.setTextColor(Color.WHITE);
                bDevise.getBackground().setColorFilter(ContextCompat.getColor(this.getApplicationContext(),R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
                this.grdDevise.addView(bDevise);
                this.txtPortefeuille.append(d.toString());
                this.txtPortefeuille.append("\n");
            }
    }

    /**

     */

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == MajNomDeviseActivity.OK || resultCode == DeviseActivity.OK) {
            if (requestCode == CREATION_DEVISE) {

                String nomDevise = data.getExtras().getString("nom");
                Devise d = new Devise(nomDevise,0);
                P.ajout(d);



            }
            else if (requestCode == MODIFICATION_NOM_DEVISE)
            {
                String nomDevise = data.getExtras().getString("nom");
                Devise d= (Devise)this.lstDevise.getItemAtPosition(this.indexListe);
                d.setNom(nomDevise);
                P.getValeurs().set(this.indexListe,d);


            }
            else if (requestCode == GESTION_DEVISE)
            {
                Devise d = (Devise)data.getExtras().getSerializable("devise");
                P.getValeurs().set(this.indexListe,d);
             }
            construitInterfacePortrait();
        }

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









    @Override
    public void onClick(View view)
    {
        int i = view.getId();
        Devise d = this.P.getValeurs().get(i);
        this.indexListe = view.getId();

        Intent appelActivite = new Intent(this, DeviseActivity.class);
        appelActivite.putExtra("devise", d);
        startActivityForResult(appelActivite, GESTION_DEVISE);


    }

    @Override
    public boolean onLongClick(View view)
    {
        int i = view.getId();
        Devise d = this.P.getValeurs().get(i);
        Intent appelActivite = new Intent(this, MajNomDeviseActivity.class);
        appelActivite.putExtra("nom", d.getNom());
        startActivityForResult(appelActivite, MODIFICATION_NOM_DEVISE);
        return true;
    }
}