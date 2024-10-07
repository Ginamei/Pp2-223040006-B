package Tugas2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BankRakyatForm extends JFrame {
    private JTextField namaField;
    private JTextField alamatField;
    private JList<String> jenisTabunganList;
    private JSlider frekuensiTransaksiSpinner;
    private JSpinner tanggalLahirSpinner;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextArea outputArea;

    public BankRakyatForm() {
        setTitle("Form Pendaftaran Nasabah Bank");
        setSize(500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel input menggunakan GridBagLayout untuk kontrol lebih
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margin antara komponen
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Input Nama
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Nama:"), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        namaField = new JTextField(20);
        inputPanel.add(namaField, gbc);

        // Input Alamat
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Alamat:"), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        alamatField = new JTextField(20);
        inputPanel.add(alamatField, gbc);

        // Jenis Tabungan (JList)
        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Jenis Tabungan:"), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        String[] jenisTabungan = {"Tabungan", "Giro", "Deposito Berjangka", "Deposito Recurring"};
        jenisTabunganList = new JList<>(jenisTabungan);
        jenisTabunganList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        inputPanel.add(new JScrollPane(jenisTabunganList), gbc);

        // Jumlah frekuensi transaksi (JSpinner)
        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(new JLabel("Frekuensi Transaksi (per bulan):"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        frekuensiTransaksiSpinner = new JSlider(0, 100);
        frekuensiTransaksiSpinner.setMajorTickSpacing(10);
        frekuensiTransaksiSpinner.setMinorTickSpacing(1);
        frekuensiTransaksiSpinner.setPaintTicks(true);
        frekuensiTransaksiSpinner.setPaintLabels(true);
        inputPanel.add(frekuensiTransaksiSpinner, gbc);


        // Tanggal Lahir (JSpinner)
        gbc.gridx = 0;
        gbc.gridy = 8;
        inputPanel.add(new JLabel("Tanggal Lahir:"), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 9;
        SpinnerDateModel dateModel = new SpinnerDateModel();
        tanggalLahirSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(tanggalLahirSpinner, "dd/MM/yyyy");
        tanggalLahirSpinner.setEditor(dateEditor);
        inputPanel.add(tanggalLahirSpinner, gbc);

        // Input Password
        gbc.gridx = 0;
        gbc.gridy = 10;
        inputPanel.add(new JLabel("Password:"), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 11;
        passwordField = new JPasswordField(20);
        inputPanel.add(passwordField, gbc);

        // Konfirmasi Password
        gbc.gridx = 0;
        gbc.gridy = 12;
        inputPanel.add(new JLabel("Konfirmasi Password:"), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 13;
        confirmPasswordField = new JPasswordField(20);
        inputPanel.add(confirmPasswordField, gbc);

        // Output Area
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Panel bawah dengan tombol Submit dan Reset
        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Submit");
        JButton resetButton = new JButton("Reset");
        buttonPanel.add(submitButton);
        buttonPanel.add(resetButton);
        
        // ActionListener untuk Submit
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitForm();
            }
        });
        
        // ActionListener untuk Reset
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        // Tambah MenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem resetMenuItem = new JMenuItem("Reset");
        JMenuItem exitMenuItem = new JMenuItem("Keluar");
        
        menu.add(resetMenuItem);
        menu.add(exitMenuItem);
        menuBar.add(menu);
        
        setJMenuBar(menuBar);

        // ActionListener untuk menu Reset
        resetMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
        
        // ActionListener untuk menu Exit
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Tambahkan komponen ke frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method untuk submit form
    private void submitForm() {
        String nama = namaField.getText();
        String alamat = alamatField.getText();
        String jenisTabungan = jenisTabunganList.getSelectedValue();
        int frekuensiTransaksi = (int) frekuensiTransaksiSpinner.getValue();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        
        // Cek apakah password dan confirm password cocok
        if (!password.equals(confirmPassword)) {
            outputArea.setText("Password dan Konfirmasi Password tidak cocok.");
            return;
        }

        // Cek apakah jenis tabungan dipilih
        if (jenisTabungan == null) {
            outputArea.setText("Silakan pilih jenis tabungan.");
            return;
        }

        outputArea.setText("Nama: " + nama + "\n"
                + "Alamat: " + alamat + "\n"
                + "Jenis Tabungan: " + jenisTabungan + "\n"
                + "Frekuensi Transaksi: " + frekuensiTransaksi + " per bulan\n"
                + "Pendaftaran Berhasil!");
    }

    // Method untuk reset form
    private void resetForm() {
        namaField.setText("");
        alamatField.setText("");
        jenisTabunganList.clearSelection();
        frekuensiTransaksiSpinner.setValue(1);
        tanggalLahirSpinner.setValue(new java.util.Date());
        passwordField.setText("");
        confirmPasswordField.setText("");
        outputArea.setText("");
    }

    public static void main(String[] args) {
        new BankRakyatForm();
    }
}