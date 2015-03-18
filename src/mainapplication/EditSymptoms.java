package mainapplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Font;

public class EditSymptoms extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditSymptoms frame = new EditSymptoms();
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
	public EditSymptoms() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditSymptoms.class.getResource("/mainapplication/Logo.png")));
		setTitle("Patient Symptoms");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(204, 255, 153));
		tabbedPane.setBounds(10, 11, 479, 305);
		contentPane.add(tabbedPane);
		
		JTabbedPane scale = new JTabbedPane(JTabbedPane.TOP);
		scale.setFont(new Font("Calibri", Font.PLAIN, 12));
		tabbedPane.addTab("Symptom Scale", null, scale, null);
		tabbedPane.setBackgroundAt(0, new Color(255, 255, 255));
		
		JTabbedPane text = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Symptom Text", null, text, null);
		
		JButton btnSendToDoctor = new JButton("Send to Doctor");
		btnSendToDoctor.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnSendToDoctor.setBounds(121, 337, 115, 23);
		contentPane.add(btnSendToDoctor);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PatientMenu pm = new PatientMenu();
				pm.setVisible(true);
				close();
			}
		});
		btnBack.setBounds(246, 337, 115, 23);
		contentPane.add(btnBack);
	}
}
