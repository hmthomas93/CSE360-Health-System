package mainapplication;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PatientLogin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String username;
	Connection con = MySQLConnection.ConnectDB();
	
	private JPanel contentPane;
	private JTextField text_username;
	private JPasswordField text_password;

	PreparedStatement pst = null;
	ResultSet rs = null;
	private JLabel lblPleaseLogin;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientLogin frame = new PatientLogin();
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
	public PatientLogin() {
		setTitle("Welcome Patient");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblUsername.setBounds(120, 140, 69, 14);
		contentPane.add(lblUsername);
		
		text_username = new JTextField();
		text_username.setBounds(213, 133, 139, 24);
		contentPane.add(text_username);
		text_username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblPassword.setBounds(120, 178, 83, 14);
		contentPane.add(lblPassword);
		
		text_password = new JPasswordField();
		text_password.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER)
				{
					String sql = "select * from patients where username=? and password=?";
					
					try{
						pst = con.prepareStatement(sql);
						pst.setString(1, text_username.getText());
						pst.setString(2, text_password.getText());
						rs = pst.executeQuery();
						
						if(rs.next())
						{
							JOptionPane.showMessageDialog(null, "Username and password are correct.");
							username = text_username.getText();
							close();
							
							PatientMenu pm = new PatientMenu();
							pm.setVisible(true);
						}else	
							JOptionPane.showMessageDialog(null, "Username and password are invalid.");

					}catch(Exception e){
						JOptionPane.showMessageDialog(null,e);
					}
				}
			}
		});
		text_password.setBounds(213, 168, 139, 24);
		contentPane.add(text_password);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnLogin.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent evt) {
				String sql = "select * from patients where username=? and password=?";
				
				try{
					pst = con.prepareStatement(sql);
					pst.setString(1, text_username.getText());
					pst.setString(2, text_password.getText());
					rs = pst.executeQuery();
					
					if(rs.next())
					{
						JOptionPane.showMessageDialog(null, "Username and password are correct.");
						username = text_username.getText();
						close();
						
						PatientMenu pm = new PatientMenu();
						pm.setVisible(true);
					}else	
						JOptionPane.showMessageDialog(null, "Username and password are invalid.");

				}catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		btnLogin.setBounds(206, 227, 89, 23);
		contentPane.add(btnLogin);
		
		lblPleaseLogin = new JLabel("Please Login");
		lblPleaseLogin.setFont(new Font("Calibri", Font.BOLD, 18));
		lblPleaseLogin.setBounds(179, 88, 148, 14);
		contentPane.add(lblPleaseLogin);
		
		//center jFrame
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	public static String getUsername()
	{
		return username;
	}
}
