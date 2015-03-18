package mainapplication;

import java.sql.*;

import javax.swing.*;

public class MySQLConnectionDoctor 
{
	public static void main(String[] args)
	{
		ConnectDBDoctor();
	}

	
	public static Connection ConnectDBDoctor()
	{
		try{
		//access driver from the JAR file
		Class.forName("com.mysql.jdbc.Driver");
			
		Connection con = DriverManager.getConnection("jdbc:mysql://208.97.172.138:3306/cse360project","cse360project","cse360project1");
		//jdbc:mysql://localhost:3306/patientlogin -> database
		//root is database user
		//root is password for user is blank
		//JOptionPane.showMessageDialog(null, "Connection Successfull");
		return con;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
	


