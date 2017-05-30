import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DoctorReadMessageJFrame extends JFrame {

	private Connection connection;
	private JLabel lblSubject;
	private JTextArea tvMessage;
	private JLabel lblMessageFrom;
	
	private static String message_From;
    private static String date;

    static void DoctorReadMessageInput(String messageFrom, String msgDate) {
        message_From = messageFrom;
        date = msgDate;
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                DoctorReadMessageJFrame frame = new DoctorReadMessageJFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 */
	DoctorReadMessageJFrame() {
		connection = MySqlConn.dbConnector();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 338);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMessageFrom = new JLabel("Message From");
		lblMessageFrom.setBounds(10, 11, 91, 14);
		contentPane.add(lblMessageFrom);
		
		lblSubject = new JLabel("Subject");
		lblSubject.setBounds(10, 46, 46, 14);
		contentPane.add(lblSubject);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "Message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(10, 71, 414, 179);
		contentPane.add(scrollPane);
		
		tvMessage = new JTextArea();
		scrollPane.setViewportView(tvMessage);
		
		JButton btnReply = new JButton("Reply");
		btnReply.addActionListener(arg0 -> {
            DoctorWriteMessageJFrame doctorWriteMessageJFrame = new DoctorWriteMessageJFrame();
            doctorWriteMessageJFrame.setVisible(true);
            DoctorWriteMessageJFrame.DoctorWriteMessageInput(message_From);
        });
		btnReply.setBounds(169, 265, 89, 23);
		contentPane.add(btnReply);
		fillMessageText();
	}
	
	private void fillMessageText() {
        try {
            String query = "SELECT messageFrom, Subject, Content " +
                    "FROM messages " +
                    "WHERE messageFrom = ? and Date = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, message_From);
            preparedStatement.setString(2, date);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                String subject =rs.getString("Subject");
                String content = rs.getString("Content");
                String messageFrom = rs.getString("messageFrom");
                lblSubject.setText(subject);
                tvMessage.setText(content);
                lblMessageFrom.setText(messageFrom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end of fillMessageText
}
