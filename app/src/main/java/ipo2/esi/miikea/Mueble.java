package ipo2.esi.miikea;

/**
 * Created by Laura Jaime on 15/05/2017.
 */

public class Mueble {
    private int codigo;
    private String nombre;
    private String precio;
    private int categoria; //Que tipo de articulo es 1:hogar 2:oficina
    private String descripcion;


    public Mueble(int codigo, String nombre, String precio, int categoria, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }


    public String getNombre() {
        return nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public int getCategoria() {
        return categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
