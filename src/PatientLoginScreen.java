import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class PatientLoginScreen extends JPanel {

    Connection connection = null;

    private JTextField tvPatientID;
    private JPasswordField tvPatientPassword;

    /**
     * Create the panel.
     */
    public PatientLoginScreen() {
        connection = MySqlConn.dbConnector();
        setLayout(null);
        setSize(1500, 1000);

        JLabel lblPatientID = new JLabel("Patient ID");
        lblPatientID.setBounds(261, 196, 100, 16);
        add(lblPatientID);

        tvPatientID = new JTextField();
        tvPatientID.setColumns(10);
        tvPatientID.setBounds(384, 193, 175, 22);
        add(tvPatientID);

        JLabel lblPatientPassword = new JLabel("Password");
        lblPatientPassword.setBounds(261, 278, 100, 16);
        add(lblPatientPassword);

        tvPatientPassword = new JPasswordField();
        tvPatientPassword.setBounds(384, 275, 175, 22);
        add(tvPatientPassword);

        JButton btnPatientLogin = new JButton("Login");
        btnPatientLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String query = "select * from patient where patientID=? and password=?";
                    PreparedStatement pst = connection.prepareStatement(query);
                    pst.setString(1, tvPatientID.getText());
                    pst.setString(2, tvPatientPassword.getText());

                    ResultSet rs = pst.executeQuery();
                    int count = 0;
                    while (rs.next()) {
                        count++;
                    }
                    if (count == 1) {
                        JOptionPane.showMessageDialog(null, "Username and password correct");
                    } else {
                        JOptionPane.showMessageDialog(null, "Username or password incorrect");
                    }

                    rs.close();
                    pst.close();
                } catch (Exception e1) {
                    JOptionPane.showConfirmDialog(null, e1);
                }
            }
        });
        btnPatientLogin.setBounds(427, 327, 97, 25);
        add(btnPatientLogin);

        JCheckBox checkPatient = new JCheckBox("Remember Me");
        checkPatient.setBounds(383, 390, 150, 25);
        add(checkPatient);

    }

}
