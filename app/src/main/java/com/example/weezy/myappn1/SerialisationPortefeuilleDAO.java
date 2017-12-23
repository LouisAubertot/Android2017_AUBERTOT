package com.example.weezy.myappn1;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;




/**
 * Created by Weezy on 22/12/2017.
 */

public class SerialisationPortefeuilleDAO {



    private static SerialisationPortefeuilleDAO instance;

    private Context contexte;
    private final String NOM_FICHIER = "portefeuille.ser";

    private SerialisationPortefeuilleDAO(Context contexte) {

        this.contexte=contexte;
    }

    public static SerialisationPortefeuilleDAO getInstance(Context contexte) {
        if (instance==null) {
            instance = new SerialisationPortefeuilleDAO(contexte);
        }
        return instance;
    }

    public void sauve(Portefeuille p) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    this.contexte.openFileOutput(
                            NOM_FICHIER,
                            Context.MODE_PRIVATE)
            );
            oos.writeObject(p);
            oos.close();
        } catch (IOException ioe) {
            Log.e("sauvegarde", "exception " + ioe.getMessage());
        }
    }

    public Portefeuille charge() {

        Portefeuille p = new Portefeuille();
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    this.contexte.openFileInput(NOM_FICHIER)
            );

            p = (Portefeuille) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException | IOException e) {
            Log.i("chargement", "exception " + e.getMessage());
        }

        return p;
    }

}
