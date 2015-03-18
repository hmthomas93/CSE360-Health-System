package mainapplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class PatientOrDoctor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientOrDoctor frame = new PatientOrDoctor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//close jframe
	public void close()
	{
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
	
	/**
	 * Create the frame.
	 */
	public PatientOrDoctor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PatientOrDoctor.class.getResource("/mainapplication/Logo.png")));
		setTitle("Welcome");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAreYouA = new JLabel("Are you a Patient or a Doctor?");
		lblAreYouA.setFont(new Font("Calibri", Font.BOLD, 18));
		lblAreYouA.setBounds(128, 223, 251, 14);
		contentPane.add(lblAreYouA);
		
		JButton btnPatient = new JButton("Patient");
		btnPatient.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnPatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PatientLogin pl = new PatientLogin();
				pl.setVisible(true);
				close();
			}
		});
		btnPatient.setBounds(143, 261, 89, 23);
		contentPane.add(btnPatient);
		
		JButton btnDoctor = new JButton("Doctor");
		btnDoctor.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnDoctor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DoctorLogin dl = new DoctorLogin();
				dl.setVisible(true);
				close();
			}
		});
		btnDoctor.setBounds(256, 261, 89, 23);
		contentPane.add(btnDoctor);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PatientOrDoctor.class.getResource("/mainapplication/Logo.png")));
		lblNewLabel.setBounds(142, 30, 251, 169);
		contentPane.add(lblNewLabel);
	}

}
