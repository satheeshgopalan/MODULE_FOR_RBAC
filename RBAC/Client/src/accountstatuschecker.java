import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
public class accountstatuschecker {
	Connection con=null;
	ResultSet rsas=null;
	PreparedStatement pstas=null;

	public int getaccountstatus( ){
		int s=0;
		String username;
		username="satheesh";
		con=ClientConnect.ConnectDB();
	      String sql= "select accountstatus from user where username='" + username +"'";
	      try {
			pstas=con.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      try {
			rsas= pstas.executeQuery();
			
			s=rsas.getInt(4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      
	      System.out.println("Status:"+s);
		return s;
	}
	
}
