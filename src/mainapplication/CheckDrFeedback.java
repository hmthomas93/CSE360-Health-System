package mainapplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class CheckDrFeedback extends JFrame {
	// get the username from when the patient logged in
	public static String username = PatientLogin.getUsername();
	public static String docName;
	String feedback;
	
	//make a connection to the database
	Connection con = MySQLConnectionDoctor.ConnectDBDoctor();
	//construct a preparedstatement and resultset
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckDrFeedback frame = new CheckDrFeedback();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//close previous frame to get to new frame
	public void close()
	{
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}

	/**
	 * Create the frame.
	 */
	public CheckDrFeedback() {
		
		//define query
		String sql = "SELECT drfeedback, doctorname FROM patients WHERE username=?";
		
		try{
			//set username = username from login and execute the query
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			
			//upon finding a username match in the database, set the given strings to the values in the database
			while(rs.next())
			{
				feedback = rs.getString("drfeedback");
				docName = rs.getString("doctorname");
			}
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,ex);
		}
		
		//initial settings for content pane
		setIconImage(Toolkit.getDefaultToolkit().getImage(CheckDrFeedback.class.getResource("/mainapplication/Logo.png")));
		setTitle("Check Doctor Feedback");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 479, 271);
		contentPane.add(scrollPane);
		
		//create new uneditable text are to view doctor feedback
		JTextArea textArea = new JTextArea(feedback);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Calibri", Font.PLAIN, 12));
		textArea.setEditable(false);
		
		//create a button to go back to the patient menu
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
		btnBack.setBounds(202, 337, 89, 23);
		contentPane.add(btnBack);
		
		//create a label specifying which doctor gave the feedback
		JLabel lblFeedbackFromDoctor = new JLabel("Feedback from Doctor " + docName);
		lblFeedbackFromDoctor.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblFeedbackFromDoctor.setBounds(160, 17, 177, 14);
		contentPane.add(lblFeedbackFromDoctor);
		
		//center jFrame
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
}
