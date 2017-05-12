import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.SwingConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class DoctorMainScreen extends JPanel {
	Connection connection = null;
	private JTextField tvPatientName;
	private JTextField tvPatientID;
	private static JTable tablePatients;

	/**
	 * Create the panel.
	 */
	public DoctorMainScreen() {
		connection = sqliteConnection.dbConnector();
		setSize(1500,1000);
		setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1500, 26);
		add(menuBar);
		
		JMenu mnPatientRecords = new JMenu("Patient Records");
		menuBar.add(mnPatientRecords);
		
		JMenuItem mnitemNewRecord = new JMenuItem("New Record");
		mnPatientRecords.add(mnitemNewRecord);
		
		JMenuItem mnitemEdit = new JMenuItem("Edit");
		mnPatientRecords.add(mnitemEdit);
		
		JMenuItem mnitemDelete = new JMenuItem("Delete");
		mnPatientRecords.add(mnitemDelete);
		
		JMenuItem mnitemRefresh = new JMenuItem("Refresh");
		mnPatientRecords.add(mnitemRefresh);
		
		JMenuItem mnitemClose = new JMenuItem("Close");
		mnitemClose.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnitemClose);
		
		JButton btnNewRecord = new JButton("New Record");
		btnNewRecord.setBounds(0, 25, 200, 80);
		add(btnNewRecord);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(199, 25, 200, 80);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnDelete.setBounds(397, 25, 200, 80);
		add(btnDelete);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resfreshTable();
			}
		});
		btnRefresh.setBounds(596, 25, 200, 80);
		add(btnRefresh);
		
		JPanel pnlPatientSearch = new JPanel();
		pnlPatientSearch.setBorder(new TitledBorder(null, "Patient Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlPatientSearch.setBounds(0, 134, 796, 207);
		add(pnlPatientSearch);
		pnlPatientSearch.setLayout(null);
		
		JLabel lblNameofPatient = new JLabel("Name of Patient");
		lblNameofPatient.setBounds(28, 32, 109, 31);
		pnlPatientSearch.add(lblNameofPatient);
		
		JLabel lblPatientID = new JLabel("Patient ID");
		lblPatientID.setBounds(28, 76, 87, 34);
		pnlPatientSearch.add(lblPatientID);
		
		tvPatientName = new JTextField();
		tvPatientName.setBounds(186, 36, 116, 22);
		pnlPatientSearch.add(tvPatientName);
		tvPatientName.setColumns(10);
		
		tvPatientID = new JTextField();
		tvPatientID.setBounds(186, 82, 116, 22);
		pnlPatientSearch.add(tvPatientID);
		tvPatientID.setColumns(10);
		
		JButton btnSearchPatient = new JButton("Search");
		btnSearchPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from PAtients where ad like ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, "%"+tvPatientName.getText()+"%");
					ResultSet rs = pst.executeQuery();
					tablePatients.setModel(DbUtils.resultSetToTableModel(rs));
					
					pst.close();
					rs.close();
					
				} 
				catch (Exception e1) {
					
				}
			}
		});
		btnSearchPatient.setBounds(186, 169, 97, 25);
		pnlPatientSearch.add(btnSearchPatient);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Patients Records", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(0, 354, 1500, 646);
		add(scrollPane);
		
		tablePatients = new JTable();
		scrollPane.setViewportView(tablePatients);
		resfreshTable();
		tablePatients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) { //Tikladigimiz verinin textboxlara gelmesini sagliyor
				try {
					int row = tablePatients.getSelectedRow();
					String ID = (tablePatients.getModel().getValueAt(row, 1)).toString();
					
					String query = "select * from PAtients where ad = ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, ID);
					ResultSet rs = pst.executeQuery();
					while(rs.next()){
						tvPatientName.setText(rs.getString("ad"));
						tvPatientID.setText(rs.getString("tc"));
					}
					
					pst.close();
				} catch (Exception e) {
					
				}
			}
		});
		
	}

	void resfreshTable()
	{
		try {
			String query = "select * from PAtients order by ad";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tablePatients.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			
		}
	}//end of resfreshtable
	
}
