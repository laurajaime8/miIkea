package ipo2.esi.miikea;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Laura Jaime on 12/05/2017.
 */

public class Registrar extends Activity {

    private ImageButton btnRegistrarse;
    private EditText txtMail;
    private EditText txtPassword;
    private ConectorBD conectorBD;

    //private ArrayList<Usuario> usuarios = new ArrayList<>();
    //private AdaptadorListaUsuarios adaptadorLista;

    private ProgressDialog dialogoCargando;
    static final String ANIADIR_USUARIO_PHP = "http://192.168.56.1/ProyectoMiIkea/InsertarUsuariosBD.php";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        txtMail = (EditText) findViewById(R.id.txtMail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnRegistrarse = (ImageButton) findViewById(R.id.btnRegistrarse);

        conectorBD = new ConectorBD(this);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            /*Datos que se pasaran en la URL del PHP*/
                String email = txtMail.getText().toString();
                String password= txtPassword.getText().toString();
                String sufijoURL= "?email="+email+"&password="+password;
                AniadirUsuarioURL aniadirUsuario= new AniadirUsuarioURL();
                aniadirUsuario.execute(sufijoURL);
            }
        });

    }

    public void oyente_btnVolver(View v){
        Intent i = new Intent(this, Autenticar.class);
        startActivity(i);
    }

    /*Clase para Añadir los usuarios a la BD*/
    class AniadirUsuarioURL extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogoCargando= ProgressDialog.show(Registrar.this, "Por favor, espere...", null, true, true);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialogoCargando.dismiss();
            Toast.makeText(Registrar.this, s, Toast.LENGTH_LONG).show();
        }
        @Override
        protected String doInBackground(String... params) {
            String s = params[0];
            BufferedReader bufferedReader= null;
            try {
                /*Crear un objeto URL y abrir la conexión*/
                URL url= new URL(ANIADIR_USUARIO_PHP + s);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                /*Recibir el resultado de la operación de inserción*/
                bufferedReader= new BufferedReader(new InputStreamReader(con.getInputStream()));
                String cadenaDevueltaPHP;
                cadenaDevueltaPHP= bufferedReader.readLine();
                return cadenaDevueltaPHP;
            } catch (Exception e) {
                return null;
            }
        }
    }


}
