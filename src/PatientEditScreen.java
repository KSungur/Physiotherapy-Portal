import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientEditScreen extends JFrame {
    Connection connection = null;
    private JPanel contentPane;
    private JTextField tvPatientName;
    private JTextField tvPatientSurname;
    //    private JTextField tvPatientTC;
    private JTextField tvPatientPhone;
    private JTextField tvGender;
    private JTextField tvBirthDate;
    private JTextField tvEmail;
    private JTextField tvRecordDate;

    private String PatientName;
    private String PatientSurname;
    private String PatientTCNo;
    private JTextField tvAdres;


    public void input(String patientTCNo, String patientName, String patientSurname,
                      String patientGender, String patientPhone, String patientBirth,
                      String patientEmail, String patientAdres, String patientRecordDate) {
//        this.PatientName = patientName;
//        this.PatientSurname = patientSurname;
        this.PatientTCNo = patientTCNo;

        tvPatientName.setText(patientName);
        tvPatientSurname.setText(patientSurname);
        tvGender.setText(patientGender);
        tvPatientPhone.setText(patientPhone);
        tvBirthDate.setText(patientBirth);
        tvEmail.setText(patientEmail);
        tvAdres.setText(patientAdres);
        tvRecordDate.setText(patientRecordDate);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PatientEditScreen frame = new PatientEditScreen();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public PatientEditScreen() {

        connection = MySqlConn.dbConnector();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 450, 449);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblPatientEditScreen = new JLabel("Patient Edit");
        lblPatientEditScreen.setBounds(182, 13, 120, 16);
        contentPane.add(lblPatientEditScreen);

        JLabel lblPatientName = new JLabel("Name");
        lblPatientName.setBounds(90, 47, 80, 22);
        contentPane.add(lblPatientName);

        tvPatientName = new JTextField();
        tvPatientName.setBounds(181, 47, 116, 22);
        contentPane.add(tvPatientName);
        tvPatientName.setColumns(10);

        JLabel lblPatientSurname = new JLabel("Surname");
        lblPatientSurname.setBounds(90, 76, 80, 22);
        contentPane.add(lblPatientSurname);

        tvPatientSurname = new JTextField();
        tvPatientSurname.setColumns(10);
        tvPatientSurname.setBounds(181, 76, 116, 22);
        contentPane.add(tvPatientSurname);

        JLabel lblPatientPhone = new JLabel("Phone");
        lblPatientPhone.setBounds(90, 105, 80, 22);
        contentPane.add(lblPatientPhone);

        tvPatientPhone = new JTextField();
        tvPatientPhone.setColumns(10);
        tvPatientPhone.setBounds(181, 105, 116, 22);
        contentPane.add(tvPatientPhone);


        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> {
            try {
                String query = "update patient set Name =?, Surname=?, Phone=?, Gender=?, Birth=?, Email=?, Address=?, RecordDate=? where patientID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, tvPatientName.getText());
                preparedStatement.setString(2, tvPatientSurname.getText());
                //                    preparedStatement.setString(3, tvPatientTC.getText());
                preparedStatement.setString(3, tvPatientPhone.getText());
                preparedStatement.setString(4, tvGender.getText());
                preparedStatement.setString(5, tvBirthDate.getText());
                preparedStatement.setString(6, tvEmail.getText());
                preparedStatement.setString(7, tvAdres.getText());
                preparedStatement.setString(8, tvRecordDate.getText());

//                preparedStatement.setString(9, PatientName);
//                preparedStatement.setString(10, PatientSurname);
                preparedStatement.setString(9, PatientTCNo);
//                    preparedStatement.setString(12, PatientTCNo);

                preparedStatement.execute();
                JOptionPane.showMessageDialog(null, "Veri basarili bir sekilde degistirildi");

                preparedStatement.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        });
        btnSave.setBounds(182, 300, 97, 25);
        contentPane.add(btnSave);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(90, 134, 56, 22);
        contentPane.add(lblGender);

        tvGender = new JTextField();
        tvGender.setColumns(10);
        tvGender.setBounds(181, 134, 116, 22);
        contentPane.add(tvGender);

        JLabel lblBirthdate = new JLabel("Birth Date");
        lblBirthdate.setBounds(90, 163, 120, 22);
        contentPane.add(lblBirthdate);

        tvBirthDate = new JTextField();
        tvBirthDate.setColumns(10);
        tvBirthDate.setBounds(181, 163, 116, 22);
        contentPane.add(tvBirthDate);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(90, 192, 80, 22);
        contentPane.add(lblEmail);

        tvEmail = new JTextField();
        tvEmail.setColumns(10);
        tvEmail.setBounds(181, 192, 116, 22);
        contentPane.add(tvEmail);

        JLabel lblRecorddate = new JLabel("RecordDate");
        lblRecorddate.setBounds(90, 221, 120, 22);
        contentPane.add(lblRecorddate);

        tvRecordDate = new JTextField();
        tvRecordDate.setColumns(10);
        tvRecordDate.setBounds(181, 221, 116, 22);
        contentPane.add(tvRecordDate);

        JLabel lblAdres = new JLabel("Address");
        lblAdres.setBounds(90, 250, 120, 22);
        contentPane.add(lblAdres);

        tvAdres = new JTextField();
        tvAdres.setColumns(10);
        tvAdres.setBounds(181, 250, 116, 22);
        contentPane.add(tvAdres);
    }

}
