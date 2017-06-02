package ipo2.esi.miikea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class DetallesMueble extends AppCompatActivity {

    //Atributos privados a nivel de clase
    private EditText txtNombreC;
    private EditText txtPrecioC;
    private Spinner spinnerTipo;
    private EditText txtDescripcionC;

    private Button btnGuardarC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_mueble);


        txtNombreC=(EditText)findViewById(R.id.txtNombre);
        txtPrecioC=(EditText)findViewById(R.id.txtPrecio);
        spinnerTipo=(Spinner)findViewById(R.id.spinnerTipo);

        String[]opciones={"Hogar", "Oficina", "Baño"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        spinnerTipo.setAdapter(adapter);
        txtDescripcionC=(EditText)findViewById(R.id.txtDescripcion);

        Bundle bundle=getIntent().getExtras();
        txtNombreC.setText(bundle.getString("nombre"));
        txtPrecioC.setText(bundle.getString("precio"));
        spinnerTipo.setSelection(bundle.getInt("tipo"));
        txtDescripcionC.setText(bundle.getString("descripcion"));


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
