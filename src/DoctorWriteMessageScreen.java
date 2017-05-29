import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DoctorWriteMessageScreen extends JPanel {
    private Connection connection;
    private JTextField tvSubject;
    private JLabel lblPatientName;
    private JLabel lblPatientSurname;
    private JLabel lblPatientID;
    private JTextArea tvMessage;


    private static String messageFrom;
    private static String PatientID;


    static void DoctorMessageInput(String messageFrom) {
        messageFrom = messageFrom;
    }

    /**
     * Create the panel.
     */
    DoctorWriteMessageScreen() {
        connection = MySqlConn.dbConnector();
        setLayout(null);
        setSize(600, 405);

        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(arg0 -> {
            int reply = JOptionPane.showConfirmDialog(null, "Sending message?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                try {
                    String query = "INSERT INTO messages (messageTo,, Subject, Content, Date, messageFrom) VALUES (?, ?, ?, ?, ?, ?)";

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate localDate = LocalDate.now();

                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, messageFrom);
                    preparedStatement.setString(2, lblPatientName.getText());
                    preparedStatement.setString(3, tvMessage.getText());
                    preparedStatement.setString(5, "Doctor");
                    preparedStatement.setString(4, dtf.format(localDate));

                    preparedStatement.execute();
                    JOptionPane.showMessageDialog(null, "Message sent");

                    preparedStatement.close();

                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, e2);
                }
            } else {
                JOptionPane.showMessageDialog(null, "GOODBYE");
            }
        });
        btnSend.setBounds(235, 371, 89, 23);
        add(btnSend);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new TitledBorder(null, "Message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        scrollPane.setBounds(10, 127, 580, 235);
        add(scrollPane);

        tvMessage = new JTextArea();
        scrollPane.setViewportView(tvMessage);

        JLabel lblSubject = new JLabel("Subject");
        lblSubject.setBounds(10, 102, 46, 14);
        add(lblSubject);

        tvSubject = new JTextField();
        tvSubject.setBounds(66, 99, 524, 20);
        add(tvSubject);
        tvSubject.setColumns(10);

        lblPatientName = new JLabel("Name");
        lblPatientName.setBounds(10, 11, 46, 14);
        add(lblPatientName);

        lblPatientSurname = new JLabel("Surname");
        lblPatientSurname.setBounds(10, 36, 46, 14);
        add(lblPatientSurname);

        lblPatientID = new JLabel("PatientID");
        lblPatientID.setBounds(10, 61, 46, 14);
        add(lblPatientID);
       // fillPatientInfo();
    }

    private void fillPatientInfo() {
        try {
            String query = "SELECT * FROM patient WHERE patientID = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, PatientID);
            ResultSet rs = pst.executeQuery();

            lblPatientName.setText(rs.getString("Name"));
            lblPatientSurname.setText(rs.getString("Surname"));
            lblPatientID.setText(rs.getString("patientID"));


            rs.close();
            pst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
