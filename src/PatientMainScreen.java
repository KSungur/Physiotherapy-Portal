import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class PatientMainScreen extends JPanel {

    public static String PatientName;
    private Connection connection = null;

    static void Patientinput(String patientName) {
        PatientName = patientName;
        System.out.println(PatientName);
    }

    /**
     * Create the panel.
     */
    PatientMainScreen() {
        connection = MySqlConn.dbConnector();

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        setSize(width, height);
        setLayout(null);
        setLayout(null);

        JPanel Infopanel = new JPanel();
        Infopanel.setBounds(0, 0, 269, 270);
        add(Infopanel);
        Infopanel.setLayout(null);

        JLabel name = new JLabel("Name:");
        name.setBounds(24, 35, 70, 14);
        Infopanel.add(name);

        JLabel id = new JLabel("ID:");
        id.setBounds(24, 85, 70, 14);
        Infopanel.add(id);

        JLabel surname = new JLabel("Surname:");
        surname.setBounds(24, 60, 70, 14);
        Infopanel.add(surname);

        JLabel phone = new JLabel("Phone:");
        phone.setBounds(24, 110, 70, 14);
        Infopanel.add(phone);

        JLabel gender = new JLabel("Gender:");
        gender.setBounds(24, 135, 70, 14);
        Infopanel.add(gender);

        JLabel birthday = new JLabel("Birthday:");
        birthday.setBounds(24, 160, 90, 14);
        Infopanel.add(birthday);

        JLabel email = new JLabel("Email:");
        email.setBounds(24, 185, 70, 14);
        Infopanel.add(email);

        JLabel address = new JLabel("Address:");
        address.setBounds(24, 210, 90, 14);
        Infopanel.add(address);


        JLabel nameTXT = new JLabel();
        nameTXT.setBounds(110, 35, 90, 14);
        Infopanel.add(nameTXT);

        JLabel surnameTXT = new JLabel();
        surnameTXT.setBounds(110, 60, 90, 14);
        Infopanel.add(surnameTXT);

        JLabel idTXT = new JLabel();
        idTXT.setBounds(110, 85, 150, 14);
        Infopanel.add(idTXT);

        JLabel phoneTXT = new JLabel();
        phoneTXT.setBounds(110, 110, 90, 14);
        Infopanel.add(phoneTXT);

        JLabel genderTXT = new JLabel();
        genderTXT.setBounds(110, 135, 90, 14);
        Infopanel.add(genderTXT);

        JLabel birthdayTXT = new JLabel();
        birthdayTXT.setBounds(110, 160, 90, 14);
        Infopanel.add(birthdayTXT);

        JLabel emailTXT = new JLabel();
        emailTXT.setBounds(110, 185, 150, 14);
        Infopanel.add(emailTXT);

        JLabel addressTXT = new JLabel();
        addressTXT.setBounds(110, 210, 150, 14);
        Infopanel.add(addressTXT);


        JPanel Diagnosepanel = new JPanel();
        Diagnosepanel.setBounds(279, 0, 390, 270);
        add(Diagnosepanel);
        Diagnosepanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Diagnose");
        lblNewLabel.setBounds(169, 35, 100, 20);
        Diagnosepanel.add(lblNewLabel);

        JPanel panel = new JPanel();
        panel.setBounds(458, 282, 212, 110);
        add(panel);
        panel.setLayout(null);

        JButton btnVideo = new JButton("Video");
        btnVideo.addActionListener(e -> {
            LoginScreen.videoFrame.dispose();
            LoginScreen.videoFrame = new JFrame("YouTube Viewer");
            LoginScreen.videolink = "https://www.youtube.com/v/_IeVQZiAsqU?fs=1";


            NativeInterface.open();
            SwingUtilities.invokeLater(() -> {

                LoginScreen.videoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                LoginScreen.videoFrame.getContentPane().add(LoginScreen.getBrowserPanel(), BorderLayout.CENTER);
                LoginScreen.videoFrame.setSize(800, 600);
                LoginScreen.videoFrame.setLocationByPlatform(true);

            });


            LoginScreen.videoFrame.setVisible(true);
        });
        btnVideo.setBounds(63, 38, 89, 23);
        panel.add(btnVideo);


        JButton btnMessage = new JButton("Compose");
        btnMessage.addActionListener(arg0 -> {
            PatientMessageScreen patientMessageScreen = new PatientMessageScreen();
            patientMessageScreen.setVisible(true);
        });
        btnMessage.setBounds(670, 0, 269, 103);
        add(btnMessage);


        JButton btnReadMSG = new JButton("Inbox");
        btnReadMSG.addActionListener(arg0 -> {
            PatientReadMessage patientReadMessage = new PatientReadMessage();
            patientReadMessage.setVisible(true);
        });
        btnReadMSG.setBounds(670, 150, 269, 103);
        add(btnReadMSG);

        try {
            String query = "SELECT * FROM patient WHERE patientID = " + PatientMainScreen.PatientName;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String ID = rs.getString("patientID");
                String Name = rs.getString("Name");
                String Surname = rs.getString("Surname");
                String Gender = rs.getString("Gender");
                String Phone = rs.getString("Phone");
                String Birth = rs.getString("Birth");
                String Address = rs.getString("Address");
                String Email = rs.getString("Email");

                nameTXT.setText(Name);
                idTXT.setText(ID);
                surnameTXT.setText(Surname);
                genderTXT.setText(Gender);
                phoneTXT.setText(Phone);
                emailTXT.setText(Email);
                birthdayTXT.setText(Birth);
                addressTXT.setText(Address);
            }
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2);
        }

        JButton btnPayment = new JButton("Make Payment");
        btnPayment.addActionListener((ActionEvent arg0) -> {
            try {
                String query = "INSERT INTO patientPayment (patientID, Date, Type, Amount) VALUES (?, ?, ?, ?)";

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime localDate = LocalDateTime.now();
                float amount = 400.50f;
                String type = "Credit Card";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, PatientMainScreen.PatientName);
                preparedStatement.setString(2, dtf.format(localDate));
                preparedStatement.setString(3, type);
                preparedStatement.setFloat(4, amount);
                System.out.println(preparedStatement);
                preparedStatement.execute();
                JOptionPane.showMessageDialog(null, "Payment Processed. Please Contact to your Bank to approve the payment.");
                preparedStatement.close();

            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, e2);
            }
        });
        btnPayment.setBounds(970, 0, 269, 103);
        add(btnPayment);



        JButton btnAppointment = new JButton("Make Appointment");
        btnAppointment.addActionListener((ActionEvent arg0) -> {
            PatientTakeAppointment patientTakeAppointment = new PatientTakeAppointment();
            patientTakeAppointment.setVisible(true);
        });
        btnAppointment.setBounds(970, 150, 269, 103);
        add(btnAppointment);
    }
}
