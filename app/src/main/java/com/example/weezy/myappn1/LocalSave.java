package com.example.weezy.myappn1;

import android.content.Context;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class LocalSave
{
    public static void enregistrer(Context cont, Portefeuille P)
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(cont.openFileOutput("devise.ser",Context.MODE_PRIVATE));
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
