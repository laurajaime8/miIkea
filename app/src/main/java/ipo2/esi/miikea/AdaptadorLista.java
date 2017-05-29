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
 * Created by Laura Jaime on 28/05/2017.
 */

public class AdaptadorLista extends ArrayAdapter {

    Activity context;

    private ArrayList<Mueble> muebles;

    AdaptadorLista(Activity context, ArrayList<Mueble> muebles) {
        super(context, R.layout.layout_item, muebles);
        this.context = context;
        this.muebles = muebles;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.layout_item, null);
        TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
        lblNombre.setText(muebles.get(position).getNombre());
        TextView lblPrecio = (TextView)item.findViewById(R.id.lblPrecio);
        lblPrecio.setText(String.valueOf(muebles.get(position).getPrecio()));
        ImageView imagMueble = (ImageView)item.findViewById(R.id.imagMueble);
        switch (muebles.get(position).getCategoria())
        {
            case 1: //Cargar imagen de muebles de tipo "hogar"
                imagMueble.setImageResource(R.drawable.hogar);
                break;
            case 2://Cargar imagen de muebles de tipo "oficina"
                imagMueble.setImageResource(R.drawable.oficina);
                break;
        }
        return(item);
    }
}
