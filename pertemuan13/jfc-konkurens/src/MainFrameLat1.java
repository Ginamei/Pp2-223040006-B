import javax.swing.*;
import java.awt.*;

public class MainFrameLat1 {
  public static void main(String[] args) {
    // Membuat frame utama
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Contoh Konkurensi di Swing");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(400, 200);
      frame.setLayout(new BorderLayout());

      // Label untuk status
      JLabel statusLabel = new JLabel("Tekan tombol untuk mulai tugas berat", JLabel.CENTER);

      // Tombol untuk memulai proses
      JButton startButton = new JButton("Mulai");

      // Progress bar
      JProgressBar progressBar = new JProgressBar(0, 60);
      progressBar.setValue(0);
      progressBar.setStringPainted(true);

      // Tambah komponen frame
      frame.add(statusLabel, BorderLayout.NORTH);
      frame.add(progressBar, BorderLayout.CENTER);
      frame.add(startButton, BorderLayout.SOUTH);

      // Tombol aksi
      startButton.addActionListener(e -> {
        // Update progress bar 1% per detik
        for (int i = 0; i <= 60; i++) { // Perbaikan loop batas
          progressBar.setValue(i);
          try {
            Thread.sleep(1000); // Simulasi pekerjaan berat
          } catch (Exception ex) {
            System.err.println(ex.getMessage()); // Perbaikan nama 'System'
          }
        }
      });

      // Tampilkan frame
      frame.setLocationRelativeTo(null);
      frame.setVisible(true); // Menambahkan tanda titik koma
    });
  }
}