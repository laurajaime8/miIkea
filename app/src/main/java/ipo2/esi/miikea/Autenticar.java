package ipo2.esi.miikea;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Laura Jaime on 12/05/2017.
 */

public class Autenticar extends Activity{

    private EditText txtMail;
    private EditText txtPassword;


    static final String URL_PHP_JSON ="http://192.168.56.1/ProyectoMiIkea/BuscarUsuariosBD.php";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticar);

    }

    public void oyente_btnRegistrarse(View v){
        Intent i = new Intent(this, Registrar.class);
        startActivity(i);
    }


    public void oyente_btnLoguearse(View view){
       // Log.d("Debug_bienvenido", "Pulsó el botón Login");
        Intent i = new Intent(this, AdaptadorLista.class);
        startActivity(i);
    }


}
