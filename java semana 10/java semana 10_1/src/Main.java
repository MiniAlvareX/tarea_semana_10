import vista.VentanaProducto;
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaProducto ventana = new VentanaProducto();
            ventana.setVisible(true);
        });

    }
}
