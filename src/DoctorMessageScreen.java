import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import org.omg.IOP.TAG_MULTIPLE_COMPONENTS;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class DoctorMessageScreen extends JFrame {
	private Connection connection = null;
	private JPanel contentPane;
	private JTable tableMessages;
	private String PatientName = "";
	private String PatientTCNo = "";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorMessageScreen frame = new DoctorMessageScreen();
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
	public DoctorMessageScreen() {
		connection = MySqlConn.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 405);
		setTitle("Messages");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "Messages", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(10, 11, 564, 310);
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
                    System.out.println(ID);
                    String query = "SELECT * FROM patient WHERE patientID = ?";
                    PreparedStatement pst = connection.prepareStatement(query);
                    pst.setString(1, ID);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                    	PatientName = rs.getString("Name");
                    	PatientTCNo = rs.getString("patientID");
                        DoctorWriteMessageScreen.DoctorMessageInput(rs.getString("Name"),rs.getString("patientID"));
                        DoctorReadMessageScreen.DoctorReadMessageInput(rs.getString("Name"),rs.getString("patientID"));
                    }

                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
		
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(PatientName == "" && PatientTCNo == "")
				{
					JOptionPane.showMessageDialog(null, "Hasta Secilmedi");
				}
				else 
				{
					removeAll();
	                getContentPane().add(new DoctorReadMessageScreen());
	                repaint();
	                invalidate();
	                revalidate();
				}
			}
		});
		btnShow.setBounds(184, 332, 89, 23);
		contentPane.add(btnShow);
		
		JButton btnReply = new JButton("Reply");
		btnReply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(PatientName == "" && PatientTCNo == "")
				{
					JOptionPane.showMessageDialog(null, "Hasta Secilmedi");
				}
				else 
				{
					removeAll();
	                getContentPane().add(new DoctorWriteMessageScreen());
	                repaint();
	                invalidate();
	                revalidate();
				}
			}
		});
		btnReply.setBounds(294, 332, 89, 23);
		contentPane.add(btnReply);
	}
	
	private void resfreshTable() {
        try {
            String query = "SELECT patientID, Name, Surname, Message" +
                    "FROM patient " +
                    "ORDER BY Date";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            tableMessages.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end of resfreshtable
}
