package com.example.weezy.myappn1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeviseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{


    private TextView etMontant;
    private EditText Edit;
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


        if(savedInstanceState == null)
        {
            this.d = (Devise) this.getIntent().getSerializableExtra("devise");

        }
        else {

        }


    }



    @Override
    protected void onStart() {
        super.onStart();

        this.etMontant = (TextView)this.findViewById(R.id.textView);
        this.etMontant.setText(d.getMontant()+" " + d.getNom());
        this.Edit = (EditText)this.findViewById(R.id.editText4);



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