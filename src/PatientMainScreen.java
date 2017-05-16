import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PatientMainScreen extends JPanel  {

	private static String PatientName;
	
	static void Patientinput(String patientName) {
        PatientName = patientName;
        System.out.println(PatientName);
    }
	/**
	 * Create the panel.
	 */
	public PatientMainScreen() {
		
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
		
		JLabel label = new JLabel("Name:");
		label.setBounds(24, 35, 31, 14);
		Infopanel.add(label);
		
		JLabel label_1 = new JLabel("Surname:");
		label_1.setBounds(24, 60, 46, 14);
		Infopanel.add(label_1);
		
		JLabel label_3 = new JLabel("Phone:");
		label_3.setBounds(24, 110, 34, 14);
		Infopanel.add(label_3);
		
		JLabel label_4 = new JLabel("Gender:");
		label_4.setBounds(24, 135, 39, 14);
		Infopanel.add(label_4);
		
		JLabel label_5 = new JLabel("Birthday:");
		label_5.setBounds(24, 160, 44, 14);
		Infopanel.add(label_5);
		
		JLabel label_6 = new JLabel("Email:");
		label_6.setBounds(24, 185, 28, 14);
		Infopanel.add(label_6);
		
		JLabel label_7 = new JLabel("Address:");
		label_7.setBounds(24, 210, 43, 14);
		Infopanel.add(label_7);
		
		JLabel label_8 = new JLabel("New label");
		label_8.setBounds(65, 35, 46, 14);
		Infopanel.add(label_8);
		
		JLabel label_9 = new JLabel("New label");
		label_9.setBounds(75, 60, 46, 14);
		Infopanel.add(label_9);
		
		JLabel label_10 = new JLabel("New label");
		label_10.setBounds(49, 85, 46, 14);
		Infopanel.add(label_10);
		
		JLabel label_2 = new JLabel("ID:");
		label_2.setBounds(24, 85, 15, 14);
		Infopanel.add(label_2);
		
		JLabel label_11 = new JLabel("New label");
		label_11.setBounds(68, 110, 46, 14);
		Infopanel.add(label_11);
		
		JLabel label_12 = new JLabel("New label");
		label_12.setBounds(73, 135, 46, 14);
		Infopanel.add(label_12);
		
		JLabel label_13 = new JLabel("New label");
		label_13.setBounds(78, 160, 46, 14);
		Infopanel.add(label_13);
		
		JLabel label_14 = new JLabel("New label");
		label_14.setBounds(62, 185, 46, 14);
		Infopanel.add(label_14);
		
		JLabel label_15 = new JLabel("New label");
		label_15.setBounds(77, 210, 46, 14);
		Infopanel.add(label_15);
		
		JPanel Diagnosepanel = new JPanel();
		Diagnosepanel.setBounds(279, 0, 390, 270);
		add(Diagnosepanel);
		Diagnosepanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Diagnose");
		lblNewLabel.setBounds(169, 11, 46, 14);
		Diagnosepanel.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(458, 282, 212, 110);
		add(panel);
		panel.setLayout(null);
		
		JButton btnVideo = new JButton("Video");
		btnVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginScreen.videoFrame.dispose();
				LoginScreen.videoFrame = new JFrame("YouTube Viewer");
				LoginScreen.videolink="https://www.youtube.com/v/_IeVQZiAsqU?fs=1";
        	

        	      NativeInterface.open();
        	        SwingUtilities.invokeLater(new Runnable() {
        	            public void run() {
        	               
        	            	LoginScreen.videoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        	            	LoginScreen.videoFrame.getContentPane().add(LoginScreen.getBrowserPanel(), BorderLayout.CENTER);
        	            	LoginScreen.videoFrame.setSize(800, 600);
        	            	LoginScreen.videoFrame.setLocationByPlatform(true);
        	                
        	            }
        	        });
        	      
        		
        	        LoginScreen.videoFrame.setVisible(true);
			}
		});
		btnVideo.setBounds(63, 38, 89, 23);
		panel.add(btnVideo);

	}
}
