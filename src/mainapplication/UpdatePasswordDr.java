package mainapplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdatePasswordDr extends JFrame {

	private JPanel contentPane;
	private JTextField txt_username;
	private JPasswordField txt_newpw;
	private JPasswordField txt_confirmpw;
	
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
					UpdatePasswordDr frame = new UpdatePasswordDr();
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
	public UpdatePasswordDr() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdatePasswordDr.class.getResource("/mainapplication/Logo.png")));
		setTitle("Update Password");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Update Password");
		label.setFont(new Font("Calibri", Font.BOLD, 18));
		label.setBounds(175, 70, 173, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Username");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		label_1.setBounds(118, 126, 91, 14);
		contentPane.add(label_1);
		
		txt_username = new JTextField();
		txt_username.setFont(new Font("Calibri", Font.PLAIN, 12));
		txt_username.setColumns(10);
		txt_username.setBounds(219, 120, 144, 20);
		contentPane.add(txt_username);
		
		txt_newpw = new JPasswordField();
		txt_newpw.setBounds(219, 159, 144, 20);
		contentPane.add(txt_newpw);
		
		JLabel label_2 = new JLabel("New Password");
		label_2.setFont(new Font("Calibri", Font.PLAIN, 12));
		label_2.setBounds(118, 162, 91, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Confirm");
		label_3.setFont(new Font("Calibri", Font.PLAIN, 12));
		label_3.setBounds(118, 203, 69, 14);
		contentPane.add(label_3);
		
		txt_confirmpw = new JPasswordField();
		txt_confirmpw.setBounds(219, 200, 144, 20);
		contentPane.add(txt_confirmpw);
		
		JButton button = new JButton("Update");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sql = "UPDATE doctors SET password=? WHERE username=?";
				
				try{
					pst = con.prepareStatement(sql);
						if (txt_newpw.getText().equals(txt_confirmpw.getText()))
						{
							pst.setString(1, txt_newpw.getText());
							pst.setString(2, txt_username.getText());
							int rowsupdated = pst.executeUpdate();
							if	(rowsupdated > 0)
								JOptionPane.showMessageDialog(null, "Password successfully updated.");
						}else
						JOptionPane.showMessageDialog(null, "Current password is incorrect.");
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null,ex);
				}
			}
		});
		button.setFont(new Font("Calibri", Font.PLAIN, 12));
		button.setBounds(163, 259, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Back");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DoctorMenu dm = new DoctorMenu();
				dm.setVisible(true);
				close();
			}
		});
		button_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		button_1.setBounds(274, 259, 89, 23);
		contentPane.add(button_1);
	}

}
