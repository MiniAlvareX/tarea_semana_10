package modelo;

public class Producto {

    private String nombre;
    private double precio;
    private int cantidadStock;
    private String categoria;

    public Producto(String nombre, double precio,
                    int cantidadStock, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
        this.categoria = categoria;
    }

    public void actualizarProducto(String nombre, double precio,
                                int cantidadStock, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
            "\nPrecio: S/ " + String.format("%.2f", precio) +
            "\nCantidad en stock: " + cantidadStock +
            "\nCategoría: " + categoria;
    }
}
