import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PatientSignupScreen extends JFrame {
    private Connection connection = null;
    private JTextField tvPatientName;
    private JTextField tvPatientSurname;
    private JTextField tvPatientTC;
    private JTextField tvPatientPhone;
    private JComboBox tvGender;
    private JTextField tvBirthday;
    private JTextField tvEmail;
    private JTextField tvAdres;
    private JPasswordField tvPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PatientSignupScreen frame = new PatientSignupScreen();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public PatientSignupScreen() {
        connection = MySqlConn.dbConnector();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 450, 489);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewRecord = new JLabel("New Record");
        lblNewRecord.setBounds(173, 13, 94, 16);
        contentPane.add(lblNewRecord);

        JLabel lblPatientName = new JLabel("Name");
        lblPatientName.setBounds(83, 47, 120, 16);
        contentPane.add(lblPatientName);

        tvPatientName = new JTextField();
        tvPatientName.setBounds(170, 44, 195, 22);
        contentPane.add(tvPatientName);
        tvPatientName.setColumns(10);

        JLabel lblPatientSurname = new JLabel("Surname");
        lblPatientSurname.setBounds(83, 79, 120, 16);
        contentPane.add(lblPatientSurname);

        tvPatientSurname = new JTextField();
        tvPatientSurname.setColumns(10);
        tvPatientSurname.setBounds(170, 76, 195, 22);
        contentPane.add(tvPatientSurname);

        JLabel lblPatientTC = new JLabel("ID");
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

        JButton btnAddPatient = new JButton("Sign Up");
        btnAddPatient.addActionListener((ActionEvent e) -> {
            int reply = JOptionPane.showConfirmDialog(null, "Veri Eklensin mi", "Confirm", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                try {
                    String query = "insert into patient (Name, Surname , patientID , Phone , Gender, Birth, Email, Address, Password, RecordDate) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    Object comboBox = tvGender.getSelectedItem();
                    String gender = comboBox.toString();

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate localDate = LocalDate.now();

                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, tvPatientName.getText());
                    preparedStatement.setString(2, tvPatientSurname.getText());
                    preparedStatement.setString(3, tvPatientTC.getText());
                    preparedStatement.setString(4, tvPatientPhone.getText());
                    preparedStatement.setString(5, gender);
                    preparedStatement.setString(6, tvBirthday.getText());
                    preparedStatement.setString(7, tvEmail.getText());
                    preparedStatement.setString(8, tvAdres.getText());
                    if (tvPassword.getText().length() > 8) {
                        JOptionPane.showMessageDialog(null, "Password cannot be more than 8 characters.");
                    } else {
                        String generatedPassword = null;
                        try {
                            // Create MessageDigest instance for MD5
                            MessageDigest md = MessageDigest.getInstance("MD5");
                            //Add password bytes to digest
                            md.update(tvPassword.getText().getBytes());
                            //Get the hash's bytes
                            byte[] bytes = md.digest();
                            //This bytes[] has bytes in decimal format;
                            //Convert it to hexadecimal format
                            StringBuilder sb = new StringBuilder();
                            for (byte aByte : bytes) {
                                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
                            }
                            //Get complete hashed password in hex format
                            generatedPassword = sb.toString();
                        }
                        catch (NoSuchAlgorithmException e3)
                        {
                            e3.printStackTrace();
                        }


                        preparedStatement.setString(9, generatedPassword);
                    }
                    preparedStatement.setString(10, dtf.format(localDate));

                    preparedStatement.execute();
                    JOptionPane.showMessageDialog(null, "You have signed up successfully.");

                    preparedStatement.close();

                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, e2);
                }
            } else {
                JOptionPane.showMessageDialog(null, "GOODBYE");
            }
        });
        btnAddPatient.setBounds(173, 353, 150, 25);
        contentPane.add(btnAddPatient);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(83, 175, 56, 16);
        contentPane.add(lblGender);

        String[] genderArr = {"Man", "Woman"};
        tvGender = new JComboBox(genderArr);
        tvGender.setSelectedIndex(0);
        tvGender.setBounds(170, 172, 195, 22);
        contentPane.add(tvGender);

        JLabel lblBirthday = new JLabel("Birthday");
        lblBirthday.setBounds(83, 207, 120, 16);
        contentPane.add(lblBirthday);

        tvBirthday = new JTextField();
        tvBirthday.setColumns(10);
        tvBirthday.setBounds(170, 204, 195, 22);
        contentPane.add(tvBirthday);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(83, 239, 120, 16);
        contentPane.add(lblEmail);

        tvEmail = new JTextField();
        tvEmail.setColumns(10);
        tvEmail.setBounds(170, 236, 195, 22);
        contentPane.add(tvEmail);

        JLabel lblAdres = new JLabel("Address");
        lblAdres.setBounds(83, 271, 120, 16);
        contentPane.add(lblAdres);

        tvAdres = new JTextField();
        tvAdres.setColumns(10);
        tvAdres.setBounds(170, 268, 195, 22);
        contentPane.add(tvAdres);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(83, 303, 120, 16);
        contentPane.add(lblPassword);

        tvPassword = new JPasswordField();
        tvPassword.setBounds(170, 300, 195, 22);
        contentPane.add(tvPassword);
    }


}
