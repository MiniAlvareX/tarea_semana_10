import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private JTextField[] temperaturas;
    private String[] dias = {
        "Lunes", "Martes", "Miercoles", "Jueves",
        "Viernes", "Sabado", "Domingo"
    };

    private GraficoTemperaturas panelGrafico;

    public Main() {
        setTitle("Registro de Temperaturas Semanales");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JPanel panelDatos = new JPanel(new GridLayout(2, 7, 5, 5));
        panelDatos.setBorder(
            BorderFactory.createTitledBorder("Temperaturas de la semana")
        );

        temperaturas = new JTextField[7];

        for (int i = 0; i < 7; i++) {
            panelDatos.add(new JLabel(dias[i], JLabel.CENTER));
        }

        for (int i = 0; i < 7; i++) {
            temperaturas[i] = new JTextField();
            temperaturas[i].setHorizontalAlignment(
                JTextField.CENTER
            );
            panelDatos.add(temperaturas[i]);
        }

        JButton btnMostrar = new JButton("Mostrar Gráfico");

        btnMostrar.addActionListener(e -> mostrarGrafico());

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelDatos, BorderLayout.CENTER);
        panelSuperior.add(btnMostrar, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

        panelGrafico = new GraficoTemperaturas();

        add(panelGrafico, BorderLayout.CENTER);
    }

    private void mostrarGrafico() {
        double[] valores = new double[7];

        try {
            for (int i = 0; i < 7; i++) {
                valores[i] = Double.parseDouble(
                    temperaturas[i].getText().trim().replace(',', '.')
                );
            }

            panelGrafico.setTemperaturas(valores);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Ingrese una temperatura válida en los siete días.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
