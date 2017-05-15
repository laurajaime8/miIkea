package ipo2.esi.miikea;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Laura Jaime on 12/05/2017.
 */

public class ConectorBD {

    static final String NOMBRE_BD = "miIkea";
    private UsuariosSQLiteHelper dbHelper;
    private SQLiteDatabase db;

    /*Constructor*/
    public ConectorBD (Context ctx)
    {
        dbHelper = new UsuariosSQLiteHelper(ctx, NOMBRE_BD, null, 1);
    }
    /*Abre la conexión con la base de datos*/
    public ConectorBD abrir()throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    /*Cierra la conexión con la base de datos*/
    public void cerrar()
    {
        if (db != null) db.close();
    }
    /*inserta un contacto en la BD*/
    public long insertarUsuario(String email, String password, String password2)
    {
        /*String consultaSQL= "INSERT INTO usuarios VALUES('"+email+"','"+password+"')";
        db.execSQL(consultaSQL);*/
        ContentValues nuevoUsuario= new ContentValues();
        nuevoUsuario.put("email", email);
        nuevoUsuario.put("password", password);
        return db.insert("Usuarios", null, nuevoUsuario);
    }
    /*devuelve todos los contactos*/
    public Cursor listarUsuarios()
    {
        //return db.rawQuery("SELECT * FROM Usuarios", null);
        return db.query("Usuarios", new String[] {"email","password"}, null, null, null, null, null);
    }
}
