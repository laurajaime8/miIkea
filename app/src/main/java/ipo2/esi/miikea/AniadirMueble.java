package ipo2.esi.miikea;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AniadirMueble extends AppCompatActivity {
    //Atributos privados a nivel de clase
    private EditText txtNombreC;
    private EditText txtPrecioC;
    private Spinner spinnerTipo;
    private EditText txtDescripcionC;

    private Button btnGuardarC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_mueble);
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

        //Obtenemos las referencias a los elementos gráficos de la GUI
        txtNombreC=(EditText)findViewById(R.id.txtNombre);
        txtPrecioC=(EditText)findViewById(R.id.txtPrecio);
        spinnerTipo=(Spinner)findViewById(R.id.spinnerTipo);

        //Llenar de contenido el Spinner
        String[]opciones={"Hogar", "Oficina"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        spinnerTipo.setAdapter(adapter);
        txtDescripcionC=(EditText)findViewById(R.id.txtDescripcion);

        //Recoger los datos enviados por la primera actividad y mostrarlos en la GUI
        Bundle bundle=getIntent().getExtras();
      //  txtNombreC.setText(bundle.getString("nombre"));
   //     txtPrecioC.setText(bundle.getString("precio"));
       // spinnerTipo.setSelection(bundle.getInt("tipo"));
       // txtDescripcionC.setText(bundle.getString("descripcion"));


        btnGuardarC= (Button)findViewById(R.id.btnGuardar);
        btnGuardarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevoMueble= new Intent();
                nuevoMueble.putExtra("nombre",txtNombreC.getText().toString());

                nuevoMueble.putExtra("precio",txtPrecioC.getText().toString());
                nuevoMueble.putExtra("tipo",spinnerTipo.getSelectedItemPosition());
                nuevoMueble.putExtra("descripcion",txtDescripcionC.getText().toString());
                setResult(RESULT_OK, nuevoMueble);
                finish();
            }
        });
    }
    }


