import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.Color;

public class Doctor_Screen extends JFrame {

	private JPanel contentPane;
	private JTextField tv_Name_of_patient;
	private JTextField tv_Patient_id;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Doctor_Screen frame = new Doctor_Screen();
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
	public Doctor_Screen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1064, 695);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 232, 26);
		contentPane.add(menuBar);
		
		JMenu mMenu_Patient_records = new JMenu("Patient Records         ");
		menuBar.add(mMenu_Patient_records);
		
		JMenuItem mnýtmNewMenuItem_1 = new JMenuItem("New Record");
		mMenu_Patient_records.add(mnýtmNewMenuItem_1);
		
		JMenuItem mnýtmNewMenuItem_2 = new JMenuItem("Edit");
		mMenu_Patient_records.add(mnýtmNewMenuItem_2);
		
		JMenuItem mnýtmNewMenuItem = new JMenuItem("Delete");
		mMenu_Patient_records.add(mnýtmNewMenuItem);
		
		JMenuItem mnýtmNewMenuItem_3 = new JMenuItem("Reflesh");
		mMenu_Patient_records.add(mnýtmNewMenuItem_3);
		
		JMenuItem mitem_Close = new JMenuItem("Close                ");
		mitem_Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit Window", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
			}
		});
		menuBar.add(mitem_Close);
		
		JButton btn_New_record = new JButton("New Record");
		btn_New_record.setBounds(0, 39, 97, 25);
		contentPane.add(btn_New_record);
		
		JButton btn_Edit = new JButton("Edit");
		btn_Edit.setBounds(98, 39, 97, 25);
		contentPane.add(btn_Edit);
		
		JButton btn_Delete = new JButton("Delete");
		btn_Delete.setBounds(196, 39, 97, 25);
		contentPane.add(btn_Delete);
		
		JButton btn_Reflesh = new JButton("Reflesh");
		btn_Reflesh.setBounds(294, 39, 97, 25);
		contentPane.add(btn_Reflesh);
		
		JPanel panel_Patient_Search = new JPanel();
		panel_Patient_Search.setBounds(0, 98, 783, 108);
		contentPane.add(panel_Patient_Search);
		panel_Patient_Search.setLayout(null);
		
		JLabel lbl_Name_of_patient = new JLabel("Name of Patient");
		lbl_Name_of_patient.setBounds(12, 13, 91, 16);
		panel_Patient_Search.add(lbl_Name_of_patient);
		
		JLabel lbl_Patient_id = new JLabel("Patient ID");
		lbl_Patient_id.setBounds(12, 59, 91, 16);
		panel_Patient_Search.add(lbl_Patient_id);
		
		tv_Name_of_patient = new JTextField();
		tv_Name_of_patient.setBounds(136, 10, 116, 22);
		panel_Patient_Search.add(tv_Name_of_patient);
		tv_Name_of_patient.setColumns(10);
		
		tv_Patient_id = new JTextField();
		tv_Patient_id.setBounds(136, 56, 116, 22);
		panel_Patient_Search.add(tv_Patient_id);
		tv_Patient_id.setColumns(10);
		
		JLabel lbl_Patient_search = new JLabel("Patient Search");
		lbl_Patient_search.setBounds(10, 82, 116, 16);
		contentPane.add(lbl_Patient_search);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Patients List", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(4, 219, 1036, 423);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 18, 1030, 402);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
