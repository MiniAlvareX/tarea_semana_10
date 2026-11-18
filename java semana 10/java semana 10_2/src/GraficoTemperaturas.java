import javax.swing.*;
import java.awt.*;

public class GraficoTemperaturas extends JPanel {

    private double[] temperaturas;

    private String[] dias = {
        "Lun", "Mar", "Mie", "Jue",
        "Vie", "Sab", "Dom"
    };

    public GraficoTemperaturas() {
        setBackground(Color.WHITE);
        setBorder(
            BorderFactory.createTitledBorder("Gráfico de temperaturas")
        );
    }

    public void setTemperaturas(double[] valores) {
        temperaturas = valores.clone();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (temperaturas == null) {
            g.setColor(Color.GRAY);
            g.drawString(
                "Ingrese las temperaturas y presione Mostrar Gráfico",
                30, 50
            );
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        int ancho = getWidth();
        int alto = getHeight();

        int margenIzquierdo = 65;
        int margenDerecho = 30;
        int margenSuperior = 45;
        int margenInferior = 55;

        int anchoGrafico = ancho - margenIzquierdo - margenDerecho;
        int altoGrafico = alto - margenSuperior - margenInferior;

        double min = temperaturas[0];
        double max = temperaturas[0];

        for (double t : temperaturas) {
            min = Math.min(min, t);
            max = Math.max(max, t);
        }

        double rango = max - min;
        double espacio = Math.max(2, rango * 0.2);

        min -= espacio;
        max += espacio;

        g2.setColor(Color.BLACK);
        g2.drawLine(
            margenIzquierdo,
            margenSuperior,
            margenIzquierdo,
            margenSuperior + altoGrafico
        );

        g2.drawLine(
            margenIzquierdo,
            margenSuperior + altoGrafico,
            margenIzquierdo + anchoGrafico,
            margenSuperior + altoGrafico
        );

        for (int i = 0; i <= 5; i++) {
            int y = margenSuperior + altoGrafico
                    - (i * altoGrafico / 5);

            double valor = min + (max - min) * i / 5.0;

            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(
                margenIzquierdo, y,
                margenIzquierdo + anchoGrafico, y
            );

            g2.setColor(Color.BLACK);
            g2.drawString(
                String.format("%.1f", valor),
                15, y + 5
            );
        }

        int[] x = new int[7];
        int[] y = new int[7];

        for (int i = 0; i < 7; i++) {
            x[i] = margenIzquierdo
                    + (i * anchoGrafico / 6);

            y[i] = margenSuperior + altoGrafico
                    - (int) ((temperaturas[i] - min)
                    / (max - min) * altoGrafico);
        }

        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(2.5f));

        for (int i = 0; i < 6; i++) {
            g2.drawLine(x[i], y[i], x[i + 1], y[i + 1]);
        }

        for (int i = 0; i < 7; i++) {
            g2.setColor(Color.RED);
            g2.fillOval(x[i] - 5, y[i] - 5, 10, 10);

            g2.setColor(Color.BLACK);
            g2.drawString(dias[i], x[i] - 10,
                    margenSuperior + altoGrafico + 25);

            g2.drawString(
                String.format("%.1f °C", temperaturas[i]),
                x[i] - 20,
                y[i] - 12
            );
        }

        g2.dispose();
    }
}
