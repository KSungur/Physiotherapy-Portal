import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class PatientWriteMessage extends JFrame {
    private Connection connection;
    private JTextField tvSubject;
    private JTextArea tvMessage;
    public static String messageTo;
    private String message;
    private String subject;
    static void PatientWriteMessageInput(String msgTo) {
        messageTo = msgTo;
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PatientWriteMessage frame = new PatientWriteMessage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    PatientWriteMessage() {
        connection = MySqlConn.dbConnector();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 450, 341);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblMessageFrom = new JLabel("Message From");
        lblMessageFrom.setBounds(10, 11, 107, 14);
        contentPane.add(lblMessageFrom);
        lblMessageFrom.setText(messageTo);

        JLabel lblNewLabel_1 = new JLabel("Subject");
        lblNewLabel_1.setBounds(10, 36, 46, 14);
        contentPane.add(lblNewLabel_1);

        tvSubject = new JTextField();
        tvSubject.setBounds(66, 33, 358, 20);
        contentPane.add(tvSubject);
        tvSubject.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new TitledBorder(null, "Message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        scrollPane.setBounds(10, 69, 414, 181);
        contentPane.add(scrollPane);

        tvMessage = new JTextArea();
        scrollPane.setViewportView(tvMessage);

        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(e -> {
            message = tvMessage.getText();
            subject = tvSubject.getText();

            int reply = JOptionPane.showConfirmDialog(null, "Sending message?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                try {
                    String query = "INSERT INTO messages (messageTo, messageFrom, Subject, Content, Date) VALUES (?, ?, ?, ?, ?)";

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime localDate = LocalDateTime.now();

                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, messageTo);
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
        });
        btnSend.setBounds(166, 268, 89, 23);
        contentPane.add(btnSend);
    }
}
