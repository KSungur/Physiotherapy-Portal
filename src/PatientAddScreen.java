import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class PatientAddScreen extends JFrame {
	Connection connection = null;
	private JPanel contentPane;
	private JTextField tvPatientName;
	private JTextField tvPatientSurname;
	private JTextField tvPatientTC;
	private JTextField tvPatientPhone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientAddScreen frame = new PatientAddScreen();
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
	public PatientAddScreen() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewRecord = new JLabel("New Record");
		lblNewRecord.setBounds(173, 13, 94, 16);
		contentPane.add(lblNewRecord);
		
		JLabel lblPatientName = new JLabel("Name");
		lblPatientName.setBounds(83, 47, 56, 16);
		contentPane.add(lblPatientName);
		
		tvPatientName = new JTextField();
		tvPatientName.setBounds(170, 44, 116, 22);
		contentPane.add(tvPatientName);
		tvPatientName.setColumns(10);
		
		JLabel lblPatientSurname = new JLabel("Surname");
		lblPatientSurname.setBounds(83, 79, 56, 16);
		contentPane.add(lblPatientSurname);
		
		tvPatientSurname = new JTextField();
		tvPatientSurname.setColumns(10);
		tvPatientSurname.setBounds(170, 76, 116, 22);
		contentPane.add(tvPatientSurname);
		
		JLabel lblPatientTC = new JLabel("TC No");
		lblPatientTC.setBounds(83, 111, 56, 16);
		contentPane.add(lblPatientTC);
		
		tvPatientTC = new JTextField();
		tvPatientTC.setColumns(10);
		tvPatientTC.setBounds(170, 108, 116, 22);
		contentPane.add(tvPatientTC);
		
		JLabel lblPatientPhone = new JLabel("Phone");
		lblPatientPhone.setBounds(83, 143, 56, 16);
		contentPane.add(lblPatientPhone);
		
		tvPatientPhone = new JTextField();
		tvPatientPhone.setColumns(10);
		tvPatientPhone.setBounds(170, 140, 116, 22);
		contentPane.add(tvPatientPhone);
		
		JButton btnAddPatient = new JButton("Add Patient");
		btnAddPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Veri Eklensin mi", "Baslik", JOptionPane.YES_NO_OPTION);
				if( reply == JOptionPane.YES_OPTION)
				{
					try {
						String query = "insert into PAtients (tc, ad , soyad , tel) values (?, ?, ? , ?)";
						PreparedStatement preparedStatement = connection.prepareStatement(query);
						preparedStatement.setString(1, tvPatientName.getText());
						preparedStatement.setString(2, tvPatientSurname.getText());
						preparedStatement.setString(3, tvPatientTC.getText());
						preparedStatement.setString(4, tvPatientPhone.getText());
						
						preparedStatement.execute();
						JOptionPane.showMessageDialog(null, "Veriler Basari ile Eklendi");
						
						preparedStatement.close();
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "GOODBYE");
				}
			}
		});
		btnAddPatient.setBounds(170, 186, 97, 25);
		contentPane.add(btnAddPatient);
	}
}
