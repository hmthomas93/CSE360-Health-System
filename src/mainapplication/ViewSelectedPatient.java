package mainapplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

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
	public static String alert;
	
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
	
	//close current jFrame to open new one
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
		
		//get the first and last name from the selected item in SelectPatient
		firstName = SelectPatient.getFirstName();
		lastName = SelectPatient.getLastName();
		
		//query database to select all symptoms, alerts, and details given a first name and last name
		String sql = "SELECT dob, diagnosis, nausea, depression, fatigue, pain, anxiety, details, alert FROM patients WHERE firstname=? AND lastname=?";
		try 
		{
			pst = con.prepareStatement(sql);
			pst.setString(1, firstName);
			pst.setString(2, lastName);
			rs = pst.executeQuery();
			
			//while there is info in the database store the results in the given strings
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
				alert = rs.getString("alert");
			}

		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,ex);
		}
		
		JLabel lblPatientName = new JLabel("Patient Name: " + firstName + " " + lastName);
		lblPatientName.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblPatientName.setBounds(27, 65, 359, 14);
		contentPane.add(lblPatientName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth: " + dob);
		lblDateOfBirth.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblDateOfBirth.setBounds(27, 96, 188, 14);
		contentPane.add(lblDateOfBirth);
		
		JLabel lblNauseaLevel = new JLabel("Nausea Level: " + nausea);
		lblNauseaLevel.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNauseaLevel.setBounds(27, 190, 129, 14);
		contentPane.add(lblNauseaLevel);
		
		JLabel lblDepressionLevel = new JLabel("Depression Level: " + depression);
		lblDepressionLevel.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblDepressionLevel.setBounds(27, 215, 143, 14);
		contentPane.add(lblDepressionLevel);
		
		JLabel lblPainLevel = new JLabel("Pain Level: " + pain);
		lblPainLevel.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblPainLevel.setBounds(27, 265, 129, 14);
		contentPane.add(lblPainLevel);
		
		JLabel lblNewLabel = new JLabel("Fatigue Level: " + fatigue);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel.setBounds(27, 240, 129, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAnxietyLevel = new JLabel("Anxiety Level: " + anxiety);
		lblAnxietyLevel.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblAnxietyLevel.setBounds(27, 290, 129, 14);
		contentPane.add(lblAnxietyLevel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(242, 62, 230, 240);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea(details);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Calibri", Font.PLAIN, 12));
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		
		JLabel lblDetailedNotesFrom = new JLabel("Detailed Notes From Patient");
		lblDetailedNotesFrom.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblDetailedNotesFrom.setBounds(291, 37, 181, 14);
		contentPane.add(lblDetailedNotesFrom);
		
		//upon clicking this button, the doctor is taken to a page to send the patient feedback
		JButton btnSendFeedback = new JButton("Send Feedback");
		btnSendFeedback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SendFeedback sf = new SendFeedback();
				sf.setVisible(true);
				close();
			}
		});
		btnSendFeedback.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnSendFeedback.setBounds(119, 331, 129, 23);
		contentPane.add(btnSendFeedback);
		
		//upon clicking this button, the doctor is taken back to SelectPatient
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
		btnBack.setBounds(257, 331, 129, 23);
		contentPane.add(btnBack);
		
		//if there is an alert, show this jLabel, else, do not show this jLabel
		JLabel lblTheResultsFrom = new JLabel("The results from the patient's symptoms have caused an alert.");
		lblTheResultsFrom.setForeground(Color.RED);
		lblTheResultsFrom.setFont(new Font("Calibri", Font.BOLD, 12));
		lblTheResultsFrom.setBounds(27, 11, 359, 14);
		contentPane.add(lblTheResultsFrom);
		
		JTextArea lblDiagnosis = new JTextArea();
		lblDiagnosis.setLineWrap(true);
		lblDiagnosis.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblDiagnosis.setText("Diagnosis: " + diagnosis);
		lblDiagnosis.setWrapStyleWord(true);
		lblDiagnosis.setEditable(false);
		lblDiagnosis.setBackground(new Color(204, 255, 153));
		lblDiagnosis.setBounds(25, 121, 212, 61);
		
		contentPane.add(lblDiagnosis);
		lblTheResultsFrom.setVisible(false);
		
		if (SelectPatient.getflname().contains("Alert for "))
			lblTheResultsFrom.setVisible(true);
		
		//center jFrame
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
