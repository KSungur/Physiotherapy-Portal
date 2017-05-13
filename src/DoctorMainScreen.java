import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorMainScreen extends JPanel {
    private Connection connection = null;
    private JTextField tvPatientName;
    private JTextField tvPatientID;
    private JTable tablePatients;
    private JTable tableAppointment;

    private static String DoctorName;

    static void Doctorinput(String doctorName) {
        DoctorName = doctorName;
        System.out.println(DoctorName);
    }

    /**
     * Create the panel.
     */
    DoctorMainScreen() {
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

        JMenuItem mnitemEdit = new JMenuItem("Edit");
        mnPatientRecords.add(mnitemEdit);

        JMenuItem mnitemDelete = new JMenuItem("Delete");
        mnPatientRecords.add(mnitemDelete);

        JMenuItem mnitemRefresh = new JMenuItem("Refresh");
        mnitemRefresh.addActionListener(e -> resfreshTable());
        mnPatientRecords.add(mnitemRefresh);

        JMenuItem mnitemClose = new JMenuItem("Close");
        mnitemClose.addActionListener(e -> System.exit(0));
        mnitemClose.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mnitemClose);

//        JButton btnNewRecord = new JButton("New Record");
//        btnNewRecord.setBounds(0, 25, 200, 40);
//        btnNewRecord.addActionListener(e -> {
//            PatientSignupScreen patientSignupScreen = new PatientSignupScreen();
//            patientSignupScreen.setVisible(true);
//        });
//        add(btnNewRecord);

        JButton btnEdit = new JButton("Edit");
        btnEdit.setBounds(0, 25, 200, 40);
        btnEdit.addActionListener(e -> {
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

        });
        add(btnEdit);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(199, 25, 200, 40);
        btnDelete.addActionListener(e -> {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the record?", "Alert", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                try {
                    String query = "DELETE FROM patient " +
                            "WHERE Name = ? AND patientID = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, tvPatientName.getText());
                    preparedStatement.setString(2, tvPatientID.getText());
                    preparedStatement.execute();
                    resfreshTable();
                    tvPatientID.setText("");
                    tvPatientName.setText("");
                    JOptionPane.showMessageDialog(null, "Record deleted successfully");
                    preparedStatement.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }

//                else {
//                    JOptionPane.showMessageDialog(null, "GOODBYE");
//                }
        });
        add(btnDelete);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(397, 25, 200, 40);
        btnRefresh.addActionListener((ActionEvent e) -> {
            resfreshTable();
            AppointmentTableRefresh();
            tvPatientID.setText("");
            tvPatientName.setText("");
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
        btnSearchPatient.addActionListener(e -> {
            try {
                String query = "SELECT patientID, Name, Surname, Phone, Gender, Birth, Email, Address, RecordDate FROM Patient WHERE patientID = ? AND Name = ?";
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setString(1, "%" + tvPatientName.getText() + "%");
                ResultSet rs = pst.executeQuery();
                tablePatients.setModel(DbUtils.resultSetToTableModel(rs));

                pst.close();
                rs.close();

            } catch (Exception e1) {

            }
        });
        btnSearchPatient.setBounds(186, 169, 97, 25);
        pnlPatientSearch.add(btnSearchPatient);

        JButton btnShowPatientPayments = new JButton("Patient's Payments");
        btnShowPatientPayments.addActionListener(arg0 -> {
            if (tvPatientID.getText().length() == 0 && tvPatientName.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Textboxlar bos");
            } else {
                PatientPayments.Patientinput(tvPatientName.getText(), tvPatientID.getText());
                PatientPayments patientPayments = new PatientPayments();
                patientPayments.setVisible(true);
            }

        });
        btnShowPatientPayments.setBounds(596, 7, 200, 40);
        pnlPatientSearch.add(btnShowPatientPayments);

        JButton btnSentExercises = new JButton("Send Exercises");
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
                    String query = "SELECT * FROM patient WHERE patientID = ?";
                    PreparedStatement pst = connection.prepareStatement(query);
                    pst.setString(1, ID);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        tvPatientName.setText(rs.getString("Name"));
                        tvPatientID.setText(rs.getString("patientID"));
                    }

                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void AppointmentTableRefresh() {
        try {
            String query = "SELECT patientID, appointmentDate " +
                    "FROM appointment " +
                    "ORDER BY appointmentDate";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            tableAppointment.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resfreshTable() {
        try {
            String query = "SELECT patientID, Name, Surname, Gender , Phone , Birth, Email, Address, RecordDate " +
                    "FROM patient " +
                    "ORDER BY Name";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            tablePatients.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end of resfreshtable
}
