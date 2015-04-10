package mainapplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SendFeedback extends JFrame {

	private JPanel contentPane;
	
	public static String firstname = SelectPatient.getFirstName();
	public static String lastname = SelectPatient.getLastName();
	public static String docName = DoctorLogin.getLastName();
	
	Connection con = MySQLConnection.ConnectDB();
	
	PreparedStatement pst = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SendFeedback frame = new SendFeedback();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//close existing window to get to next window
	public void close()
	{
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}

	/**
	 * Create the frame.
	 */
	public SendFeedback() {
		setTitle("Send Feedback");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SendFeedback.class.getResource("/mainapplication/Logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Calibri", Font.PLAIN, 12));
		textArea.setBounds(10, 46, 479, 251);
		textArea.setLineWrap(true);
		contentPane.add(textArea);
		
		JLabel lblSendFeedbackTo = new JLabel("Send feedback to patient about symptoms.");
		lblSendFeedbackTo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSendFeedbackTo.setBounds(121, 21, 262, 14);
		contentPane.add(lblSendFeedbackTo);
		
		JButton btnSendFeedback = new JButton("Send Feedback");
		btnSendFeedback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sql = "UPDATE patients SET drfeedback=?, doctorname=? WHERE firstname=? AND lastname=?";
				
				try{
					pst = con.prepareStatement(sql);
					//get text from textArea and update database field "feedback"
					pst.setString(1, textArea.getText());
					pst.setString(2, docName);
					pst.setString(3, firstname);
					pst.setString(4, lastname);
					int rowsupdated = pst.executeUpdate();
					if	(rowsupdated > 0)
						JOptionPane.showMessageDialog(null, "Feedback Successfully Sent.");
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,ex);
				}
			}
		});
		btnSendFeedback.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnSendFeedback.setBounds(97, 331, 125, 23);
		contentPane.add(btnSendFeedback);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SelectPatient sp = new SelectPatient();
				sp.setVisible(true);
				close();
			}
		});
		btnBack.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnBack.setBounds(242, 331, 125, 23);
		contentPane.add(btnBack);
		
		//center jFrame
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}

}
