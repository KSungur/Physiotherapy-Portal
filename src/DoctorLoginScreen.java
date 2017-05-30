import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DoctorLoginScreen extends JPanel {

    Connection connection = null;

    private JTextField tvDoctorID;
    private JPasswordField tvDoctorPassword;

    public DoctorLoginScreen() {
        connection = MySqlConn.dbConnector();
        setSize(1500, 1000);
        setLayout(null);

        JLabel lblDoctorID = new JLabel("Doctor ID");
        lblDoctorID.setBounds(237, 298, 100, 16);
        add(lblDoctorID);

        JLabel lblDoctorPassword = new JLabel("Password");
        lblDoctorPassword.setBounds(237, 380, 100, 16);
        add(lblDoctorPassword);

        tvDoctorID = new JTextField();
        tvDoctorID.setBounds(360, 295, 175, 22);
        add(tvDoctorID);
        tvDoctorID.setColumns(10);

        JButton btnDoctorLogin = new JButton("Login");
        btnDoctorLogin.addActionListener(e -> {
            try {

                String query = "SELECT * FROM therapist WHERE employeeID=? and password=?";
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setString(1, tvDoctorID.getText());

                String generatedPassword = null;
                try {
                    // Create MessageDigest instance for MD5
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    //Add password bytes to digest
                    md.update(tvDoctorPassword.getText().getBytes());
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
                if (count != 1) {
                    JOptionPane.showMessageDialog(null, "Username or password incorrect");
                } else {
                    DoctorMainScreen.Doctorinput(tvDoctorID.getText());
                    removeAll();
                    add(new DoctorMainScreen());
                    repaint();
                    invalidate();
                    revalidate();
                }
                rs.close();
                pst.close();
            } catch (Exception e1) {
                JOptionPane.showConfirmDialog(null, e1);
            }
        });
        btnDoctorLogin.setBounds(403, 429, 97, 25);
        add(btnDoctorLogin);

        tvDoctorPassword = new JPasswordField();
        tvDoctorPassword.setBounds(360, 377, 175, 22);
        add(tvDoctorPassword);
    }
}
