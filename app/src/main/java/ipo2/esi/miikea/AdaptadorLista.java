package ipo2.esi.miikea;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Laura Jaime on 15/05/2017.
 */

public class AdaptadorLista extends ArrayAdapter {

    Activity context;
    private ArrayList<Mueble> muebles;

    AdaptadorLista(Activity context, ArrayList<Mueble> muebles) {
        super(context, R.layout.activity_mueble, muebles);
        this.context = context;
        this.muebles = muebles;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.activity_mueble, null);
        TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
        lblNombre.setText(muebles.get(position).getNombre());
        TextView lblPrecio = (TextView)item.findViewById(R.id.lblPrecio);
        lblPrecio.setText(muebles.get(position).getPrecio());
        TextView lblDescripcion = (TextView)item.findViewById(R.id.lblDescripcion);
        lblDescripcion.setText(muebles.get(position).getDescripcion());
        /*ImageView imagMueble = (ImageView)item.findViewById(R.id.imagMueble);
        switch (muebles.get(position).getCategoria())
        {
            case 0: //Muebles dormitorio
               // imagContacto.setImageResource(R.drawable.familia);
                break;
            case 1: //Muebles de cocina
                //imagContacto.setImageResource(R.drawable.amigo);
                break;
            case 2: //Muebles de terraza
              //  imagContacto.setImageResource(R.drawable.trabajo);
                break;
            case 3: //Muebles de baño
                break;
        }*/
        return(item);
    }
}
