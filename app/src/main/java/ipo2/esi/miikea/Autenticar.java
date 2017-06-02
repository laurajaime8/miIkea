package ipo2.esi.miikea;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;




/**
 * Created by Laura Jaime on 12/05/2017.
 */

public class Autenticar extends Activity {

     private EditText txtMail;
     private EditText txtPassword;
     private ConectorBD conector;
     private Cursor fila;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticar);

        txtMail = (EditText) findViewById(R.id.txtMail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        conector = new ConectorBD(this);
    }

    public void oyente_btnLoguearse(View v) {
        UsuariosSQLiteHelper admin=new UsuariosSQLiteHelper(this,"miIkea",null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        String email=txtMail.getText().toString();
        String password=txtPassword.getText().toString();
        fila=db.rawQuery("select email,password from usuarios where email='"+email+"' and password='"+password+"'",null);

        if (fila.moveToFirst()){
            String mail=fila.getString(0);
            String pass=fila.getString(1);
            if (email.equals(mail)&&password.equals(pass)){
                Toast.makeText(Autenticar.this, "Autenticación correcta. Conectando....", Toast.LENGTH_SHORT).show();
                Intent ven=new Intent(this,Listado.class);
                startActivity(ven);
            }
        }
    }

    public void oyente_btnRegistrarse(View v){
        Intent i = new Intent(this, Registrar.class);
        startActivity(i);
    }
}
