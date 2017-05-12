package ipo2.esi.miikea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Laura Jaime on 12/05/2017.
 */

public class Autenticar extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticar);
    }


    public void oyente_btnRegistrarse(View v){
        //Log.d("Debug_bienvenido", "Pulsó el botón Entrar");

        Intent i = new Intent(this, Registrar.class);
        startActivity(i);
    }
}
