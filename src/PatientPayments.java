import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientPayments extends JFrame {

    private Connection connection = null;
    private JTable tablepayment;
    private JLabel lblPatientName;
    private JLabel lblPatientSurname;
    private JLabel lblPatientTCNo;
    private JLabel lblPatientPhone;
    private JLabel lblPatientGender;
    private JLabel lblPatientBirthday;
    private JLabel lblPatientEmail;
    private JLabel lblPatientAdres;

    private static String PatientName;
    private static String PatientTCNo;

    static void Patientinput(String name, String id) {
        PatientName = name;
        PatientTCNo = id;

        System.out.println(PatientName + " " + PatientTCNo);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PatientPayments frame = new PatientPayments();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    PatientPayments() {
        connection = MySqlConn.dbConnector();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 512, 436);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new TitledBorder(null, "Payments", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        scrollPane.setBounds(12, 173, 470, 203);
        contentPane.add(scrollPane);

        tablepayment = new JTable();
        scrollPane.setViewportView(tablepayment);

        JLabel Name = new JLabel("Name");
        Name.setBounds(10, 42, 56, 16);
        contentPane.add(Name);

        lblPatientName = new JLabel("");
        lblPatientName.setBounds(90, 42, 120, 16);
        contentPane.add(lblPatientName);

        JLabel Surname = new JLabel("Surname");
        Surname.setBounds(10, 71, 120, 16);
        contentPane.add(Surname);

        lblPatientSurname = new JLabel("");
        lblPatientSurname.setBounds(90, 71, 120, 16);
        contentPane.add(lblPatientSurname);

        JLabel lblTcNo = new JLabel("Patient ID");
        lblTcNo.setBounds(10, 100, 150, 16);
        contentPane.add(lblTcNo);

        lblPatientTCNo = new JLabel("");
        lblPatientTCNo.setBounds(90, 100, 200, 16);
        contentPane.add(lblPatientTCNo);

        JLabel lblPatientPayment = new JLabel("Patient's Payments");
        lblPatientPayment.setHorizontalAlignment(SwingConstants.CENTER);
        lblPatientPayment.setBounds(0, 13, 494, 16);
        contentPane.add(lblPatientPayment);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(252, 42, 56, 16);
        contentPane.add(lblGender);

        lblPatientGender = new JLabel("");
        lblPatientGender.setBounds(332, 42, 80, 16);
        contentPane.add(lblPatientGender);

        JLabel lblBirthday = new JLabel("Birthday");
        lblBirthday.setBounds(252, 71, 120, 16);
        contentPane.add(lblBirthday);

        lblPatientBirthday = new JLabel("");
        lblPatientBirthday.setBounds(332, 71, 150, 16);
        contentPane.add(lblPatientBirthday);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(252, 100, 56, 16);
        contentPane.add(lblEmail);

        lblPatientEmail = new JLabel("");
        lblPatientEmail.setBounds(332, 100, 200, 16);
        contentPane.add(lblPatientEmail);

        lblPatientPhone = new JLabel("");
        lblPatientPhone.setBounds(90, 129, 200, 16);
        contentPane.add(lblPatientPhone);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(10, 129, 56, 16);
        contentPane.add(lblPhone);

        JLabel lblAdres = new JLabel("Address");
        lblAdres.setBounds(252, 129, 120, 16);
        contentPane.add(lblAdres);

        lblPatientAdres = new JLabel("");
        lblPatientAdres.setBounds(332, 129, 200, 16);
        contentPane.add(lblPatientAdres);

        PatientInformation();
        fillPaymentTable();
    }

    private void PatientInformation() {
        String query = "select * from patient where Name = ? and patientID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, PatientName);
            preparedStatement.setString(2, PatientTCNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lblPatientName.setText(resultSet.getString("Name"));
                lblPatientSurname.setText(resultSet.getString("Surname"));
                lblPatientTCNo.setText(resultSet.getString("patientID"));
                lblPatientPhone.setText(resultSet.getString("Phone"));
                lblPatientGender.setText(resultSet.getString("Gender"));
                lblPatientBirthday.setText(resultSet.getString("Birth"));
                lblPatientEmail.setText(resultSet.getString("Email"));
                lblPatientAdres.setText(resultSet.getString("Address"));
            }

            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillPaymentTable() {
        try {
            String query = "SELECT * FROM patientPayment WHERE patientID = ? ORDER BY Date";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, PatientTCNo);
            ResultSet rs = preparedStatement.executeQuery();
            tablepayment.setModel(DbUtils.resultSetToTableModel(rs));

            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
