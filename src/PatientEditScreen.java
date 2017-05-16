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


    public void input(String patientName, String patientSurname, String patientTCNo, String patientPhone,
                      String patientGender, String patientBirth, String patientEmail, String patientAdres,
                      String patientRecordDate) {
        this.PatientName = patientName;
        this.PatientSurname = patientSurname;
//        this.PatientTCNo = patientTCNo;

        tvPatientName.setText(patientName);
        tvPatientSurname.setText(patientSurname);
//        tvPatientTC.setText(patientTCNo);
        tvPatientPhone.setText(patientPhone);
        tvGender.setText(patientGender);
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
        lblPatientName.setBounds(90, 47, 80, 16);
        contentPane.add(lblPatientName);

        tvPatientName = new JTextField();
        tvPatientName.setBounds(181, 44, 116, 22);
        contentPane.add(tvPatientName);
        tvPatientName.setColumns(10);

        JLabel lblPatientSurname = new JLabel("Surname");
        lblPatientSurname.setBounds(90, 79, 80, 16);
        contentPane.add(lblPatientSurname);

        tvPatientSurname = new JTextField();
        tvPatientSurname.setColumns(10);
        tvPatientSurname.setBounds(181, 76, 116, 22);
        contentPane.add(tvPatientSurname);

//        JLabel lblPatientTC = new JLabel("ID");
//        lblPatientTC.setBounds(90, 111, 80, 16);
//        contentPane.add(lblPatientTC);

//        tvPatientTC = new JTextField();
//        tvPatientTC.setColumns(10);
//        tvPatientTC.setBounds(181, 108, 116, 22);
//        contentPane.add(tvPatientTC);

        JLabel lblPatientPhone = new JLabel("Phone");
        lblPatientPhone.setBounds(90, 143, 80, 16);
        contentPane.add(lblPatientPhone);

        tvPatientPhone = new JTextField();
        tvPatientPhone.setColumns(10);
        tvPatientPhone.setBounds(181, 140, 116, 22);
        contentPane.add(tvPatientPhone);


        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> {
            try {
                String query = "update patient set Name =?, Surname=?, Phone=?, Gender=?, Birth=?, Email=?, Address=?, RecordDate=? where Name = ? and Surname = ? and patientID = ?";
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

                preparedStatement.setString(9, PatientName);
                preparedStatement.setString(10, PatientSurname);
                preparedStatement.setString(11, PatientTCNo);
//                    preparedStatement.setString(12, PatientTCNo);

                preparedStatement.execute();
                JOptionPane.showMessageDialog(null, "Veri basarili bir sekilde degistirildi");

                preparedStatement.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        });
        btnSave.setBounds(182, 332, 97, 25);
        contentPane.add(btnSave);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(90, 175, 56, 16);
        contentPane.add(lblGender);

        tvGender = new JTextField();
        tvGender.setColumns(10);
        tvGender.setBounds(181, 172, 116, 22);
        contentPane.add(tvGender);

        JLabel lblBirthdate = new JLabel("Birth Date");
        lblBirthdate.setBounds(90, 207, 120, 16);
        contentPane.add(lblBirthdate);

        tvBirthDate = new JTextField();
        tvBirthDate.setColumns(10);
        tvBirthDate.setBounds(181, 204, 116, 22);
        contentPane.add(tvBirthDate);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(90, 239, 80, 16);
        contentPane.add(lblEmail);

        tvEmail = new JTextField();
        tvEmail.setColumns(10);
        tvEmail.setBounds(181, 236, 116, 22);
        contentPane.add(tvEmail);

        JLabel lblRecorddate = new JLabel("RecordDate");
        lblRecorddate.setBounds(90, 300, 120, 16);
        contentPane.add(lblRecorddate);

        tvRecordDate = new JTextField();
        tvRecordDate.setColumns(10);
        tvRecordDate.setBounds(181, 297, 116, 22);
        contentPane.add(tvRecordDate);

        JLabel lblAdres = new JLabel("Address");
        lblAdres.setBounds(90, 271, 120, 16);
        contentPane.add(lblAdres);

        tvAdres = new JTextField();
        tvAdres.setColumns(10);
        tvAdres.setBounds(181, 268, 116, 22);
        contentPane.add(tvAdres);
    }

}
