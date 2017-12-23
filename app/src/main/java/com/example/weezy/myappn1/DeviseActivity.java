package com.example.weezy.myappn1;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class DeviseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{


    private TextView etMontant;
    private EditText Edit;
    private Portefeuille P;
    private Devise d;
    public static final int OK = 1;
    private static final int CANCEL = 2;


    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState((outState));

        outState.putSerializable("devise",  this.d);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.devise_activity);


        if(savedInstanceState != null)
        {
            this.d = (Devise)savedInstanceState.getSerializable("devise");

        }
        else {

            this.d = (Devise) this.getIntent().getSerializableExtra("devise");

        }


    }


    public void alert (){

        AlertDialog.Builder builderAlert = new AlertDialog.Builder(this);
        builderAlert.setMessage("la devise a un montant égal a 0")
                .setTitle("Voulez vous sortir cette devise du portefeuille ? ")
                .setPositiveButton("Oui", (DialogInterface.OnClickListener) this)
                .setNegativeButton("Non", (DialogInterface.OnClickListener) this);
        builderAlert.show();
    }

    public void onClick(DialogInterface dialog, int choix)
    {
        if (choix == DialogInterface.BUTTON_POSITIVE)
        {
            P.deleteDevise(d);
        }
        else if(choix == DialogInterface.BUTTON_NEGATIVE)
        {
            this.annuler(null);
        }
    }





    @Override
    protected void onStart() {
        super.onStart();

        this.etMontant = (TextView)this.findViewById(R.id.textView);
        this.etMontant.setText(d.getMontant()+" " + d.getNom());
        this.Edit = (EditText)this.findViewById(R.id.editText4);

    }


    private void cacheClavier()
    {
        InputMethodManager mgr = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(this.etMontant.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

        this.etMontant.setText("");
    }

    public void AjouterClick(View view)
    {
        float montant = Float.parseFloat(this.Edit.getText().toString());

        try {
            d.ajout(montant);

        }catch(IllegalArgumentException e){

            System.out.println(e.getMessage());
    }

        this.etMontant.setText(d.getMontant()+" " + d.getNom());
    }


    public void retirerClick(View view)
    {
        float montant = Float.parseFloat(this.Edit.getText().toString());

        try {
        d.retrait(montant);

        }catch(IllegalArgumentException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        catch(DeviseDevientNulleException r)
        {

        }

        this.etMontant.setText(d.getMontant()+" " + d.getNom());
    }


    public void annuler(View v)
    {
        this.setResult(CANCEL);
        this.finish();
    }

    @Override
    public void onBackPressed()
    {
        this.annuler(null);

    }

    public void retourClick (View view)
    {
        Intent retour = new Intent();
        retour.putExtra("devise", d);

        setResult(OK,retour);


        this.finish();
    }












    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }

    public void clickBack(View view) {
        Intent retour = new Intent();
        retour.putExtra("devise", d);

        setResult(OK,retour);


        this.finish();

    }
}