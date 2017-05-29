package ipo2.esi.miikea;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lstMuebles = (ListView)findViewById(R.id.lstMuebles);
        muebles = new ArrayList<Mueble>();


        AdaptadorLista adaptador = new AdaptadorLista(this, muebles);
        lstMuebles.setAdapter(adaptador);

        lblSeleccionado = (TextView)findViewById(R.id.lblSeleccionado);


        lstMuebles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                //lblSeleccionado.setText("Contacto seleccionado: "+ lstMuebles.getItemAtPosition(posicion));
                lblSeleccionado.setText("Mueble seleccionado: "+ ((Mueble)lstMuebles.getItemAtPosition(posicion)).getNombre());
            }
        });


        muebles.add(new Mueble(1,"Sofa comodo",100, 1,"sofa comodo"));
    }




}