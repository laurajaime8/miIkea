package ipo2.esi.miikea;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



/**
 * Created by Laura Jaime on 12/05/2017.
 */

public class Registrar extends Activity {

    private Button btnRegistrarse;
    private EditText txtMail;
    private EditText txtPassword;
    private ConectorBD conectorBD;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        txtMail = (EditText) findViewById(R.id.txtMail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);

        conectorBD = new ConectorBD(this);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UsuariosSQLiteHelper admin = new UsuariosSQLiteHelper(Registrar.this, "miIkea", null, 1);
                SQLiteDatabase db =admin.getWritableDatabase();

                String email = txtMail.getText().toString();
                String password= txtPassword.getText().toString();
                ContentValues values = new ContentValues();
                values.put("email", email);
                values.put("password", password);
                db.insert("usuarios", null, values);
                Toast.makeText(Registrar.this, "Se ha registrado correctamente.", Toast.LENGTH_SHORT).show();
                db.close();
                Intent i = new Intent(Registrar.this, Autenticar.class);
                startActivity(i);

            }
        });

    }

    public void oyente_btnVolver(View v){
        Intent i = new Intent(this, Autenticar.class);
        startActivity(i);
    }




}
