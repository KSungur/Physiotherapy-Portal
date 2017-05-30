import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PatientMessageScreen extends JFrame {

	private Connection connection = null;
	private JPanel contentPane;
	private JTextField tvDoctorID;
	private JTextField tvSubject;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientMessageScreen frame = new PatientMessageScreen();
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
	public PatientMessageScreen() {
		connection = MySqlConn.dbConnector();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 600, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDoctorID = new JLabel("Doctor ID :");
		lblDoctorID.setBounds(12, 13, 100, 16);
		contentPane.add(lblDoctorID);

		tvDoctorID = new JTextField();
		tvDoctorID.setBounds(96, 10, 116, 22);
		contentPane.add(tvDoctorID);
		tvDoctorID.setColumns(10);

		JLabel lblNewLabel = new JLabel("Subject");
		lblNewLabel.setBounds(12, 60, 56, 16);
		contentPane.add(lblNewLabel);

		tvSubject = new JTextField();
		tvSubject.setBounds(96, 57, 474, 22);
		contentPane.add(tvSubject);
		tvSubject.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "Message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(12, 94, 558, 221);
		contentPane.add(scrollPane);

		JTextArea tvMessage = new JTextArea();
		scrollPane.setViewportView(tvMessage);

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int reply = JOptionPane.showConfirmDialog(null, "Sending message?", "Confirm", JOptionPane.YES_NO_OPTION);
		            if (reply == JOptionPane.YES_OPTION) {
		                try {
		                    String query = "INSERT INTO messages (messageTo, messageFrom, Subject, Content, Date) VALUES (?, ?, ?, ?, ?)";

		                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		                    LocalDateTime localDate = LocalDateTime.now();

		                    PreparedStatement preparedStatement = connection.prepareStatement(query);
		                    preparedStatement.setString(1, tvDoctorID.getText());
                            preparedStatement.setString(2, PatientMainScreen.PatientName);
		                    preparedStatement.setString(3, tvSubject.getText());
		                    preparedStatement.setString(4, tvMessage.getText());
		                    preparedStatement.setString(5, dtf.format(localDate));

		                    preparedStatement.execute();
		                    JOptionPane.showMessageDialog(null, "Message sent");

		                    preparedStatement.close();

		                } catch (Exception e2) {
		                    JOptionPane.showMessageDialog(null, e2);
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "GOODBYE");
		            }
			}
		});
		btnSend.setBounds(250, 320, 97, 25);
		contentPane.add(btnSend);
	}
}
