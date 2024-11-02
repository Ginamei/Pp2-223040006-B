import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SkincareApp extends JFrame {
    public JTextField titleField, authorField;
    public JSpinner yearSpinner;
    public JComboBox<String> genreComboBox;
    public JCheckBox availabilityCheckBox;
    public JTextArea descriptionArea;
    public JList<String> typeList;
    public JRadioButton borrowableRadioYes, borrowableRadioNo;
    public ButtonGroup borrowableGroup;
    public JTable table;
    public DefaultTableModel tableModel;
    public int selectedRow = -1;
    public SkincareController controller;
    

    public SkincareApp() {
        controller = new SkincareController(this);
        setTitle("Sistem Manajemen Skincare");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        createMenuBar();
        initializeInputPanel();
        initializeTable();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        JMenuItem resetItem = new JMenuItem("Reset App");
        resetItem.addActionListener(e -> controller.resetApp());
        menu.add(resetItem);

        JMenuItem exitItem = new JMenuItem("Exit App");
        exitItem.addActionListener(e -> System.exit(0));
        menu.add(exitItem);

        setJMenuBar(menuBar);
    }

    private void initializeInputPanel() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0; inputPanel.add(new JLabel("Nama Produk:"), gbc);
        gbc.gridx = 1; titleField = new JTextField(20); inputPanel.add(titleField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; inputPanel.add(new JLabel("Tahun Produksi:"), gbc);
        gbc.gridx = 1; yearSpinner = new JSpinner(new SpinnerNumberModel(2000, 1900, 2100, 1));
        inputPanel.add(yearSpinner, gbc);

        gbc.gridx = 0; gbc.gridy = 3; inputPanel.add(new JLabel("Jenis Produk:"), gbc);
        gbc.gridx = 1; genreComboBox = new JComboBox<>(new String[]{"Cleanser", "Toner", "Serum", "Facial wash", "Moisturizer", "Suncreen"});
        inputPanel.add(genreComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 4; inputPanel.add(new JLabel("Deskripsi:"), gbc);
        gbc.gridx = 1; descriptionArea = new JTextArea(3, 20);
        inputPanel.add(new JScrollPane(descriptionArea), gbc);

        gbc.gridx = 0; gbc.gridy = 5; inputPanel.add(new JLabel("Jenis Produk:"), gbc);
        gbc.gridx = 1; typeList = new JList<>(new String[]{"Cleanser", "Toner", "Serum", "Facial wash", "Moisturizer", "Suncreen"});
        typeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        inputPanel.add(new JScrollPane(typeList), gbc);

        gbc.gridx = 0; gbc.gridy = 6; inputPanel.add(new JLabel("Payment? :"), gbc);
        gbc.gridx = 1; 
        borrowableRadioYes = new JRadioButton("Ya");
        borrowableRadioNo = new JRadioButton("Tidak");
        borrowableGroup = new ButtonGroup();
        borrowableGroup.add(borrowableRadioYes);
        borrowableGroup.add(borrowableRadioNo);
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        radioPanel.add(borrowableRadioYes);
        radioPanel.add(borrowableRadioNo);
        inputPanel.add(radioPanel, gbc);

        // Buttons for CRUD operations
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton addButton = new JButton("Tambah Skincare");
        addButton.addActionListener(e -> controller.addDataToTable());
        JButton updateButton = new JButton("Perbarui Skincare");
        updateButton.addActionListener(e -> controller.updateDataInTable(selectedRow));
        JButton deleteButton = new JButton("Hapus Skincare");
        deleteButton.addActionListener(e -> controller.deleteDataFromTable(selectedRow));

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);

        add(inputPanel, BorderLayout.WEST);
    }

    private void initializeTable() {
        String[] columns = {"Nama Produk", "Tahun Produksi", "Jenis Produk", "Deskripsi", "Jenis Produk", "Tersedia"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Listening for table row selection
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                selectedRow = table.getSelectedRow();
                loadDataToForm(selectedRow);
            }
        });
    }

    public void loadDataToForm(int row) {
        titleField.setText((String) tableModel.getValueAt(row, 0));
        authorField.setText((String) tableModel.getValueAt(row, 1));
        yearSpinner.setValue(Integer.parseInt(tableModel.getValueAt(row, 2).toString()));
        genreComboBox.setSelectedItem(tableModel.getValueAt(row, 3));
        descriptionArea.setText((String) tableModel.getValueAt(row, 4));
        typeList.setSelectedValue(tableModel.getValueAt(row, 5), true);
        borrowableRadioYes.setSelected("Ya".equals(tableModel.getValueAt(row, 6)));
        borrowableRadioNo.setSelected("Tidak".equals(tableModel.getValueAt(row, 6)));
        availabilityCheckBox.setSelected("Ya".equals(tableModel.getValueAt(row, 7)));
    }

    public void clearForm() {
        titleField.setText("");
        authorField.setText("");
        yearSpinner.setValue(2000);
        genreComboBox.setSelectedIndex(0);
        descriptionArea.setText("");
        typeList.clearSelection();
        borrowableGroup.clearSelection();
        availabilityCheckBox.setSelected(false);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SkincareApp().setVisible(true));
    }
}