import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LoginScreen extends JFrame {

	public static LoginScreen frame;
	private static JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginScreen();
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
	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblfizyoterapistim = new JLabel("FIZYOTERAPISTIM");
		lblfizyoterapistim.setHorizontalAlignment(SwingConstants.CENTER);
		lblfizyoterapistim.setBounds(533, 78, 359, 65);
		contentPane.add(lblfizyoterapistim);
		
		JButton btnDoctorLogin = new JButton("Doctor Login");
		btnDoctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new DoctorLoginScreen());
				frame.getContentPane().repaint();
				frame.getContentPane().invalidate();
				frame.getContentPane().revalidate();
			}
		});
		btnDoctorLogin.setBounds(169, 305, 293, 236);
		contentPane.add(btnDoctorLogin);
		
		JButton btnPatientLogin = new JButton("Patient Login");
		btnPatientLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(new PatientLoginScreen());
				frame.getContentPane().repaint();
				frame.getContentPane().invalidate();
				frame.getContentPane().revalidate();
			}
		});
		btnPatientLogin.setBounds(890, 305, 293, 236);
		contentPane.add(btnPatientLogin);
	}

}
