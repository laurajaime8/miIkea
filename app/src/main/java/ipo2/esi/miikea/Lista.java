package ipo2.esi.miikea;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Laura Jaime on 28/05/2017.
 */

public class Lista extends Activity{

    private ListView lstMuebles;
    private TextView lblSeleccionado;

    private ArrayList<Mueble>muebles;

    private int muebleSeleccionado;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lstMuebles = (ListView)findViewById(R.id.lstMuebles);
        muebles = new ArrayList<Mueble>();


        AdaptadorLista adaptador = new AdaptadorLista(this, muebles);
        lstMuebles.setAdapter(adaptador);

        lblSeleccionado = (TextView)findViewById(R.id.lblSeleccionado);
        registerForContextMenu(lstMuebles);

        lstMuebles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                //lblSeleccionado.setText("Contacto seleccionado: "+ lstMuebles.getItemAtPosition(posicion));
                lblSeleccionado.setText("Mueble seleccionado: "+ ((Mueble)lstMuebles.getItemAtPosition(posicion)).getNombre());
            }
        });


        muebles.add(new Mueble(1,"Sofa comodo",100, 1,"sofa comodo"));
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);

        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) menuInfo;
        muebleSeleccionado= info.position;
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
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.verDetalles:
                Log.d("LogCat","Pulsó la opción del menú contextual Ver Detalles");
                break;
            case R.id.borrarMueble:
                muebles.remove(muebleSeleccionado);
               // lstMuebles.getAdapter().notifyDataSetChanged();
                Log.d("LogCat","Pulsó la opción de menú contextual Borrar Contacto");
        }
        return true;
    }



}