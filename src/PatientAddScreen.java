import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;

public class PatientAddScreen extends JFrame {
    Connection connection = null;
    private JPanel contentPane;
    private JTextField tvPatientName;
    private JTextField tvPatientSurname;
    private JTextField tvPatientTC;
    private JTextField tvPatientPhone;
    private JTextField tvGender;
    private JTextField tvBirthday;
    private JTextField tvEmail;
    private JTextField tvAdres;
    private JPasswordField tvPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PatientAddScreen frame = new PatientAddScreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public PatientAddScreen() {
        connection = MySqlConn.dbConnector();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 450, 489);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewRecord = new JLabel("New Record");
        lblNewRecord.setBounds(173, 13, 94, 16);
        contentPane.add(lblNewRecord);

        JLabel lblPatientName = new JLabel("Name");
        lblPatientName.setBounds(83, 47, 56, 16);
        contentPane.add(lblPatientName);

        tvPatientName = new JTextField();
        tvPatientName.setBounds(170, 44, 195, 22);
        contentPane.add(tvPatientName);
        tvPatientName.setColumns(10);

        JLabel lblPatientSurname = new JLabel("Surname");
        lblPatientSurname.setBounds(83, 79, 56, 16);
        contentPane.add(lblPatientSurname);

        tvPatientSurname = new JTextField();
        tvPatientSurname.setColumns(10);
        tvPatientSurname.setBounds(170, 76, 195, 22);
        contentPane.add(tvPatientSurname);

        JLabel lblPatientTC = new JLabel("TC No");
        lblPatientTC.setBounds(83, 111, 56, 16);
        contentPane.add(lblPatientTC);

        tvPatientTC = new JTextField();
        tvPatientTC.setColumns(10);
        tvPatientTC.setBounds(170, 108, 195, 22);
        contentPane.add(tvPatientTC);

        JLabel lblPatientPhone = new JLabel("Phone");
        lblPatientPhone.setBounds(83, 143, 56, 16);
        contentPane.add(lblPatientPhone);

        tvPatientPhone = new JTextField();
        tvPatientPhone.setColumns(10);
        tvPatientPhone.setBounds(170, 140, 195, 22);
        contentPane.add(tvPatientPhone);

        JButton btnAddPatient = new JButton("Add Patient");
        btnAddPatient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Veri Eklensin mi", "Baslik", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        String query = "insert into Patients (ad, soyad , tc , tel, gender, birth, email, adres, password) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, tvPatientName.getText());
                        preparedStatement.setString(2, tvPatientSurname.getText());
                        preparedStatement.setString(3, tvPatientTC.getText());
                        preparedStatement.setString(4, tvPatientPhone.getText());
                        preparedStatement.setString(5, tvGender.getText());
                        preparedStatement.setString(6, tvBirthday.getText());
                        preparedStatement.setString(7, tvEmail.getText());
                        preparedStatement.setString(8, tvAdres.getText());
                        preparedStatement.setString(9, tvPassword.getText());

                        preparedStatement.execute();
                        JOptionPane.showMessageDialog(null, "Veriler Basari ile Eklendi");

                        preparedStatement.close();

                    } catch (Exception e2) {
                        JOptionPane.showMessageDialog(null, e2);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "GOODBYE");
                }
            }
        });
        btnAddPatient.setBounds(173, 353, 97, 25);
        contentPane.add(btnAddPatient);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(83, 175, 56, 16);
        contentPane.add(lblGender);

        tvGender = new JTextField();
        tvGender.setColumns(10);
        tvGender.setBounds(170, 172, 195, 22);
        contentPane.add(tvGender);

        JLabel lblBirthday = new JLabel("Birthday");
        lblBirthday.setBounds(83, 207, 56, 16);
        contentPane.add(lblBirthday);

        tvBirthday = new JTextField();
        tvBirthday.setColumns(10);
        tvBirthday.setBounds(170, 204, 195, 22);
        contentPane.add(tvBirthday);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(83, 239, 56, 16);
        contentPane.add(lblEmail);

        tvEmail = new JTextField();
        tvEmail.setColumns(10);
        tvEmail.setBounds(170, 236, 195, 22);
        contentPane.add(tvEmail);

        JLabel lblAdres = new JLabel("Adres");
        lblAdres.setBounds(83, 271, 56, 16);
        contentPane.add(lblAdres);

        tvAdres = new JTextField();
        tvAdres.setColumns(10);
        tvAdres.setBounds(170, 268, 195, 22);
        contentPane.add(tvAdres);

        JLabel lblPassword = new JLabel("Adres");
        lblPassword.setBounds(83, 303, 56, 16);
        contentPane.add(lblPassword);

        tvPassword = new JPasswordField();
        tvPassword.setBounds(170, 300, 195, 22);
        contentPane.add(tvPassword);
    }
}
