import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginScreen extends JFrame {

    public static LoginScreen frame;
    public static JFrame videoFrame;
    public static String videolink;


    public static void openVideoFrame() {
        videoFrame = new JFrame("YouTube Viewer");

    }

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

         openVideoFrame();

        NativeInterface.runEventPump();
        // don't forget to properly close native components
        Runtime.getRuntime().addShutdownHook(new Thread(() -> NativeInterface.close()));

    }

    /**
     * Create the frame.
     */
    public LoginScreen() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        setBounds(0, 0, width, height);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblfizyoterapistim = new JLabel("Physiotherapy Portal");
        lblfizyoterapistim.setHorizontalAlignment(SwingConstants.CENTER);
        lblfizyoterapistim.setBounds(533, 78, 359, 65);
        contentPane.add(lblfizyoterapistim);

        JButton btnDoctorLogin = new JButton();
        Image doctor = new ImageIcon(this.getClass().getResource("/doctor.png")).getImage();
        btnDoctorLogin.setIcon(new ImageIcon(doctor));
        btnDoctorLogin.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new DoctorLoginScreen());
            frame.getContentPane().repaint();
            frame.getContentPane().invalidate();
            frame.getContentPane().revalidate();
        });
        btnDoctorLogin.setBounds(169, 270, 144, 144);
        contentPane.add(btnDoctorLogin);

        JButton btnPatientLogin = new JButton();
        Image patient = new ImageIcon(this.getClass().getResource("/patient.png")).getImage();
        btnPatientLogin.setIcon(new ImageIcon(patient));
        btnPatientLogin.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new PatientLoginScreen());
            frame.getContentPane().repaint();
            frame.getContentPane().invalidate();
            frame.getContentPane().revalidate();
        });
        btnPatientLogin.setBounds(600, 270, 144, 144);
        contentPane.add(btnPatientLogin);

        JButton btnVideo = new JButton("Video");
        btnVideo.addActionListener(e -> {

            videoFrame.dispose();
            videoFrame = new JFrame("YouTube Viewer");
            videolink = "https://www.youtube.com/v/_IeVQZiAsqU?fs=1";


            NativeInterface.open();
            SwingUtilities.invokeLater(() -> {

                videoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                videoFrame.getContentPane().add(getBrowserPanel(), BorderLayout.CENTER);
                videoFrame.setSize(800, 600);
                videoFrame.setLocationByPlatform(true);

            });


            videoFrame.setVisible(true);
        });
        btnVideo.setBounds(169, 46, 89, 23);
        contentPane.add(btnVideo);

        JButton btnVideo_1 = new JButton("Video2");
        btnVideo_1.addActionListener(e -> {

            videoFrame.dispose();
            videoFrame = new JFrame("YouTube Viewer");
            videolink = "https://www.youtube.com/v/xZ2T4CQbwL8?fs=1";


            NativeInterface.open();
            SwingUtilities.invokeLater(() -> {

                videoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                videoFrame.getContentPane().add(getBrowserPanel(), BorderLayout.CENTER);
                videoFrame.setSize(800, 600);
                videoFrame.setLocationByPlatform(true);

            });


            videoFrame.setVisible(true);
        });
        btnVideo_1.setBounds(169, 78, 89, 23);
        contentPane.add(btnVideo_1);
    }


    public static JPanel getBrowserPanel() {
        JPanel webBrowserPanel = new JPanel(new BorderLayout());
        JWebBrowser webBrowser = new JWebBrowser();
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        webBrowser.setBarsVisible(false);
        webBrowser.navigate(videolink);
        return webBrowserPanel;
    }
}
