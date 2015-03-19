package mainapplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class DoctorMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorMenu frame = new DoctorMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void close()
	{
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}

	/**
	 * Create the frame.
	 */
	public DoctorMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DoctorMenu.class.getResource("/mainapplication/Logo.png")));
		setTitle("Doctor Menu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCreatePatientLogin = new JButton("Create Patient Login");
		btnCreatePatientLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreatePatientLogin cpl = new CreatePatientLogin();
				cpl.setVisible(true);
				close();
			}
		});
		btnCreatePatientLogin.setBounds(174, 90, 149, 23);
		contentPane.add(btnCreatePatientLogin);
		
		JButton btnSelectPatient = new JButton("Select Patient");
		btnSelectPatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SelectPatient sp = new SelectPatient();
				sp.setVisible(true);
				close();
			}
		});
		btnSelectPatient.setBounds(174, 138, 149, 23);
		contentPane.add(btnSelectPatient);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			
				JOptionPane.showMessageDialog(null, "Logout successfull.");
				close();
			}
		});
		btnNewButton.setBounds(174, 233, 149, 23);
		contentPane.add(btnNewButton);
		
		JButton btnChangePassword = new JButton("Update Password");
		btnChangePassword.setBounds(174, 184, 149, 23);
		contentPane.add(btnChangePassword);
	}

}
