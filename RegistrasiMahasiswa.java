import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrasiMahasiswa extends JFrame {
    // Komponen Form
    private JTextField txtNama, txtTglLahir, txtNoDaftar, txtNoTelp, txtEmail;
    private JTextArea txtAlamat;
    private JButton btnSubmit;

    public RegistrasiMahasiswa() {
        setTitle("Form Daftar Ulang Mahasiswa");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Inisialisasi Komponen
        txtNama = new JTextField(20);
        txtTglLahir = new JTextField(20);
        txtNoDaftar = new JTextField(20);
        txtNoTelp = new JTextField(20);
        txtAlamat = new JTextArea(3, 20);
        txtEmail = new JTextField(20);
        btnSubmit = new JButton("Submit");

        // Menambahkan Label dan Field ke Layout
        addFormField("Nama Lengkap:", txtNama, 0, gbc);
        addFormField("Tanggal Lahir:", txtTglLahir, 1, gbc);
        addFormField("Nomor Pendaftaran:", txtNoDaftar, 2, gbc);
        addFormField("No. Telp:", txtNoTelp, 3, gbc);
        
        gbc.gridy = 4; gbc.gridx = 0; add(new JLabel("Alamat:"), gbc);
        gbc.gridx = 1; add(new JScrollPane(txtAlamat), gbc);
        
        addFormField("E-mail:", txtEmail, 5, gbc);

        gbc.gridy = 6; gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(btnSubmit, gbc);

        // Logika Tombol Submit
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSubmit();
            }
        });
    }

    private void addFormField(String label, JTextField field, int y, GridBagConstraints gbc) {
        gbc.gridy = y; gbc.gridx = 0; add(new JLabel(label), gbc);
        gbc.gridx = 1; add(field, gbc);
    }

    private void prosesSubmit() {
        // 1. Validasi Kosong
        if (txtNama.getText().isEmpty() || txtTglLahir.getText().isEmpty() || 
            txtNoDaftar.getText().isEmpty() || txtNoTelp.getText().isEmpty() || 
            txtAlamat.getText().isEmpty() || txtEmail.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Semua kolom harus terisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Panel Konfirmasi
        int response = JOptionPane.showConfirmDialog(this, 
            "Apakah anda yakin data yang Anda isi sudah benar?", 
            "Konfirmasi", JOptionPane.OK_CANCEL_OPTION);

        if (response == JOptionPane.OK_OPTION) {
            tampilkanDataBaru();
        }
    }

   private void tampilkanDataBaru() {
    JFrame frameOutput = new JFrame("Data Mahasiswa");
    frameOutput.setSize(400, 350);
    frameOutput.setLocationRelativeTo(this);
    frameOutput.setLayout(new BorderLayout());

    // Panel Utama dengan Padding (warna abu-abu tipis seperti background frame)
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // Label Judul di Tengah
    JLabel lblJudul = new JLabel("Data Mahasiswa", SwingConstants.CENTER);
    lblJudul.setFont(new Font("Arial", Font.PLAIN, 14));
    lblJudul.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
    mainPanel.add(lblJudul, BorderLayout.NORTH);

    // Area Teks dengan Border Garis Biru
    String data = String.format(
        " Nama               : %s\n" +
        " Tanggal Lahir      : %s\n" +
        " No. Pendaftaran    : %s\n" +
        " No. Telp           : %s\n" +
        " Alamat             : %s\n" +
        " E-mail             : %s",
        txtNama.getText(), txtTglLahir.getText(), txtNoDaftar.getText(),
        txtNoTelp.getText(), txtAlamat.getText(), txtEmail.getText()
    );

    JTextArea areaData = new JTextArea(data);
    areaData.setEditable(false);
    areaData.setFont(new Font("Monospaced", Font.PLAIN, 12));
    areaData.setBackground(Color.WHITE);
    
    // Memberikan margin dalam teks agar tidak nempel ke garis biru
    areaData.setMargin(new Insets(10, 10, 10, 10));

    // Membuat Border Garis Biru (Sesuai Gambar)
    areaData.setBorder(BorderFactory.createLineBorder(new Color(100, 150, 200), 2));

    mainPanel.add(areaData, BorderLayout.CENTER);
    frameOutput.add(mainPanel);
    frameOutput.setVisible(true);
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrasiMahasiswa().setVisible(true));
    }
}