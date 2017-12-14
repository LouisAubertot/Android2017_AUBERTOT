package com.example.weezy.myappn1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeviseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    private Portefeuille P;
    private TextView etMontant;
    private EditText Edit;


    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState((outState));

        outState.putSerializable("portefeuille",  this.P);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.devise_activity);


        if(savedInstanceState == null)
        {
            P = new Portefeuille();
            P.ajout(new Devise("Euro",500));
        }
        else {
            this.P = (Portefeuille)savedInstanceState.getSerializable("portefeuille");
        }


    }



    @Override
    protected void onStart() {
        super.onStart();

        this.etMontant = (TextView)this.findViewById(R.id.textView);
        this.etMontant.setText(P.getValeurs().get(0).getMontant()+" " + P.getValeurs().get(0).getNom());
        this.Edit = (EditText)this.findViewById(R.id.editText4);



    }

    public void AjouterClick(View view)
    {
        float montant = Float.parseFloat(this.Edit.getText().toString());

        try {
            P.getValeurs().get(0).ajout(montant);

        }catch(IllegalArgumentException e){

            System.out.println(e.getMessage());
    }

        this.etMontant.setText(P.getValeurs().get(0).getMontant()+" " + P.getValeurs().get(0).getNom());
    }


    public void retirerClick(View view)
    {
        float montant = Float.parseFloat(this.Edit.getText().toString());

        try {
        P.getValeurs().get(0).retrait(montant);

        }catch(IllegalArgumentException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        catch(DeviseDevientNulleException r)
        {

        }

        this.etMontant.setText(P.getValeurs().get(0).getMontant()+" " + P.getValeurs().get(0).getNom());
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }
}
