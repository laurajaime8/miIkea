package ipo2.esi.miikea;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class Listado extends AppCompatActivity {

    private ListView lstMuebles;
    private TextView lblSeleccionado;

    private ArrayList<Mueble> muebles;

    private int muebleSeleccionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
            case R.id.aniadirMueble:
                Log.d("LogCat","Pulsó la opción de menú Añadir Contacto");
                Intent i = new Intent(this, AniadirMueble.class);
                Log.d("LogCat", "Item seleccionado "+muebleSeleccionado);
                i.putExtra("nombre", muebles.get(muebleSeleccionado).getNombre());
                i.putExtra("precio", muebles.get(muebleSeleccionado).getPrecio());
                i.putExtra("tipo",muebles.get(muebleSeleccionado).getCategoria());
                i.putExtra("descripcion",muebles.get(muebleSeleccionado).getDescripcion());
                startActivityForResult(i, 1234);
                break;

        }
        return true;
    }
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.verDetalles:
                Log.d("LogCat","Pulsó la opción del menú contextual Ver Detalles");
                //Pasar los datos del contacto a la otra ventana
                Intent i = new Intent(this, DetallesMueble.class);
                Log.d("LogCat", "Item seleccionado "+muebleSeleccionado);
                i.putExtra("nombre", muebles.get(muebleSeleccionado).getNombre());
                i.putExtra("precio", muebles.get(muebleSeleccionado).getPrecio());
                i.putExtra("tipo",muebles.get(muebleSeleccionado).getCategoria());
                i.putExtra("descripcion",muebles.get(muebleSeleccionado).getDescripcion());
                startActivityForResult(i, 1234);
                break;

            case R.id.borrarMueble:
                muebles.remove(muebleSeleccionado);
                // lstMuebles.getAdapter().notifyDataSetChanged();
                Log.d("LogCat","Pulsó la opción de menú contextual Borrar Contacto");
                break;

        }
        return true;


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        if (requestCode == 1234){
            if (resultCode == RESULT_OK){
                Mueble muebleModificado = new Mueble(1,b.getString("nombre"),b.getInt("precio"), b.getInt("tipo"), b.getString("descripcion"));
                muebles.set(muebleSeleccionado, muebleModificado);
                ((AdaptadorLista)lstMuebles.getAdapter()).notifyDataSetChanged();
            }
        }
        if (requestCode == 4321){
            if (resultCode == RESULT_OK){
                Mueble nuevoMueble = new Mueble(1,b.getString("nombre"),b.getInt("precio"), b.getInt("tipo"), b.getString("descripcion"));
                muebles.set(muebleSeleccionado, nuevoMueble);
                ((AdaptadorLista)lstMuebles.getAdapter()).notifyDataSetChanged();
            }
        }
    }

}
