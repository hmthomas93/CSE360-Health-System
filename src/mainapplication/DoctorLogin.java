package mainapplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Color;
import java.awt.Font;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JProgressBar;

public class DoctorLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text_username;
	private JPasswordField text_password;
	
	public static String docName;
	public static String docUserName;

	Connection con = MySQLConnectionDoctor.ConnectDBDoctor();
	
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
					DoctorLogin frame = new DoctorLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//close jframe to open new jframe
	public void close()
	{
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
	
	/**
	 * Create the frame.
	 */
	public DoctorLogin() {
		//initial setup for content pane
		setBackground(new Color(0, 204, 102));
		setIconImage(Toolkit.getDefaultToolkit().getImage(DoctorLogin.class.getResource("/mainapplication/Logo.png")));
		setTitle("Welcome Doctor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblUsername.setBounds(136, 135, 64, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblPassword.setBounds(136, 181, 64, 14);
		contentPane.add(lblPassword);
		
		//input username
		text_username = new JTextField();
		text_username.setBounds(210, 132, 152, 20);
		contentPane.add(text_username);
		text_username.setColumns(10);
		
		//input password
		text_password = new JPasswordField();
		text_password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER)
				{
					//define query
					String sql = "select lastname, username from doctors where username=? and password=?";
					
					try{
						pst = con.prepareStatement(sql);
						pst.setString(1, text_username.getText());
						pst.setString(2, text_password.getText());
						//execute query
						rs = pst.executeQuery();
						
						//while there are more values in the database, get the last name and username of the doctor that matches the username
						while(rs.next())
						{
							docName = rs.getString("lastname");
							docUserName = rs.getString("username");
						}
						//execute the query again to start search from beginning
						rs = pst.executeQuery();
						
						//if there is a match, let the doctor continue with the program else, display that the credentials are not valid
						if(rs.next())
						{
							JOptionPane.showMessageDialog(null, "Username and password are correct.");
							close();
							DoctorMenu dm = new DoctorMenu();
							dm.setVisible(true);
							close();
						}else	
							JOptionPane.showMessageDialog(null, "Username and password are invalid.");

					}catch(Exception ex){
						JOptionPane.showMessageDialog(null,ex);
					}
				}
			}
		});
		text_password.setBounds(210, 178, 152, 20);
		contentPane.add(text_password);
		
		//upon clicking this button, the doctor will be logged in if credentials match the database
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//define query
				String sql = "select lastname, username from doctors where username=? and password=?";
				
				try{
					pst = con.prepareStatement(sql);
					pst.setString(1, text_username.getText());
					pst.setString(2, text_password.getText());
					//execute query
					rs = pst.executeQuery();
					
					//while there are more values in the database, get the last name and username of the doctor that matches the username
					while(rs.next())
					{
						docName = rs.getString("lastname");
						docUserName = rs.getString("username");
					}
					//execute the query again to start search from beginning
					rs = pst.executeQuery();
					
					//if there is a match, let the doctor continue with the program else, display that the credentials are not valid
					if(rs.next())
					{
						JOptionPane.showMessageDialog(null, "Username and password are correct.");
						close();
						DoctorMenu dm = new DoctorMenu();
						dm.setVisible(true);
						close();
					}else	
						JOptionPane.showMessageDialog(null, "Username and password are invalid.");

				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,ex);
				}
			}
		});
		btnLogin.setBounds(192, 239, 89, 23);
		contentPane.add(btnLogin);
		
		lblPleaseLogin = new JLabel("Please Login");
		lblPleaseLogin.setFont(new Font("Calibri", Font.BOLD, 18));
		lblPleaseLogin.setBounds(192, 74, 147, 14);
		contentPane.add(lblPleaseLogin);
		
		//center jFrame
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	//return the last name of the doctor
	public static String getLastName()
	{
		return docName;
	}
	
	public static String getUsername()
	{
		return docUserName;
	}
}
