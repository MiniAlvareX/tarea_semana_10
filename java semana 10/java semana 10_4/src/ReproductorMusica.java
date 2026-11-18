import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ReproductorMusica extends JFrame {

    private Clip clip;

    private JButton btnReproducir;
    private JButton btnPausar;
    private JButton btnReanudar;

    private long posicionPausa = 0;

    public ReproductorMusica() {

        setTitle("Reproductor de Música");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        JLabel titulo = new JLabel(
            "REPRODUCTOR DE MÚSICA",
            SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

        btnReproducir = new JButton("Reproducir");
        btnPausar = new JButton("Pausar");
        btnReanudar = new JButton("Reanudar");

        panelBotones.add(btnReproducir);
        panelBotones.add(btnPausar);
        panelBotones.add(btnReanudar);

        panel.add(titulo);
        panel.add(panelBotones);

        add(panel);

        btnReproducir.addActionListener(e -> reproducir());

        btnPausar.addActionListener(e -> pausar());

        btnReanudar.addActionListener(e -> reanudar());

        cargarAudio();

        setVisible(true);
    }

    private void cargarAudio() {
        try {
            File archivo = new File("musica.wav");

            AudioInputStream audio =
                AudioSystem.getAudioInputStream(archivo);

            clip = AudioSystem.getClip();
            clip.open(audio);

            System.out.println("Audio cargado correctamente.");

        } catch (UnsupportedAudioFileException |
                IOException |
                LineUnavailableException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Error al cargar el archivo de audio: " + ex.getMessage()
            );
        }
    }

    private void reproducir() {

        if (clip != null) {

            clip.stop();
            clip.setFramePosition(0);

            posicionPausa = 0;

            clip.start();
        }
    }

    private void pausar() {

        if (clip != null && clip.isRunning()) {

            posicionPausa = clip.getMicrosecondPosition();

            clip.stop();
        }
    }

    private void reanudar() {

        if (clip != null && !clip.isRunning()) {

            if (posicionPausa >= clip.getMicrosecondLength()) {
                posicionPausa = 0;
            }

            clip.setMicrosecondPosition(posicionPausa);
            clip.start();
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new ReproductorMusica();
        });
    }
}
