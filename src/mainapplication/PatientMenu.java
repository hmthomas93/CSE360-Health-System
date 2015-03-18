package mainapplication;

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
import java.awt.Font;

public class PatientMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientMenu frame = new PatientMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//close previous frame
	public void close()
	{
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}

	/**
	 * Create the frame.
	 */
	public PatientMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PatientMenu.class.getResource("/mainapplication/Logo.png")));
		setTitle("Patient Menu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCheckDoctorFeedback = new JButton("Check Doctor Feedback");
		btnCheckDoctorFeedback.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnCheckDoctorFeedback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CheckDrFeedback cf = new CheckDrFeedback();
				cf.setVisible(true);
				close();
			}
		});
		btnCheckDoctorFeedback.setBounds(156, 136, 179, 23);
		contentPane.add(btnCheckDoctorFeedback);
		
		JButton btnEditSymptoms = new JButton("Edit Symptoms");
		btnEditSymptoms.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnEditSymptoms.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EditSymptoms es = new EditSymptoms();
				es.setVisible(true);
				close();
			}
		});
		btnEditSymptoms.setBounds(156, 190, 179, 23);
		contentPane.add(btnEditSymptoms);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Logout successfull.");
				close();
			}
		});
		btnNewButton.setBounds(156, 245, 179, 23);
		contentPane.add(btnNewButton);
	}
}
