package mainapplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;

import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditSymptoms extends JFrame {

	private JPanel contentPane;
	
	public static Connection con = MySQLConnection.ConnectDB();
	
	public static PreparedStatement pst = null;
	public static ResultSet rs = null;
	
	public static String username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditSymptoms frame = new EditSymptoms();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	    public String getSelectedButtonText(ButtonGroup buttonGroup)
	    {
	        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
	            AbstractButton button = buttons.nextElement();

	            if (button.isSelected()) {
	                return button.getText();
	            }
	        }
	        return null;
	    }

	/**
	 * Create the frame.
	 */
	public EditSymptoms() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditSymptoms.class.getResource("/mainapplication/Logo.png")));
		setTitle("Edit Symptoms");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 479, 309);
		contentPane.add(tabbedPane);
		//scale.add(n10);
		
		ButtonGroup bGN = new ButtonGroup();
		this.setSize(515,420);
	    this.setVisible(true);
	    
	    ButtonGroup bGD = new ButtonGroup();
	    this.setVisible(true);
	    
	    ButtonGroup bGP = new ButtonGroup();
	    this.setVisible(true);
	    
	    ButtonGroup bGF = new ButtonGroup();
	    this.setVisible(true);
	    
	    ButtonGroup bGA = new ButtonGroup();
	    this.setVisible(true);
		
		JPanel scale = new JPanel();
		tabbedPane.addTab("Scale", null, scale, null);
		scale.setLayout(null);
		
		JLabel label = new JLabel("1");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(149, 64, 19, 14);
		scale.add(label);
		
		JLabel label_1 = new JLabel("2");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(178, 64, 19, 14);
		scale.add(label_1);
		
		JLabel label_2 = new JLabel("3");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(207, 64, 19, 14);
		scale.add(label_2);
		
		JLabel label_3 = new JLabel("4");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(236, 64, 19, 14);
		scale.add(label_3);
		
		JLabel label_4 = new JLabel("5");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(265, 64, 19, 14);
		scale.add(label_4);
		
		JLabel label_5 = new JLabel("6");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(294, 64, 19, 14);
		scale.add(label_5);
		
		JLabel label_6 = new JLabel("7");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(323, 64, 19, 14);
		scale.add(label_6);
		
		JLabel label_7 = new JLabel("8");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(352, 64, 19, 14);
		scale.add(label_7);
		
		JLabel label_8 = new JLabel("9");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(381, 64, 19, 14);
		scale.add(label_8);
		
		JLabel label_9 = new JLabel("10");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(410, 64, 21, 14);
		scale.add(label_9);
		
		JLabel label_10 = new JLabel("Nausea");
		label_10.setFont(new Font("Calibri", Font.PLAIN, 12));
		label_10.setBounds(70, 89, 73, 14);
		scale.add(label_10);
		
		JLabel label_11 = new JLabel("Depression");
		label_11.setFont(new Font("Calibri", Font.PLAIN, 12));
		label_11.setBounds(70, 129, 73, 14);
		scale.add(label_11);
		
		JRadioButton d1 = new JRadioButton(Integer.toString(1));
		d1.setBounds(149, 122, 21, 21);
		scale.add(d1);
		bGD.add(d1);
		d1.setSelected(true);
		
		JRadioButton d2 = new JRadioButton(Integer.toString(2));
		d2.setBounds(178, 122, 21, 21);
		scale.add(d2);
		bGD.add(d2);
		
		JRadioButton d3 = new JRadioButton(Integer.toString(3));
		d3.setBounds(207, 122, 21, 21);
		scale.add(d3);
		bGD.add(d3);
		
		JRadioButton d4 = new JRadioButton(Integer.toString(4));
		d4.setBounds(236, 122, 21, 21);
		scale.add(d4);
		bGD.add(d4);
		
		JRadioButton d5 = new JRadioButton(Integer.toString(5));
		d5.setBounds(265, 122, 21, 21);
		scale.add(d5);
		bGD.add(d5);
		
		JRadioButton d6 = new JRadioButton(Integer.toString(6));
		d6.setBounds(294, 122, 21, 21);
		scale.add(d6);
		bGD.add(d6);
		
		JRadioButton d7 = new JRadioButton(Integer.toString(7));
		d7.setBounds(323, 122, 21, 21);
		scale.add(d7);
		bGD.add(d7);
		
		JRadioButton d8 = new JRadioButton(Integer.toString(8));
		d8.setBounds(352, 122, 21, 21);
		scale.add(d8);
		bGD.add(d8);
		
		JRadioButton d9 = new JRadioButton(Integer.toString(9));
		d9.setBounds(381, 122, 21, 21);
		scale.add(d9);
		bGD.add(d9);
		
		JRadioButton d10 = new JRadioButton(Integer.toString(10));
		d10.setBounds(410, 122, 21, 21);
		scale.add(d10);
		bGD.add(d10);
		
		JLabel label_12 = new JLabel("Fatigue");
		label_12.setFont(new Font("Calibri", Font.PLAIN, 12));
		label_12.setBounds(70, 165, 73, 14);
		scale.add(label_12);
		
		JRadioButton f1 = new JRadioButton(Integer.toString(1));
		f1.setBounds(149, 158, 21, 21);
		scale.add(f1);
		bGF.add(f1);
		f1.setSelected(true);
		
		JRadioButton f2 = new JRadioButton(Integer.toString(2));
		f2.setBounds(178, 158, 21, 21);
		scale.add(f2);
		bGF.add(f2);
		
		JRadioButton f3 = new JRadioButton(Integer.toString(3));
		f3.setBounds(207, 158, 21, 21);
		scale.add(f3);
		bGF.add(f3);
		
		JRadioButton f4 = new JRadioButton(Integer.toString(4));
		f4.setBounds(236, 158, 21, 21);
		scale.add(f4);
		bGF.add(f4);
		
		JRadioButton f5 = new JRadioButton(Integer.toString(5));
		f5.setBounds(265, 158, 21, 21);
		scale.add(f5);
		bGF.add(f5);
		
		JRadioButton f6 = new JRadioButton(Integer.toString(6));
		f6.setBounds(294, 158, 21, 21);
		scale.add(f6);
		bGF.add(f6);
		
		JRadioButton f7 = new JRadioButton(Integer.toString(7));
		f7.setBounds(323, 158, 21, 21);
		scale.add(f7);
		bGF.add(f7);
		
		JRadioButton f8 = new JRadioButton(Integer.toString(8));
		f8.setBounds(352, 158, 21, 21);
		scale.add(f8);
		bGF.add(f8);
		
		JRadioButton f9 = new JRadioButton(Integer.toString(9));
		f9.setBounds(381, 158, 21, 21);
		scale.add(f9);
		bGF.add(f9);
		
		JRadioButton f10 = new JRadioButton(Integer.toString(10));
		f10.setBounds(410, 158, 21, 21);
		scale.add(f10);
		bGF.add(f10);
		
		JLabel label_13 = new JLabel("Pain");
		label_13.setFont(new Font("Calibri", Font.PLAIN, 12));
		label_13.setBounds(70, 196, 73, 14);
		scale.add(label_13);
		
		JRadioButton p1 = new JRadioButton(Integer.toString(1));
		p1.setBounds(149, 189, 21, 21);
		scale.add(p1);
		bGP.add(p1);
		p1.setSelected(true);
		
		JRadioButton p2 = new JRadioButton(Integer.toString(2));
		p2.setBounds(178, 189, 21, 21);
		scale.add(p2);
		
		JRadioButton p3 = new JRadioButton(Integer.toString(3));
		p3.setBounds(207, 189, 21, 21);
		scale.add(p3);
		bGP.add(p3);
		
		JRadioButton p4 = new JRadioButton(Integer.toString(4));
		p4.setBounds(236, 189, 21, 21);
		scale.add(p4);
		bGP.add(p4);
		
		JRadioButton p5 = new JRadioButton(Integer.toString(5));
		p5.setBounds(265, 189, 21, 21);
		scale.add(p5);
		bGP.add(p5);
		
		JRadioButton p6 = new JRadioButton(Integer.toString(6));
		p6.setBounds(294, 189, 21, 21);
		scale.add(p6);
		bGP.add(p6);
		
		JRadioButton p7 = new JRadioButton(Integer.toString(7));
		p7.setBounds(323, 189, 21, 21);
		scale.add(p7);
		bGP.add(p7);
		
		JRadioButton p8 = new JRadioButton(Integer.toString(8));
		p8.setBounds(352, 189, 21, 21);
		scale.add(p8);
		bGP.add(p8);
		
		JRadioButton p9 = new JRadioButton(Integer.toString(9));
		p9.setBounds(381, 189, 21, 21);
		scale.add(p9);
		bGP.add(p9);
		
		JRadioButton p10 = new JRadioButton(Integer.toString(10));
		p10.setBounds(410, 189, 21, 21);
		scale.add(p10);
		bGP.add(p10);
		
		JLabel label_14 = new JLabel("Anxiety");
		label_14.setFont(new Font("Calibri", Font.PLAIN, 12));
		label_14.setBounds(70, 230, 73, 14);
		scale.add(label_14);
		
		JRadioButton a1 = new JRadioButton(Integer.toString(1));
		a1.setBounds(149, 230, 21, 21);
		scale.add(a1);
		bGA.add(a1);
		a1.setSelected(true);
		
		JRadioButton a2 = new JRadioButton(Integer.toString(2));
		a2.setBounds(178, 230, 21, 21);
		scale.add(a2);
		bGA.add(a2);
		
		JRadioButton a3 = new JRadioButton(Integer.toString(3));
		a3.setBounds(207, 230, 21, 21);
		scale.add(a3);
		bGA.add(a3);
		
		JRadioButton a4 = new JRadioButton(Integer.toString(4));
		a4.setBounds(236, 230, 21, 21);
		scale.add(a4);
		bGA.add(a4);
		
		JRadioButton a5 = new JRadioButton(Integer.toString(5));
		a5.setBounds(265, 230, 21, 21);
		scale.add(a5);
		bGA.add(a5);
		
		JRadioButton a6 = new JRadioButton(Integer.toString(6));
		a6.setBounds(294, 230, 21, 21);
		scale.add(a6);
		bGA.add(a6);
		
		JRadioButton a7 = new JRadioButton(Integer.toString(7));
		a7.setBounds(323, 230, 21, 21);
		scale.add(a7);
		bGA.add(a7);
		
		JRadioButton a8 = new JRadioButton(Integer.toString(8));
		a8.setBounds(352, 230, 21, 21);
		scale.add(a8);
		bGA.add(a8);
		
		JRadioButton a9 = new JRadioButton(Integer.toString(9));
		a9.setBounds(381, 230, 21, 21);
		scale.add(a9);
		bGA.add(a9);
		
		JRadioButton a10 = new JRadioButton(Integer.toString(10));
		a10.setBounds(410, 230, 21, 21);
		scale.add(a10);
		bGA.add(a10);
		
		JLabel lblPleaseInputSymptoms = new JLabel("Please input symptoms on the scale with 1 (least severe) to 10 (most severe).");
		lblPleaseInputSymptoms.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPleaseInputSymptoms.setBounds(24, 31, 440, 14);
		scale.add(lblPleaseInputSymptoms);
		
		JRadioButton n1 = new JRadioButton(Integer.toString(1));
		n1.setBounds(149, 85, 21, 21);
		scale.add(n1);
		bGN.add(n1);
		n1.setSelected(true);
		
		JRadioButton n2 = new JRadioButton(Integer.toString(2));
		n2.setBounds(178, 85, 21, 21);
		scale.add(n2);
		bGN.add(n2);
		
		JRadioButton n3 = new JRadioButton(Integer.toString(3));
		n3.setBounds(207, 85, 21, 21);
		scale.add(n3);
		bGN.add(n3);
		
		JRadioButton n4 = new JRadioButton(Integer.toString(4));
		n4.setBounds(234, 85, 21, 21);
		scale.add(n4);
		bGN.add(n4);
		
		JRadioButton n5 = new JRadioButton(Integer.toString(5));
		n5.setBounds(263, 85, 21, 21);
		scale.add(n5);
		bGN.add(n5);
		
		JRadioButton n6 = new JRadioButton(Integer.toString(6));
		n6.setBounds(292, 85, 21, 21);
		scale.add(n6);
		bGN.add(n6);
		
		JRadioButton n7 = new JRadioButton(Integer.toString(7));
		n7.setBounds(321, 85, 21, 21);
		scale.add(n7);
		bGN.add(n7);
		
		JRadioButton n8 = new JRadioButton(Integer.toString(8));
		n8.setBounds(352, 85, 21, 21);
		scale.add(n8);
		bGN.add(n8);
		
		JRadioButton n9 = new JRadioButton(Integer.toString(9));
		n9.setBounds(381, 85, 21, 21);
		scale.add(n9);
		bGN.add(n9);
		
		JRadioButton n10 = new JRadioButton(Integer.toString(10));
		n10.setBounds(410, 85, 21, 21);
		scale.add(n10);
		bGN.add(n10);
		
		JPanel details = new JPanel();
		tabbedPane.addTab("Details", null, details, null);
		details.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Calibri", Font.PLAIN, 12));
		textArea.setBounds(10, 38, 454, 221);
		details.add(textArea);
		
		JLabel lblPleaseDescribeYour = new JLabel("Please describe your symptoms in detail.");
		lblPleaseDescribeYour.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPleaseDescribeYour.setBounds(112, 11, 299, 14);
		details.add(lblPleaseDescribeYour);
		
		//get username from login
		username = PatientLogin.getUsername();
		
		JButton btnSendFeedback = new JButton("Send Feedback");
		btnSendFeedback.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnSendFeedback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Get the selected button's value and put it in a string
				String nLevel = getSelectedButtonText(bGN);
				String dLevel = getSelectedButtonText(bGD);
				String fLevel = getSelectedButtonText(bGF);
				String pLevel = getSelectedButtonText(bGP);
				String aLevel = getSelectedButtonText(bGA);
				
				//Query for database
				String sql = "UPDATE patients SET nausea=?, depression=?, fatigue=?, pain=?, anxiety=?, alert=?, details=? WHERE username=?";
				
				try 
				{
					pst = con.prepareStatement(sql);

						pst.setString(1, nLevel);
						pst.setString(2, dLevel);
						pst.setString(3, fLevel);
						pst.setString(4, pLevel);
						pst.setString(5, aLevel);
						pst.setString(6, "0");
						pst.setString(7, textArea.getText());
						pst.setString(8, username);
						
						System.out.println(textArea.getText());
						
						//update database with given information
						int rowsupdated = pst.executeUpdate();
						if	(rowsupdated > 0)
							JOptionPane.showMessageDialog(null, "Symptoms successfully sent to doctor.");
					
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,ex);
				}
			}
		});
		btnSendFeedback.setBounds(91, 348, 132, 23);
		contentPane.add(btnSendFeedback);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnBack.setBounds(235, 348, 138, 23);
		contentPane.add(btnBack);
	}

}
