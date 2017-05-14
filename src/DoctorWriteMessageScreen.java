import javax.swing.JPanel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.LinkedBlockingDeque;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class DoctorWriteMessageScreen extends JPanel {
	private Connection connection;
	private JTextField tvSubject;
	private JLabel lblPatientName;
	private JLabel lblPatientSurname;
	private JLabel lblPatientID;
	private JTextArea tvMessage;
	
	
	private static String PatientName;
	private static String PAtientID;
	

	
	static void DoctorMessageInput(String patientName, String patientTCNo) {
        PatientName = patientName;
        PAtientID = patientTCNo;
    }
	/**
	 * Create the panel.
	 */
	public DoctorWriteMessageScreen() {
		connection = MySqlConn.dbConnector();
		setLayout(null);
		setSize(600,405);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Mesaj Gonderilsin mi", "Confirm", JOptionPane.YES_NO_OPTION);
	            if (reply == JOptionPane.YES_OPTION) {
	                try {
	                    String query = "insert into Message (patientID, Name, Surname, Subject, Message, Date) values (?, ?, ?, ?, ?, ?)";

	                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	                    LocalDate localDate = LocalDate.now();

	                    PreparedStatement preparedStatement = connection.prepareStatement(query);
	                    preparedStatement.setString(1, lblPatientID.getText());
	                    preparedStatement.setString(2, lblPatientName.getText());
	                    preparedStatement.setString(3, lblPatientSurname.getText());
	                    preparedStatement.setString(4, tvSubject.getText());
	                    preparedStatement.setString(5, tvMessage.getText());
	                    preparedStatement.setString(6, dtf.format(localDate));

	                    preparedStatement.execute();
	                    JOptionPane.showMessageDialog(null, "Mesaj Basari ile iletildi");

	                    preparedStatement.close();

	                } catch (Exception e2) {
	                    JOptionPane.showMessageDialog(null, e2);
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "GOODBYE");
	            }
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
		fillPatientInfo();
	}
	
	void fillPatientInfo()
	{
		 try {
             String query = "SELECT * FROM patient WHERE patientID = ?";
             PreparedStatement pst = connection.prepareStatement(query);
             pst.setString(1, PAtientID);
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
