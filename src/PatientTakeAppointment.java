import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PatientTakeAppointment extends JFrame {

    private JTextField tvDoctorID;
    private JTextField tvDate;
    private Connection connection = null;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PatientTakeAppointment frame = new PatientTakeAppointment();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    PatientTakeAppointment() {
        connection = MySqlConn.dbConnector();

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblDoctorID = new JLabel("Doctor ID:");
        lblDoctorID.setBounds(12, 68, 80, 16);
        contentPane.add(lblDoctorID);


        JLabel lblAppointmentForm = new JLabel("Appointment Form");
        lblAppointmentForm.setBounds(156, 13, 147, 16);
        contentPane.add(lblAppointmentForm);

        tvDoctorID = new JTextField();
        tvDoctorID.setBounds(104, 65, 200, 22);
        contentPane.add(tvDoctorID);
        tvDoctorID.setColumns(10);

        JLabel lblDate = new JLabel("Date");
        lblDate.setBounds(12, 107, 56, 16);
        contentPane.add(lblDate);

        tvDate = new JTextField();
        tvDate.setBounds(103, 100, 200, 22);
        contentPane.add(tvDate);
        tvDate.setColumns(10);

        JButton btnNewButton = new JButton("Take Appointment");
        btnNewButton.addActionListener(e -> {
            try {
                String query = "INSERT INTO appointment (employeeID,patientID , appointmentDate) VALUES (?, ?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, tvDoctorID.getText());
                preparedStatement.setString(2, PatientMainScreen.PatientName);
                preparedStatement.setString(3, tvDate.getText());

                preparedStatement.execute();
                JOptionPane.showMessageDialog(null, "Appointment Taken Successfully.");
                preparedStatement.close();

            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, e2);
            }
        });
        btnNewButton.setBounds(156, 200, 200, 25);
        contentPane.add(btnNewButton);
    }
}
