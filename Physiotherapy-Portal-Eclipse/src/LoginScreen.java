import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginScreen {

	private JFrame frame;
	private JPasswordField tv_Doctor_password;
	private JTextField tv_Doctor_username;
	private JPasswordField tv_Patient_password;
	private JTextField tv_Patient_username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	/**
	 * Create the application.
	 */
	public LoginScreen() {
		initialize();
		connection = sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1064, 695);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Panel panel_Doctor_login = new Panel();
		panel_Doctor_login.setBounds(117, 178, 310, 297);
		frame.getContentPane().add(panel_Doctor_login);
		panel_Doctor_login.setLayout(null);
		
		JLabel lbl_Doctor_username = new JLabel("Username :");
		lbl_Doctor_username.setBounds(26, 85, 73, 16);
		panel_Doctor_login.add(lbl_Doctor_username);
		
		JLabel lbl_Doctor_password = new JLabel("Password :");
		lbl_Doctor_password.setBounds(26, 159, 73, 16);
		panel_Doctor_login.add(lbl_Doctor_password);
		
		tv_Doctor_password = new JPasswordField();
		tv_Doctor_password.setBounds(111, 156, 157, 22);
		panel_Doctor_login.add(tv_Doctor_password);
		
		tv_Doctor_username = new JTextField();
		tv_Doctor_username.setBounds(111, 82, 157, 22);
		panel_Doctor_login.add(tv_Doctor_username);
		tv_Doctor_username.setColumns(10);
		
		JButton btn_Doctor_login = new JButton("Doctor Login");
		btn_Doctor_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query = "select * from Doctor_Account where doctor_username=? and doctor_password=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString (1 , tv_Doctor_username.getText());
					pst.setString(2, tv_Doctor_password.getText());
					
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next()){
						count++;
					}
					if(count == 1){
						JOptionPane.showMessageDialog(null, "Username and password correct");
						frame.dispose();
						Doctor_Screen doctor_Screen = new Doctor_Screen();
						doctor_Screen.setVisible(true);
					}
					else{
						JOptionPane.showMessageDialog(null, "Username or password incorrect");
					}
					
					rs.close();
					pst.close();
				}
				catch (Exception e_doctor) {
					JOptionPane.showConfirmDialog(null, e_doctor);
				}
			}
		});
		btn_Doctor_login.setBounds(111, 224, 157, 25);
		panel_Doctor_login.add(btn_Doctor_login);
		
		Panel panel_Patient_login = new Panel();
		panel_Patient_login.setLayout(null);
		panel_Patient_login.setBounds(596, 178, 310, 297);
		frame.getContentPane().add(panel_Patient_login);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(26, 85, 77, 16);
		panel_Patient_login.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(26, 159, 77, 16);
		panel_Patient_login.add(lblPassword);
		
		tv_Patient_password = new JPasswordField();
		tv_Patient_password.setBounds(107, 156, 157, 22);
		panel_Patient_login.add(tv_Patient_password);
		
		tv_Patient_username = new JTextField();
		tv_Patient_username.setColumns(10);
		tv_Patient_username.setBounds(107, 82, 157, 22);
		panel_Patient_login.add(tv_Patient_username);
		
		JButton btn_Patient_login = new JButton("Patient Login");
		btn_Patient_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query = "select * from Patient_Account where patient_username=? and patient_password=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString (1 , tv_Doctor_username.getText());
					pst.setString(2, tv_Doctor_password.getText());
					
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next()){
						count++;
					}
					if(count == 1){
						JOptionPane.showMessageDialog(null, "Username and password correct");
						frame.dispose();
						
					}
					else{
						JOptionPane.showMessageDialog(null, "Username or password incorrect");
					}
					
					rs.close();
					pst.close();
				}
				catch (Exception e_patient) {
					JOptionPane.showConfirmDialog(null, e_patient);
				}
			}
		});
		btn_Patient_login.setBounds(107, 225, 157, 25);
		panel_Patient_login.add(btn_Patient_login);
		
		JLabel lblFizyoterapistim = new JLabel("Fizyoterapistim");
		lblFizyoterapistim.setBounds(456, 81, 94, 16);
		frame.getContentPane().add(lblFizyoterapistim);
		
		JLabel lbl_Doctor_Login = new JLabel("Doctor Login");
		lbl_Doctor_Login.setBounds(172, 506, 120, 16);
		frame.getContentPane().add(lbl_Doctor_Login);
		
		JLabel lbl_Patient_Login = new JLabel("Patient Login");
		lbl_Patient_Login.setBounds(685, 506, 113, 16);
		frame.getContentPane().add(lbl_Patient_Login);
	}
}
