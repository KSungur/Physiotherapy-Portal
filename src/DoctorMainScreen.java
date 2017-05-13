import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.SwingConstants;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    private JTable tablePatients;
    private JTable tableAppointment;

    private static String DoctorName;

    public static void Doctorinput(String doctorName) {
        DoctorName = doctorName;
        System.out.println(DoctorName);
    }

    /**
     * Create the panel.
     */
    public DoctorMainScreen() {
        connection = MySqlConn.dbConnector();
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        setSize(width, height);
        setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 1500, 26);
        add(menuBar);

        JMenu mnPatientRecords = new JMenu("Patient Records");
        menuBar.add(mnPatientRecords);

        JMenuItem mnitemNewRecord = new JMenuItem("New Record");
        mnitemNewRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PatientAddScreen patientAddScreen = new PatientAddScreen();
                patientAddScreen.setVisible(true);
            }
        });
        mnPatientRecords.add(mnitemNewRecord);

        JMenuItem mnitemEdit = new JMenuItem("Edit");
        mnPatientRecords.add(mnitemEdit);

        JMenuItem mnitemDelete = new JMenuItem("Delete");
        mnPatientRecords.add(mnitemDelete);

        JMenuItem mnitemRefresh = new JMenuItem("Refresh");
        mnitemRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resfreshTable();
            }
        });
        mnPatientRecords.add(mnitemRefresh);

        JMenuItem mnitemClose = new JMenuItem("Close");
        mnitemClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnitemClose.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mnitemClose);

        JButton btnNewRecord = new JButton("New Record");
        btnNewRecord.setBounds(0, 25, 200, 40);
        btnNewRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PatientAddScreen patientAddScreen = new PatientAddScreen();
                patientAddScreen.setVisible(true);
            }
        });
        add(btnNewRecord);

        JButton btnEdit = new JButton("Edit");
        btnEdit.setBounds(199, 25, 200, 40);
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PatientEditScreen patientEditScreen = new PatientEditScreen();
                int row = tablePatients.getSelectedRow();
                String PatientName = (tablePatients.getModel().getValueAt(row, 0)).toString();
                String PatientSurname = (tablePatients.getModel().getValueAt(row, 1)).toString();
                String PatientTCNo = (tablePatients.getModel().getValueAt(row, 2)).toString();
                String PatientPhone = (tablePatients.getModel().getValueAt(row, 3)).toString();
                String PatientGender = (tablePatients.getModel().getValueAt(row, 4)).toString();
                String PatientBirth = (tablePatients.getModel().getValueAt(row, 5)).toString();
                String PatientEmail = (tablePatients.getModel().getValueAt(row, 6)).toString();
                String PatientAdres = (tablePatients.getModel().getValueAt(row, 7)).toString();
                String PatientRecordDate = (tablePatients.getModel().getValueAt(row, 8)).toString();

                patientEditScreen.input(PatientName, PatientSurname, PatientTCNo, PatientPhone, PatientGender, PatientBirth, PatientEmail, PatientAdres, PatientRecordDate);
                patientEditScreen.setVisible(true);
                tvPatientName.setText("");
                tvPatientID.setText("");

            }
        });
        add(btnEdit);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(397, 25, 200, 40);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Veri Silinsin mi?", "Baslik", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {

                    try {
                        String query = "delete from patient where id = ? and tc = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, tvPatientName.getText());
                        preparedStatement.setString(2, tvPatientID.getText());
                        preparedStatement.execute();
                        resfreshTable();
                        tvPatientID.setText("");
                        tvPatientName.setText("");
                        JOptionPane.showMessageDialog(null, "Veri basarili bir sekilde silindi");
                        preparedStatement.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }

//                else {
//                    JOptionPane.showMessageDialog(null, "GOODBYE");
//                }
            }
        });
        add(btnDelete);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(596, 25, 200, 40);
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resfreshTable();
                AppointmentTableRefresh();
                tvPatientID.setText("");
                tvPatientName.setText("");
            }
        });
        add(btnRefresh);

        JPanel pnlPatientSearch = new JPanel();
        pnlPatientSearch.setBounds(0, 134, 796, 207);
        pnlPatientSearch.setBorder(new TitledBorder(null, "Patient Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        add(pnlPatientSearch);
        pnlPatientSearch.setLayout(null);

        JLabel lblNameofPatient = new JLabel("Name of Patient");
        lblNameofPatient.setBounds(28, 32, 150, 31);
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
                    String query = "select ad, soyad, tc, tel, gender, birth, email, adres, recorddate from Patients where ad like ?";
                    PreparedStatement pst = connection.prepareStatement(query);
                    pst.setString(1, "%" + tvPatientName.getText() + "%");
                    ResultSet rs = pst.executeQuery();
                    tablePatients.setModel(DbUtils.resultSetToTableModel(rs));

                    pst.close();
                    rs.close();

                } catch (Exception e1) {

                }
            }
        });
        btnSearchPatient.setBounds(186, 169, 97, 25);
        pnlPatientSearch.add(btnSearchPatient);

        JButton btnShowPatientPayments = new JButton("Patient's Payments");
        btnShowPatientPayments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (tvPatientID.getText().length() == 0 && tvPatientName.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Textboxlar bos");
                } else {
                    PatientPayments.Patientinput(tvPatientName.getText(), tvPatientID.getText());
                    PatientPayments patientPayments = new PatientPayments();
                    patientPayments.setVisible(true);
                }

            }
        });
        btnShowPatientPayments.setBounds(596, 7, 200, 40);
        pnlPatientSearch.add(btnShowPatientPayments);

        JButton btnSentExercises = new JButton("Sent Exercises");
        btnSentExercises.setBounds(596, 114, 200, 40);
        pnlPatientSearch.add(btnSentExercises);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 354, 1495, 600);
        add(scrollPane);

        tablePatients = new JTable();
        scrollPane.setViewportView(tablePatients);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setViewportBorder(new TitledBorder(null, "Appointments", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        scrollPane_1.setBounds(808, 25, 687, 311);
        add(scrollPane_1);

        tableAppointment = new JTable();
        scrollPane_1.setViewportView(tableAppointment);
        resfreshTable();
        AppointmentTableRefresh();

        tablePatients.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) { //Tikladigimiz verinin textboxlara gelmesini sagliyor
                try {
                    int row = tablePatients.getSelectedRow();
                    String ID = (tablePatients.getModel().getValueAt(row, 0)).toString();
                    System.out.println(ID);
                    String query = "select * from Patients where ad = ?";
                    PreparedStatement pst = connection.prepareStatement(query);
                    pst.setString(1, ID);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        tvPatientName.setText(rs.getString("ad"));
                        tvPatientID.setText(rs.getString("tc"));
                    }

                    pst.close();
                } catch (Exception e) {

                }
            }
        });


    }

    public void AppointmentTableRefresh() {
        try {
            String query = "select appointment_id, patient_id, appointment_date from Appointment order by appointment_id";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            tableAppointment.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {

        }
    }

    public void resfreshTable() {
        try {
            String query = "select ad, soyad, tc, tel, gender, birth, email, adres, recorddate from Patients order by ad";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            tablePatients.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {

        }
    }//end of resfreshtable
}
