package ipo2.esi.miikea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Laura Jaime on 12/05/2017.
 */

public class Registrar extends Activity {

    private Button btnRegistrarse;
    private EditText txtMail;
    private EditText txtPassword;
    private ConectorBD conectorBD;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);

        conectorBD = new ConectorBD(this);
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*Datos que se pasaran en la URL del PHP*/
                String email = txtMail.getText().toString();
                String sufijoURL= "?mail="+email;
                /*Crear una instancia de la clase Prueba que se ejecuta en segundo plano*/
                Prueba aniadirUsuario= new Prueba();
               // aniadirUsuario.execute(sufijoURL);
            }
        });

    }


}
