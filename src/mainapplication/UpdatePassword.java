package mainapplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPasswordField;

import java.awt.Font;

public class UpdatePassword extends JFrame {

	private JPanel contentPane;
	
	public static String username;
	
	Connection con = MySQLConnection.ConnectDB();
	
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JPasswordField txt_newpw;
	private JPasswordField txt_confirmpw;
	private JPasswordField txt_currentpw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdatePassword frame = new UpdatePassword();
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
	public UpdatePassword() {
		username = PatientLogin.getUsername();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdatePassword.class.getResource("/mainapplication/Logo.png")));
		setTitle("UpdatePassword");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewPassword.setBounds(94, 164, 106, 14);
		contentPane.add(lblNewPassword);
		
		JLabel lblConfirm = new JLabel("Confirm Password");
		lblConfirm.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblConfirm.setBounds(94, 205, 84, 14);
		contentPane.add(lblConfirm);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PatientMenu pm = new PatientMenu();
				pm.setVisible(true);
				close();
			}
		});
		btnBack.setBounds(265, 261, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblUsername = new JLabel("Current Password");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblUsername.setBounds(94, 128, 106, 14);
		contentPane.add(lblUsername);
		
		txt_newpw = new JPasswordField();
		txt_newpw.setBounds(210, 161, 144, 20);
		contentPane.add(txt_newpw);
		
		txt_confirmpw = new JPasswordField();
		txt_confirmpw.setBounds(210, 202, 144, 20);
		contentPane.add(txt_confirmpw);
		
		JLabel lblUpdatePassword = new JLabel("Update Password");
		lblUpdatePassword.setFont(new Font("Calibri", Font.BOLD, 18));
		lblUpdatePassword.setBounds(166, 72, 173, 14);
		contentPane.add(lblUpdatePassword);
		
		txt_currentpw = new JPasswordField();
		txt_currentpw.setBounds(210, 124, 144, 20);
		contentPane.add(txt_currentpw);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sql = "UPDATE patients SET password=? WHERE username=?";
				String sql1 = "SELECT password FROM patients WHERE username=?";
				
				try{
					pst = con.prepareStatement(sql1);
					pst.setString(1, username);
					rs = pst.executeQuery();
					
					while (rs.next())
					{
						if (rs.getString("password").equals(txt_currentpw.getText()))
						{
							if (txt_newpw.getText().equals(txt_confirmpw.getText()))
							{
									pst = con.prepareStatement(sql);
									pst.setString(1, txt_newpw.getText());
									pst.setString(2, username);
									int rowsupdated = pst.executeUpdate();
									
									if	(rowsupdated > 0)
										JOptionPane.showMessageDialog(null, "Password successfully updated.");
							}else
								JOptionPane.showMessageDialog(null, "Passwords do not match.");
						}else
							JOptionPane.showMessageDialog(null, "Current password is incorrect");
					}

				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,ex);
				}
			}
		});
		btnUpdate.setBounds(154, 261, 89, 23);
		contentPane.add(btnUpdate);
		
		//center jFrame
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
