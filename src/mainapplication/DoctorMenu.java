package mainapplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;

public class DoctorMenu extends JFrame {

	private JPanel contentPane;
	public static String username = DoctorLogin.getUsername();

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
			public void mouseClicked(MouseEvent arg0) {
				SelectPatient sp = new SelectPatient();
				sp.setVisible(true);
				close();
			}
		});
		btnSelectPatient.setBounds(174, 138, 149, 23);
		contentPane.add(btnSelectPatient);
		
		//Clicking this button will log the user out and take them to the start screen PatientOrDoctor
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			
				JOptionPane.showMessageDialog(null, "Logout successfull.");
				PatientOrDoctor pod = new PatientOrDoctor();
				pod.setVisible(true);
				close();
			}
		});
		btnNewButton.setBounds(174, 233, 149, 23);
		contentPane.add(btnNewButton);
		
		//Clicking this button will take the doctor to a jFrame where they can update their password
		JButton btnChangePassword = new JButton("Update Password");
		btnChangePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				UpdatePasswordDr upd = new UpdatePasswordDr();
				upd.setVisible(true);
				close();
			}
		});
		btnChangePassword.setBounds(174, 184, 149, 23);
		contentPane.add(btnChangePassword);
		
		//Display a welcome to the doctor
		JLabel lblWelcomeDoctor = new JLabel("Welcome Doctor " + DoctorLogin.getLastName());
		lblWelcomeDoctor.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblWelcomeDoctor.setBounds(174, 51, 216, 14);
		contentPane.add(lblWelcomeDoctor);
		
		//center jFrame
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
