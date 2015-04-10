package mainapplication;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class AlertRenderer extends DefaultListCellRenderer {

	public static String alert;
	
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
    {    	
       Component superRenderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

       if (value.toString().contains("Alert for "))
    	   setForeground(Color.red);

       return superRenderer;
    }
 }
