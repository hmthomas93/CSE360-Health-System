package mainapplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.awt.Color;

public class CreatePatientLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txt_firstname;
	private JTextField txt_lastname;
	private JTextField txt_username;
	private JTextField txt_dob;
	private JTextField txt_diagnosis;
	
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
					CreatePatientLogin frame = new CreatePatientLogin();
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
	public CreatePatientLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CreatePatientLogin.class.getResource("/mainapplication/Logo.png")));
		setTitle("Create Patient Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblFirstName.setBounds(105, 119, 73, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblLastName.setBounds(105, 144, 73, 14);
		contentPane.add(lblLastName);
		
		JLabel lblDobyyyymmdd = new JLabel("DOB (yyyy-mm-dd)");
		lblDobyyyymmdd.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblDobyyyymmdd.setBounds(65, 194, 113, 14);
		contentPane.add(lblDobyyyymmdd);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblUsername.setBounds(105, 169, 73, 14);
		contentPane.add(lblUsername);
		
		JLabel lblDiagnosis = new JLabel("Diagnosis");
		lblDiagnosis.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblDiagnosis.setBounds(105, 219, 73, 14);
		contentPane.add(lblDiagnosis);
		
		txt_firstname = new JTextField();
		txt_firstname.setBounds(178, 116, 178, 20);
		contentPane.add(txt_firstname);
		txt_firstname.setColumns(10);
		
		txt_lastname = new JTextField();
		txt_lastname.setColumns(10);
		txt_lastname.setBounds(179, 141, 177, 20);
		contentPane.add(txt_lastname);
		
		txt_username = new JTextField();
		txt_username.setColumns(10);
		txt_username.setBounds(178, 166, 178, 20);
		contentPane.add(txt_username);
		
		txt_dob = new JTextField();
		txt_dob.setColumns(10);
		txt_dob.setBounds(179, 191, 177, 20);
		contentPane.add(txt_dob);
		
		txt_diagnosis = new JTextField();
		txt_diagnosis.setColumns(10);
		txt_diagnosis.setBounds(178, 216, 178, 20);
		contentPane.add(txt_diagnosis);
		
		JLabel lblCreatePatientLogin = new JLabel("Create Patient Login");
		lblCreatePatientLogin.setFont(new Font("Calibri", Font.BOLD, 18));
		lblCreatePatientLogin.setBounds(166, 68, 178, 14);
		contentPane.add(lblCreatePatientLogin);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sql = "INSERT INTO `patients`(`username`, `firstname`, `lastname`, `dob`, `diagnosis`) VALUES (?,?,?,?,?)";
				try{
					pst = con.prepareStatement(sql);
					pst.setString(1, txt_username.getText());
					pst.setString(2, txt_firstname.getText());
					pst.setString(3, txt_lastname.getText());
					pst.setString(4, txt_dob.getText());
					pst.setString(5, txt_diagnosis.getText());
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Patient created successfully.");
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,ex);
				}
			}
		});
		btnCreate.setBounds(139, 289, 89, 23);
		contentPane.add(btnCreate);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DoctorMenu dm = new DoctorMenu();
				dm.setVisible(true);
				close();
			}
		});
		btnBack.setBounds(255, 289, 89, 23);
		contentPane.add(btnBack);
	}
}
