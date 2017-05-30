import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
        btnPatientLogin.addActionListener(e -> {
            try {
                String query = "SELECT * FROM patient WHERE patientID=? AND password=?";
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setString(1, tvPatientID.getText());

                String generatedPassword = null;
                try {
                    // Create MessageDigest instance for MD5
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    //Add password bytes to digest
                    md.update(tvPatientPassword.getText().getBytes());
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
                } catch (NoSuchAlgorithmException e3) {
                    e3.printStackTrace();
                }

                pst.setString(2, generatedPassword);

                ResultSet rs = pst.executeQuery();
                int count = 0;
                while (rs.next()) {
                    count++;
                }
                if (count == 1) {
                    PatientMainScreen.Patientinput(tvPatientID.getText());
                    JOptionPane.showMessageDialog(null, "Username and password correct");
                    removeAll();
                    add(new PatientMainScreen());
                    repaint();
                    invalidate();
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(null, "Username or password incorrect");
                }

                rs.close();
                pst.close();
            } catch (Exception e1) {
                JOptionPane.showConfirmDialog(null, e1);
            }
        });
        btnPatientLogin.setBounds(370, 327, 97, 25);
        add(btnPatientLogin);


        JButton btnPatientSignup = new JButton("Sign up");
        btnPatientSignup.addActionListener(e -> {
            PatientSignupScreen patientSignupScreen = new PatientSignupScreen();
            patientSignupScreen.setVisible(true);
        });
        btnPatientSignup.setBounds(480, 327, 97, 25);
        add(btnPatientSignup);


        JCheckBox checkPatient = new JCheckBox("Remember Me");
        checkPatient.setBounds(383, 390, 150, 25);
        add(checkPatient);

    }

}
