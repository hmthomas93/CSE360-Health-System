package mainapplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewSelectedPatient extends JFrame {
	
	public static String firstName;
	public static String lastName;
	public static String dob;
	public static String diagnosis;
	public static String nausea;
	public static String depression;
	public static String fatigue;
	public static String pain;
	public static String anxiety;
	public static String details;
	
	public static Connection con = MySQLConnection.ConnectDB();
	
	public static PreparedStatement pst = null;
	public static ResultSet rs = null;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSelectedPatient frame = new ViewSelectedPatient();
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
	public ViewSelectedPatient() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewSelectedPatient.class.getResource("/mainapplication/Logo.png")));
		setTitle("View Selected Patient");
		setFont(new Font("Calibri", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		firstName = SelectPatient.getFirstName();
		lastName = SelectPatient.getLastName();
		
		String sql = "SELECT dob, diagnosis, nausea, depression, fatigue, pain, anxiety, details FROM patients WHERE firstname=? AND lastname=?";
		try 
		{
			pst = con.prepareStatement(sql);
			pst.setString(1, firstName);
			pst.setString(2, lastName);
			rs = pst.executeQuery();
			
			while (rs.next())
			{
				dob = rs.getString("dob");
				diagnosis = rs.getString("diagnosis");
				nausea = rs.getString("nausea");
				depression = rs.getString("depression");
				fatigue = rs.getString("fatigue");
				pain = rs.getString("pain");
				anxiety = rs.getString("anxiety");
				details = rs.getString("details");
			}

		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,ex);
		}
		
		JLabel lblPatientName = new JLabel("Patient Name: " + firstName + " " + lastName);
		lblPatientName.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblPatientName.setBounds(27, 25, 188, 14);
		contentPane.add(lblPatientName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth: " + dob);
		lblDateOfBirth.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblDateOfBirth.setBounds(27, 50, 188, 14);
		contentPane.add(lblDateOfBirth);
		
		JLabel lblDiagnosis = new JLabel("Diagnosis: " + diagnosis);
		lblDiagnosis.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblDiagnosis.setBounds(27, 75, 188, 14);
		contentPane.add(lblDiagnosis);
		
		JLabel lblNauseaLevel = new JLabel("Nausea Level: " + nausea);
		lblNauseaLevel.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNauseaLevel.setBounds(27, 156, 129, 14);
		contentPane.add(lblNauseaLevel);
		
		JLabel lblDepressionLevel = new JLabel("Depression Level: " + depression);
		lblDepressionLevel.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblDepressionLevel.setBounds(27, 181, 143, 14);
		contentPane.add(lblDepressionLevel);
		
		JLabel lblPainLevel = new JLabel("Pain Level: " + pain);
		lblPainLevel.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblPainLevel.setBounds(27, 231, 129, 14);
		contentPane.add(lblPainLevel);
		
		JLabel lblNewLabel = new JLabel("Fatigue Level: " + fatigue);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel.setBounds(27, 206, 129, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAnxietyLevel = new JLabel("Anxiety Level: " + anxiety);
		lblAnxietyLevel.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblAnxietyLevel.setBounds(27, 256, 129, 14);
		contentPane.add(lblAnxietyLevel);
		
		JTextArea textArea = new JTextArea(details);
		textArea.setFont(new Font("Calibri", Font.PLAIN, 12));
		textArea.setBounds(257, 45, 232, 225);
		contentPane.add(textArea);
		
		JLabel lblDetailedNotesFrom = new JLabel("Detailed Notes From Patient");
		lblDetailedNotesFrom.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblDetailedNotesFrom.setBounds(291, 25, 181, 14);
		contentPane.add(lblDetailedNotesFrom);
		
		JButton btnSendFeedback = new JButton("Send Feedback");
		btnSendFeedback.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnSendFeedback.setBounds(120, 315, 129, 23);
		contentPane.add(btnSendFeedback);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SelectPatient sp = new SelectPatient();
				sp.setVisible(true);
				close();
			}
		});
		btnBack.setBounds(257, 315, 129, 23);
		contentPane.add(btnBack);
	}
}
