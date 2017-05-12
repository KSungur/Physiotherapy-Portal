import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PatientEditScreen extends JFrame {
	Connection connection = null;
	private JPanel contentPane;
	private JTextField tvPatientName;
	private JTextField tvPatientSurname;
	private JTextField tvPatientTC;
	private JTextField tvPatientPhone;
	
	private String PatientName;
	private String PatientSurname;
	

	
	public void input(String PatientName , String PatientSurname, String PatientTCNo, String PatientPhone){
		tvPatientName.setText(PatientName);
		this.PatientName = PatientName;
		this.PatientSurname = PatientSurname;
		tvPatientSurname.setText(PatientSurname);
		tvPatientTC.setText(PatientTCNo);
		tvPatientPhone.setText(PatientPhone);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientEditScreen frame = new PatientEditScreen();
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
	public PatientEditScreen() {
		
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPatientEditScreen = new JLabel("Patient Edit");
		lblPatientEditScreen.setBounds(182, 13, 56, 16);
		contentPane.add(lblPatientEditScreen);
		
		JLabel lblPatientName = new JLabel("Name");
		lblPatientName.setBounds(90, 47, 56, 16);
		contentPane.add(lblPatientName);
		
		tvPatientName = new JTextField();
		tvPatientName.setBounds(181, 44, 116, 22);
		contentPane.add(tvPatientName);
		tvPatientName.setColumns(10);
		
		JLabel lblPatientSurname = new JLabel("Surname");
		lblPatientSurname.setBounds(90, 79, 56, 16);
		contentPane.add(lblPatientSurname);
		
		tvPatientSurname = new JTextField();
		tvPatientSurname.setColumns(10);
		tvPatientSurname.setBounds(181, 76, 116, 22);
		contentPane.add(tvPatientSurname);
		
		JLabel lblPatientTC = new JLabel("TC No");
		lblPatientTC.setBounds(90, 111, 56, 16);
		contentPane.add(lblPatientTC);
		
		tvPatientTC = new JTextField();
		tvPatientTC.setColumns(10);
		tvPatientTC.setBounds(181, 108, 116, 22);
		contentPane.add(tvPatientTC);
		
		JLabel lblPatientPhone = new JLabel("Phone");
		lblPatientPhone.setBounds(90, 143, 56, 16);
		contentPane.add(lblPatientPhone);
		
		tvPatientPhone = new JTextField();
		tvPatientPhone.setColumns(10);
		tvPatientPhone.setBounds(181, 140, 116, 22);
		contentPane.add(tvPatientPhone);
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "update PAtients set ad=?, soyad=?, tc=?, tel=? where ad = ? and soyad = ?";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.setString(1, tvPatientName.getText());
					preparedStatement.setString(2, tvPatientSurname.getText());
					preparedStatement.setString(3, tvPatientTC.getText());
					preparedStatement.setString(4, tvPatientPhone.getText());
					
					preparedStatement.setString(5, PatientName);
					preparedStatement.setString(6, PatientSurname);
					
					preparedStatement.execute();
					JOptionPane.showMessageDialog(null, "Veri basarili bir sekilde degistirildi");
					
					preparedStatement.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnSave.setBounds(182, 184, 97, 25);
		contentPane.add(btnSave);
	}

}
