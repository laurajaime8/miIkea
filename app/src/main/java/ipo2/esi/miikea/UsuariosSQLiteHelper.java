package ipo2.esi.miikea;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Laura Jaime on 12/05/2017.
 */

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {

    /*Crea la tabla Usuarios*/
    String sqlCrearTabla = "CREATE TABLE Usuarios(email TEXT, password TEXT)";
    public UsuariosSQLiteHelper(Context contexto, String nombreBD, SQLiteDatabase.CursorFactory factory, int versionBD){
        super(contexto, nombreBD, factory, versionBD);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        try{
            /*Se ejecuta la sentencia SQL*/
            db.execSQL(sqlCrearTabla);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {

        try {
            /*Se elimina la versión anterior de la tabla*/
            db.execSQL("DROP TABLE IF EXISTS Usuarios");
            /*Se creala nueva versión de la tabla*/
            db.execSQL(sqlCrearTabla);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
