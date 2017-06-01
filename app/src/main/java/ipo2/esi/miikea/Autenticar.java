package ipo2.esi.miikea;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;


/**
 * Created by Laura Jaime on 12/05/2017.
 */

public class Autenticar extends Activity {

     EditText txtMail;
     EditText txtPassword;

    private Cursor fila;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticar);

        txtMail = (EditText) findViewById(R.id.txtMail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
    }


    public void oyente_btnLoguearse(View v) {
        Intent i = new Intent(this, Listado.class);
        startActivity(i);
        /*UsuariosSQLiteHelper admin = new UsuariosSQLiteHelper(this, "miikea",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String mail = txtMail.getText().toString();
        String pass = txtPassword.getText().toString();
        fila=db.rawQuery("select email,password from usuarios where email='"+mail+"' and password='"+pass+"'",null);

        if(fila.moveToFirst()){

                String u = fila.getString(0);
                String p = fila.getString(1);

                if (mail.equals(u) && pass.equals(p)) {*/
                   // Intent i = new Intent(this, Lista.class);
                  //  startActivity(i);
               // }


       // }
       
    }

    public void oyente_btnRegistrarse(View v){
        Intent i = new Intent(this, Registrar.class);
        startActivity(i);
    }









}
