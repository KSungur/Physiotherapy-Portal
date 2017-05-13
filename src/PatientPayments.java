import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PatientPayments extends JFrame {

    Connection connection = null;
    private JPanel contentPane;
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

    public static void Patientinput(String name, String tcno) {
        PatientName = name;
        PatientTCNo = tcno;

        System.out.println(PatientName + " " + PatientTCNo);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PatientPayments frame = new PatientPayments();
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
    public PatientPayments() {
        connection = MySqlConn.dbConnector();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 512, 436);
        contentPane = new JPanel();
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
        lblPatientName.setBounds(90, 42, 56, 16);
        contentPane.add(lblPatientName);

        JLabel Surname = new JLabel("Surname");
        Surname.setBounds(10, 71, 56, 16);
        contentPane.add(Surname);

        lblPatientSurname = new JLabel("");
        lblPatientSurname.setBounds(90, 71, 56, 16);
        contentPane.add(lblPatientSurname);

        JLabel lblTcNo = new JLabel("TC No");
        lblTcNo.setBounds(10, 100, 56, 16);
        contentPane.add(lblTcNo);

        lblPatientTCNo = new JLabel("");
        lblPatientTCNo.setBounds(90, 100, 56, 16);
        contentPane.add(lblPatientTCNo);

        JLabel lblPatientPayment = new JLabel("Patient's Payment");
        lblPatientPayment.setHorizontalAlignment(SwingConstants.CENTER);
        lblPatientPayment.setBounds(0, 13, 494, 16);
        contentPane.add(lblPatientPayment);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(252, 42, 56, 16);
        contentPane.add(lblGender);

        lblPatientGender = new JLabel("");
        lblPatientGender.setBounds(332, 42, 56, 16);
        contentPane.add(lblPatientGender);

        JLabel lblBirthday = new JLabel("Birthday");
        lblBirthday.setBounds(252, 71, 56, 16);
        contentPane.add(lblBirthday);

        lblPatientBirthday = new JLabel("");
        lblPatientBirthday.setBounds(332, 71, 56, 16);
        contentPane.add(lblPatientBirthday);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(252, 100, 56, 16);
        contentPane.add(lblEmail);

        lblPatientEmail = new JLabel("");
        lblPatientEmail.setBounds(332, 100, 56, 16);
        contentPane.add(lblPatientEmail);

        lblPatientPhone = new JLabel("");
        lblPatientPhone.setBounds(90, 129, 56, 16);
        contentPane.add(lblPatientPhone);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(10, 129, 56, 16);
        contentPane.add(lblPhone);

        JLabel lblAdres = new JLabel("Adres");
        lblAdres.setBounds(252, 129, 56, 16);
        contentPane.add(lblAdres);

        lblPatientAdres = new JLabel("");
        lblPatientAdres.setBounds(332, 129, 56, 16);
        contentPane.add(lblPatientAdres);

        PatientInformation();
        fillPaymentTable();
    }

    public void PatientInformation() {
        String query = "select * from Patients where ad = ? and tc = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, PatientName);
            preparedStatement.setString(2, PatientTCNo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                lblPatientName.setText(resultSet.getString("ad"));
                lblPatientSurname.setText(resultSet.getString("soyad"));
                lblPatientTCNo.setText(resultSet.getString("tc"));
                lblPatientPhone.setText(resultSet.getString("tel"));
                lblPatientGender.setText(resultSet.getString("gender"));
                lblPatientBirthday.setText(resultSet.getString("birth"));
                lblPatientEmail.setText(resultSet.getString("email"));
                lblPatientAdres.setText(resultSet.getString("adres"));
            }

            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillPaymentTable() {
        try {
            String query = "select * from Patients_Payment where patient_id = ? order by payment_date";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, PatientTCNo);
            ResultSet rs = preparedStatement.executeQuery();
            tablepayment.setModel(DbUtils.resultSetToTableModel(rs));

            preparedStatement.close();
        } catch (Exception e) {

        }
    }
}
