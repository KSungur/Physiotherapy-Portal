import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class DoctorReadMessageScreen extends JPanel {
    private Connection connection;
    private JTextField tvSubject;
    private JTextArea tvMessage;

    private static String PatientName;
    private static String PatientID;

    static void DoctorReadMessageInput(String patientName, String patientTCNo) {
        PatientName = patientName;
        PatientID = patientTCNo;
    }

    /**
     * Create the panel.
     */
    DoctorReadMessageScreen() {
        connection = MySqlConn.dbConnector();
        setLayout(null);
        setSize(600, 405);

        JLabel lblPatientName = new JLabel("Name");
        lblPatientName.setBounds(10, 11, 46, 14);
        add(lblPatientName);

        JLabel lblPatientSurname = new JLabel("Surname");
        lblPatientSurname.setToolTipText("Surname");
        lblPatientSurname.setBounds(10, 36, 46, 14);
        add(lblPatientSurname);

        JLabel lblPatientId = new JLabel("Patient ID");
        lblPatientId.setBounds(10, 61, 46, 14);
        add(lblPatientId);

        JLabel lblSubject = new JLabel("Subject");
        lblSubject.setBounds(10, 102, 46, 14);
        add(lblSubject);

        tvSubject = new JTextField();
        tvSubject.setColumns(10);
        tvSubject.setBounds(66, 99, 524, 20);
        add(tvSubject);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new TitledBorder(null, "Message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        scrollPane.setBounds(10, 127, 580, 235);
        add(scrollPane);

        tvMessage = new JTextArea();
        scrollPane.setViewportView(tvMessage);
        fillMessageText();
        JButton btnReply = new JButton("Reply");
        btnReply.addActionListener(e -> {
            removeAll();
            add(new DoctorWriteMessageScreen());
            repaint();
            invalidate();
            revalidate();
        });
        btnReply.setBounds(237, 371, 89, 23);
        add(btnReply);

    }

    private void fillMessageText() {
        try {
            String query = "SELECT patientID, Name, Surname " +
                    "FROM patient " +
                    "WHERE Name = ? AND patientID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, PatientName);
            preparedStatement.setString(2, PatientID);
            ResultSet rs = preparedStatement.executeQuery();

            tvSubject.setText(rs.getString("Subject"));
            tvMessage.setText(rs.getString("Message"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end of fillMessageText
}
