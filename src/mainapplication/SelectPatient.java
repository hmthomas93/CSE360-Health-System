package mainapplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
	public static String alert;
	public static String flname;
	public static DefaultListModel listModel = new DefaultListModel();
	public static JList list = new JList(listModel);
	
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
	
	//close current jframe to open new jframe
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
		
		JTextArea Question = new JTextArea();
		Question.setFont(new Font("Calibri", Font.PLAIN, 10));
		Question.setLineWrap(true);
		Question.setText("Patients are arranged by whether  or not they have an alert then by "
		+ "   the average from their symptom    scales. If a patient is highlighted in red, then they have an alert. "
		+ "Selecta patient to view their details          which include their symptom scale and their symptom details.");
		Question.setEditable(false);   
		Question.setBounds(280, 168, 139, 108);
		contentPane.add(Question);
		Question.setVisible(false);
		
		//create a list using listModel
		list = new JList(listModel);
		list.setFont(new Font("Calibri", Font.PLAIN, 12));
		list.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		list.setBounds(58, 63, 380, 224);
		contentPane.add(list);
		
		//define query to sort the contents by alert then average in descending order
		String sqlAve = "ALTER TABLE patients ORDER BY alert DESC, average DESC";
		
		//sort table
		try{
			pst = con.prepareStatement(sqlAve);
			pst.executeUpdate();
			
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex);
		}
		
		//define query for the database
		String sql = "SELECT firstname, lastname, alert FROM patients";
		try 
		{ 
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			//while there is info in the table, set the first name and last name to the given variables
			while(rs.next())
			{
				firstname = rs.getString("firstname");
				lastname = rs.getString("lastname");
				alert = rs.getString("alert");
				//if there is an alert, display that there is an alert and add it to the jlist
				if (alert.equals("1"))
					flname = "Alert for " + firstname + " " + lastname;
				else
					flname = firstname + " " + lastname;
				listModel.addElement(flname);
			}
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,ex);
		}
		
		//this cell renderer changes the color if the patient has an alert
		list.setCellRenderer(new AlertRenderer());
		
		//upon clicking select, the highlighted item's info is stored for ViewSelectedPatient to use
		JButton btnSelect = new JButton("Select");
		btnSelect.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//split the string by spaces
				String selected = list.getSelectedValue().toString();
				String[] result = selected.split(" ");

				//if there is an alert, get just the first and last name
				if (list.getSelectedValue().toString().contains("Alert for "))
				{
					firstname = result[2];
					lastname = result[3];
				}else
				{
					firstname = result[0];
					lastname = result[1];
				}
				ViewSelectedPatient vsp = new ViewSelectedPatient();
				vsp.setVisible(true);
				listModel.removeAllElements();
				close();
			}
		});
		btnSelect.setBounds(151, 314, 89, 23);
		contentPane.add(btnSelect);
		
		//upon clicking, the doctor is taken back to the doctor menu
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DoctorMenu dm = new DoctorMenu();
				dm.setVisible(true);
				listModel.removeAllElements();
				close();
			}
		});
		btnBack.setBounds(267, 314, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Question.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Question.setVisible(false);
			}
		});
		lblNewLabel.setBounds(443, 273, 31, 14);
		contentPane.add(lblNewLabel);
		
		ImageIcon newIcon = new ImageIcon(SelectPatient.class.getResource("/mainapplication/Question.png"));
		newIcon.getImage().flush();
		lblNewLabel.setIcon(newIcon);
		
		//center jFrame
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	//return first name
	public static String getFirstName()
	{
		return firstname;
	}
	//return last name
	public static String getLastName()
	{
		return lastname;
	}
	//return first and last name
	public static String getflname()
	{
		return list.getSelectedValue().toString();
	}
}