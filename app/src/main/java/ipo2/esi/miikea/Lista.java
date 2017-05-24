package ipo2.esi.miikea;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Laura Jaime on 15/05/2017.
 */

public class Lista extends Activity{

    /*ESTATICO*/
    private String[] mueblesEstatico={"Mesa", "Silla"};
    private ListView lstMuebles;
    private TextView lblSeleccionado;

    static final String LISTAR_PRODUCTOS = "http://192.168.56.1/ProyectoMiIkea/ListarProductos.php";
    private ProgressDialog dialogoCargando;

    private ArrayList<Mueble> muebles = new ArrayList<>();

    private static final String TAG_PRODUCTOS="productos";

    private static final String TAG_CODIGO= "codigo";
    private static final String TAG_NOMBRE = "nombre";
    private static final String TAG_PRECIO= "precio";
    private static final String TAG_CATEGORIA= "categoria";
    private static final String TAG_DESCRIPCION= "descripcion";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lstMuebles =(ListView)findViewById(R.id.lstMuebles);
        //Añadir contenido estático al elemento ListView
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mueblesEstatico);
        lstMuebles.setAdapter(adaptador);
        //Obtener una referencia a la etiqueta en la que se mostrará el ítem seleccionado
        lblSeleccionado= (TextView)findViewById(R.id.lblSeleccionado);




        lstMuebles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
               /*ESTATICO*/
               lblSeleccionado.setText("Artículo seleccionado: "+ lstMuebles.getItemAtPosition(posicion));

            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.acercaDe:
                Log.d("LogCat","Pulsó la opción de menú Acerca De...");
                //Se muestra una ventana de diálogo
                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setTitle("Acerca de...");
                builder.setMessage("Aplicación creada por Laura Jaime y Oliva Gálvez");
                builder.setPositiveButton("OK",null);
                builder.create();
                builder.show();
                break;
            case R.id.aniadirContacto:
                Log.d("LogCat","Pulsó la opción de menú Añadir Contacto");
        }
        return true;
    }


    public void oyente_btnCargarProductos(View v){
        CargarProductosURL leerContactosURL= new CargarProductosURL();
        leerContactosURL.execute(LISTAR_PRODUCTOS);
    }


    /*Clase para cargar los productos de la BD*/
    class CargarProductosURL extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogoCargando= ProgressDialog.show(Lista.this, "Por favor, espere...",null,true,true);
        }
        @Override
        protected String doInBackground(String... params) {
            String uri= params[0];
            BufferedReader bufferedReader= null;
            try {
            /*Crear un objeto URL y abrir la conexión*/
                URL url= new URL(uri);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                /*Obtener el Stringdevuelto por la URL*/
                StringBuilder sb= new StringBuilder();
                bufferedReader= new BufferedReader(new InputStreamReader(con.getInputStream()));
                String cadenaDevueltaPHP;
                while((cadenaDevueltaPHP= bufferedReader.readLine())!= null){
                    sb.append(cadenaDevueltaPHP+"\n");
                }
                Log.d("LogCat", "Contenido devuelto por el PHP\n" + sb.toString());
                return sb.toString();
            }catch(Exception e){
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            /*Ocultar el ProgressDialog*/
            dialogoCargando.dismiss();
            /*Limpiar el arrayListde Contactos y cargarlos con los datos parseados*/
            muebles.removeAll(muebles);
            muebles = parsearJSON(s);
/*contactos = parsearXML(s);*/
            if(muebles!=null) {
                ((BaseAdapter) lstMuebles.getAdapter()).notifyDataSetChanged();
            }else{
                Toast.makeText(Lista.this,"Ocurrió un error de Parsing",Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*Método que parseaun Stringen formato JSON en un arrayde Contactos*/
    private ArrayList<Mueble> parsearJSON(String json)
    {
        JSONArray mueblesJSON= null;
        JSONObject jsonObject= null;
        Mueble mueble;
        try {
            jsonObject= new JSONObject(json);
            mueblesJSON= jsonObject.getJSONArray(TAG_PRODUCTOS);
            for(int i = 0; i < mueblesJSON.length(); i++ ){
                jsonObject= mueblesJSON.getJSONObject(i);
                mueble = new Mueble(jsonObject.getInt(TAG_CODIGO),jsonObject.getString(TAG_NOMBRE),jsonObject.getInt(TAG_PRECIO),jsonObject.getInt(TAG_CATEGORIA), jsonObject.getString(TAG_DESCRIPCION));
                muebles.add(mueble);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return muebles;
    }
}


