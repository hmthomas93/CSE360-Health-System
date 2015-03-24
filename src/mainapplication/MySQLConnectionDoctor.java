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
		//jdbc:mysql://208.97.172.138:3306/cse360project -> database
		//cse360project is database user
		//cse360project1 is database password
		return con;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
	


