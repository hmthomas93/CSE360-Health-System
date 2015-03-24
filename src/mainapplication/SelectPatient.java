package mainapplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JList;
import javax.swing.border.MatteBorder;

public class SelectPatient extends JFrame {

	private JPanel contentPane;
	public static String firstname;
	public static String lastname;
	
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
					SelectPatient frame = new SelectPatient();
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
	public SelectPatient() {
		setTitle("Select Patient");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectPatient.class.getResource("/mainapplication/Logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectPatientFrom = new JLabel("Select Patient from Patient List");
		lblSelectPatientFrom.setFont(new Font("Calibri", Font.BOLD, 18));
		lblSelectPatientFrom.setBounds(134, 23, 294, 14);
		contentPane.add(lblSelectPatientFrom);
		
		DefaultListModel listModel = new DefaultListModel();
		JList list = new JList(listModel);
		list.setFont(new Font("Calibri", Font.PLAIN, 12));
		list.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		list.setBounds(58, 63, 380, 224);
		contentPane.add(list);
		
		String sql = "SELECT firstname, lastname FROM patients";
		try 
		{
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while(rs.next())
			{
				firstname = rs.getString("firstname");
				lastname = rs.getString("lastname");
				String flname = firstname + " " + lastname;
				listModel.addElement(flname);
			}
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,ex);
		}
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = list.getSelectedValue().toString();
				String[] result = selected.split(" ");
				firstname = result[0];
				lastname = result[1];
				ViewSelectedPatient vsp = new ViewSelectedPatient();
				vsp.setVisible(true);
				close();
			}
		});
		btnSelect.setBounds(151, 314, 89, 23);
		contentPane.add(btnSelect);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DoctorMenu dm = new DoctorMenu();
				dm.setVisible(true);
				close();
			}
		});
		btnBack.setBounds(267, 314, 89, 23);
		contentPane.add(btnBack);
		
	}
	
	public static String getFirstName()
	{
		return firstname;
	}
	
	public static String getLastName()
	{
		return lastname;
	}
}