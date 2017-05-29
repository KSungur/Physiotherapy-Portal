import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class LoginScreen extends JFrame {

    public static LoginScreen frame;
    private static JPanel contentPane;
    public static JFrame videoFrame;
    public static String videolink ;
    
    
    public static void openVideoFrame(){
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
        
     // openVideoFrame();
        
        NativeInterface.runEventPump();
        // don't forget to properly close native components
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                NativeInterface.close();
            }
        }));
  
    }

    /**
     * Create the frame.
     */
    public LoginScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        setBounds(0, 0, width, height);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblfizyoterapistim = new JLabel("FIZYOTERAPISTIM");
        lblfizyoterapistim.setHorizontalAlignment(SwingConstants.CENTER);
        lblfizyoterapistim.setBounds(533, 78, 359, 65);
        contentPane.add(lblfizyoterapistim);

        JButton btnDoctorLogin = new JButton("Doctor Login");
        Image img1 = new ImageIcon(this.getClass().getResource("/doctor-icon.png")).getImage();
        btnDoctorLogin.setIcon(new ImageIcon("C:\\Users\\alisi\\Desktop\\doctor-icon.png"));
        btnDoctorLogin.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new DoctorLoginScreen());
            frame.getContentPane().repaint();
            frame.getContentPane().invalidate();
            frame.getContentPane().revalidate();
        });
        btnDoctorLogin.setBounds(169, 270, 444, 330);
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
        
        JButton btnVideo = new JButton("Video");
        btnVideo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	
        		 videoFrame.dispose();
        		 videoFrame = new JFrame("YouTube Viewer");
        		videolink="https://www.youtube.com/v/_IeVQZiAsqU?fs=1";
        	

        	      NativeInterface.open();
        	        SwingUtilities.invokeLater(new Runnable() {
        	            public void run() {
        	               
        	                videoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        	                videoFrame.getContentPane().add(getBrowserPanel(), BorderLayout.CENTER);
        	                videoFrame.setSize(800, 600);
        	                videoFrame.setLocationByPlatform(true);
        	                
        	            }
        	        });
        	      
        		
        		videoFrame.setVisible(true);
        	}
        });
        btnVideo.setBounds(169, 46, 89, 23);
        contentPane.add(btnVideo);
        
        JButton btnVideo_1 = new JButton("Video2");
        btnVideo_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		 videoFrame.dispose();
        		 videoFrame = new JFrame("YouTube Viewer");
        		videolink="https://www.youtube.com/v/xZ2T4CQbwL8?fs=1";
            	

      	      NativeInterface.open();
      	        SwingUtilities.invokeLater(new Runnable() {
      	            public void run() {
      	               
      	            	 videoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     	                videoFrame.getContentPane().add(getBrowserPanel(), BorderLayout.CENTER);
     	                videoFrame.setSize(800, 600);
     	                videoFrame.setLocationByPlatform(true);
      	                
      	            }
      	        });
      	      
      		
      		videoFrame.setVisible(true);
        	}
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
