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

        txtNombreC=(EditText)findViewById(R.id.txtNombre);
        txtPrecioC=(EditText)findViewById(R.id.txtPrecio);
        spinnerTipo=(Spinner)findViewById(R.id.spinnerTipo);

        String[]opciones={"Hogar", "Oficina", "Baño"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        spinnerTipo.setAdapter(adapter);
        txtDescripcionC=(EditText)findViewById(R.id.txtDescripcion);

        Bundle bundle=getIntent().getExtras();

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


