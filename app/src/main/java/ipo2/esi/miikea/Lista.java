package ipo2.esi.miikea;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * Created by Laura Jaime on 15/05/2017.
 */

public class Lista extends Activity{

   // private String[] mueblesEstatico={"Mesa", "Silla"};
    private ListView lstMuebles;

    private TextView lblSeleccionado;


    private ArrayList<Mueble> muebles;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lstMuebles =(ListView)findViewById(R.id.lstMuebles);
        //Añadir contenido estático al elemento ListView
       // ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mueblesEstatico);
        //lstMuebles.setAdapter(adaptador);

        AdaptadorLista adaptador = new AdaptadorLista(this, muebles);
        lstMuebles.setAdapter(adaptador);

        //Obtener una referencia a la etiqueta en la que se mostrará el ítem seleccionado
        lblSeleccionado= (TextView)findViewById(R.id.lblSeleccionado);
        lstMuebles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
               //lblSeleccionado.setText("Artículo seleccionado: "+ lstMuebles.getItemAtPosition(posicion));
                lblSeleccionado.setText("Artículo seleccionado: " + ((Mueble)lstMuebles.getItemAtPosition(posicion)).getNombre());
            }
        });

        muebles = new ArrayList<Mueble>();

        muebles.add(new Mueble("TAblento", "200", 2, "Mesa comoda"));

    }
}
