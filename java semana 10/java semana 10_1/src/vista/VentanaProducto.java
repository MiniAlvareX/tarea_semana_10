package vista;

import modelo.Producto;
import javax.swing.*;
import java.awt.*;

public class VentanaProducto extends JFrame {

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtCantidad;
    private JTextField txtCategoria;

    private JLabel lblInformacion;
    private Producto producto;

    public VentanaProducto() {

        producto = new Producto("", 0, 0, "");

        setTitle("Gestión de Productos");
        setSize(450, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        panel.add(new JLabel("Nombre del producto:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panel.add(txtPrecio);

        panel.add(new JLabel("Cantidad en stock:"));
        txtCantidad = new JTextField();
        panel.add(txtCantidad);

        panel.add(new JLabel("Categoría:"));
        txtCategoria = new JTextField();
        panel.add(txtCategoria);

        JButton btnActualizar = new JButton("Actualizar Producto");
        panel.add(new JLabel(""));
        panel.add(btnActualizar);

        lblInformacion = new JLabel("Ingrese los datos del producto.");
        lblInformacion.setVerticalAlignment(SwingConstants.TOP);
        lblInformacion.setBorder(
            BorderFactory.createTitledBorder("Información actualizada")
        );

        add(panel, BorderLayout.NORTH);
        add(lblInformacion, BorderLayout.CENTER);

        btnActualizar.addActionListener(e -> actualizarProducto());
    }

    private void actualizarProducto() {

        try {
            String nombre = txtNombre.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText());
            int cantidad = Integer.parseInt(txtCantidad.getText());
            String categoria = txtCategoria.getText().trim();

            if (nombre.isEmpty() || categoria.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Complete todos los campos.");
                return;
            }

            if (precio < 0 || cantidad < 0) {
                JOptionPane.showMessageDialog(this,
                    "El precio y la cantidad no pueden ser negativos.");
                return;
            }

            producto.actualizarProducto(
                nombre, precio, cantidad, categoria
            );

            lblInformacion.setText(
                "<html>" +
                producto.toString().replace("\n", "<br>") +
                "</html>"
            );

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Ingrese un precio y una cantidad válidos.");
        }
    }
}
