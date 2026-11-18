import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class ReproductorSonidos extends JFrame {

    public ReproductorSonidos() {

        setTitle("Reproductor de Efectos de Sonido");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(
                20, 40, 20, 40));

        panel.setBackground(new Color(230, 240, 250));

        JLabel titulo = new JLabel(
                "REPRODUCTOR DE SONIDOS",
                SwingConstants.CENTER);

        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JButton btnAplausos = new JButton(" Aplausos");
        JButton btnCampana = new JButton(" Campana");
        JButton btnExplosion = new JButton(" Explosión");

        configurarBoton(btnAplausos);
        configurarBoton(btnCampana);
        configurarBoton(btnExplosion);

        btnAplausos.addActionListener(e ->
                reproducirSonido("sonidos/aplausos.wav"));

        btnCampana.addActionListener(e ->
                reproducirSonido("sonidos/campana.wav"));

        btnExplosion.addActionListener(e ->
                reproducirSonido("sonidos/explosion.wav"));

        panel.add(titulo);
        panel.add(btnAplausos);
        panel.add(btnCampana);
        panel.add(btnExplosion);

        add(panel);

        setVisible(true);
    }

    private void configurarBoton(JButton boton) {

        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(new Color(70, 130, 180));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
    }

    private void reproducirSonido(String ruta) {

        try {

            File archivo = new File(ruta);

            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(archivo);

            Clip clip = AudioSystem.getClip();

            clip.open(audio);

            clip.addLineListener(event -> {

                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            clip.start();

        } catch (UnsupportedAudioFileException |
                IOException |
                LineUnavailableException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo reproducir el sonido:\n" + ex.getMessage(),
                    "Error de audio",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new ReproductorSonidos());
    }
}
