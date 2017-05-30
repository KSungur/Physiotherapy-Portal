import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class PatientReadMessage extends JFrame {
    private Connection connection = null;
    private JTable tableMessages;
    public String message_From = "";
    private String date = "";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PatientReadMessage frame = new PatientReadMessage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    PatientReadMessage() {
        connection = MySqlConn.dbConnector();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 740, 405);
        setTitle("Messages");
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new TitledBorder(null, "Messages", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        scrollPane.setBounds(10, 11, 725, 310);
        contentPane.add(scrollPane);

        tableMessages = new JTable();
        scrollPane.setViewportView(tableMessages);
        resfreshTable();

        tableMessages.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) { //Tikladigimiz rowun verisini aliyoruz
                try {
                    int row = tableMessages.getSelectedRow();
                    String ID = (tableMessages.getModel().getValueAt(row, 0)).toString();
                    String dt = (tableMessages.getModel().getValueAt(row, 3)).toString();
                    System.out.println(ID);
                    String query = "SELECT * FROM messages WHERE messageFrom = ? and DATE = ?";
                    PreparedStatement pst = connection.prepareStatement(query);
                    pst.setString(1, ID);
                    pst.setString(2, dt);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        message_From = rs.getString("messageFrom");
                        date = rs.getString("Date");
                        PatientWriteMessage.PatientWriteMessageInput(rs.getString("messageFrom"));
                        DoctorReadMessageJFrame.DoctorReadMessageInput(rs.getString("messageFrom"), date);
                        date = rs.getString("Date");
                        DoctorWriteMessageJFrame.DoctorWriteMessageInput(date);
                    }

                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JButton btnShow = new JButton("Show");
        btnShow.addActionListener(arg0 -> {
            if (Objects.equals(message_From, "")) {
                JOptionPane.showMessageDialog(null, "Mesaj Secilmedi");
            } else {
                DoctorReadMessageJFrame doctorReadMessageJFrame = new DoctorReadMessageJFrame();
                doctorReadMessageJFrame.setVisible(true);
                DoctorReadMessageJFrame.DoctorReadMessageInput(message_From, date);
            }
        });
        btnShow.setBounds(184, 332, 89, 23);
        contentPane.add(btnShow);

        JButton btnReply = new JButton("Reply");
        btnReply.addActionListener(arg0 -> {
            if (Objects.equals(message_From, "")) {
                JOptionPane.showMessageDialog(null, "Mesaj Secilmedi");
            } else {
                PatientWriteMessage patientWriteMessage = new PatientWriteMessage();
                patientWriteMessage.setVisible(true);
            }
        });
        btnReply.setBounds(294, 332, 89, 23);
        contentPane.add(btnReply);
    }

    private void resfreshTable() {
        try {
            String query = "SELECT messageFrom, Subject, Content, Date " +
                    "FROM messages " +
                    "WHERE messageTo = " + PatientMainScreen.PatientName + " " +
                    "ORDER BY Date";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            tableMessages.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end of resfreshtable
}


