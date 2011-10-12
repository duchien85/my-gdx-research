package org.badlogic;

import org.gsn.caro.CaroGame;
import org.gsn.engine.Debug;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;


public class MetaGunAndroidActivity extends AndroidApplication {
    public void onCreate (android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(new CaroGame(), false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
               .setCancelable(false)
               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                        Log.i("androi", "trung");
                   }
               })
               .setNegativeButton("No", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                   }
               });
        AlertDialog alert = builder.create();
}
}