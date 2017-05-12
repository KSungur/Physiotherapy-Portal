import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DoctorLoginScreen extends JPanel {
	
	Connection connection = null;
	
	private JTextField tvDoctorID;
	private JPasswordField tvDoctorPassword;

	public DoctorLoginScreen() {
		connection = sqliteConnection.dbConnector();
		setSize(1500,1000);
		setLayout(null);
		
		JLabel lblDoctorID = new JLabel("Doctor ID");
		lblDoctorID.setBounds(237, 298, 56, 16);
		add(lblDoctorID);
		
		JLabel lblDoctorPassword = new JLabel("Password");
		lblDoctorPassword.setBounds(237, 380, 56, 16);
		add(lblDoctorPassword);
		
		tvDoctorID = new JTextField();
		tvDoctorID.setBounds(360, 295, 175, 22);
		add(tvDoctorID);
		tvDoctorID.setColumns(10);
		
		JCheckBox checkDoctor = new JCheckBox("Remember Me");
		checkDoctor.setBounds(359, 492, 113, 25);
		add(checkDoctor);
		
		JButton btnDoctorLogin = new JButton("Login");
		btnDoctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query = "select * from Doctor_Account where doctor_username=? and doctor_password=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString (1 , tvDoctorID.getText());
					pst.setString(2, tvDoctorPassword.getText());
					
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next()){
						count++;
					}
					if(count == 1){
						JOptionPane.showMessageDialog(null, "Username and password correct");
						removeAll();
						add(new DoctorMainScreen());
						repaint();
						invalidate();
						revalidate();
					}
					else{
						JOptionPane.showMessageDialog(null, "Username or password incorrect");
					}
					rs.close();
					pst.close();
				}
				catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, e1);
				}
			}
		});
		btnDoctorLogin.setBounds(403, 429, 97, 25);
		add(btnDoctorLogin);
		
		tvDoctorPassword = new JPasswordField();
		tvDoctorPassword.setBounds(360, 377, 175, 22);
		add(tvDoctorPassword);
	}
}
