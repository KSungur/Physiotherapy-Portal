import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginScreen extends JFrame {

    private static LoginScreen frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new LoginScreen();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    private LoginScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        setBounds(100, 100, width, height);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblfizyoterapistim = new JLabel("Physiotherapy Portal");
        lblfizyoterapistim.setHorizontalAlignment(SwingConstants.CENTER);
        lblfizyoterapistim.setBounds(533, 78, 359, 65);
        contentPane.add(lblfizyoterapistim);

        JButton btnDoctorLogin = new JButton("Doctor Login");
        btnDoctorLogin.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new DoctorLoginScreen());
            frame.getContentPane().repaint();
            frame.getContentPane().invalidate();
            frame.getContentPane().revalidate();
        });
        btnDoctorLogin.setBounds(169, 305, 293, 236);
        contentPane.add(btnDoctorLogin);

        JButton btnPatientLogin = new JButton("Patient Login");
        btnPatientLogin.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new PatientLoginScreen());
            frame.getContentPane().repaint();
            frame.getContentPane().invalidate();
            frame.getContentPane().revalidate();
        });
        btnPatientLogin.setBounds(890, 305, 293, 236);
        contentPane.add(btnPatientLogin);
    }

}
