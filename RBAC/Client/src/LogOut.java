import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class LogOut implements ActionListener {
	Connection con=null;
	ResultSet rs=null;
	PreparedStatement pst=null;
	JFrame jfrm = new JFrame("Logout");
	JLabel l1;
	JButton b1,b2,b3;
	static String arg=new String();
	LogOut(String args) 
	{
		arg=args;
		jfrm.setLayout(new FlowLayout());
		jfrm.setSize(200,150);
	    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    l1=new JLabel("Do you want to logout?");
		jfrm.add(l1);
		b1=new JButton("Yes");
		b2=new JButton("No");
		b3=new JButton("OK");
		jfrm.add(b1);
		jfrm.add(b2);
		b1.addActionListener(this);
		b2.addActionListener(this);
		jfrm.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("Yes"))
		{
			con=ClientConnect.ConnectDB();
		      String sql= "update user set loggedcurrent= '" + 0 + "' where username= '" + arg +  "'";
		      try
		      {
		    	  Statement stmt = con.createStatement();
		             stmt.execute(sql.toString());
		             stmt.close();
		      }
		      catch(SQLException | HeadlessException e)
		      {
		    	  JOptionPane.showMessageDialog(null, e);
		      }
			jfrm.remove(b1);
			jfrm.remove(b2);
			jfrm.remove(l1);
			jfrm.revalidate();
			jfrm.repaint();
			l1=new JLabel("LOGGED OUT!");
			jfrm.add(l1);
			jfrm.add(b3);
			b3.addActionListener(this);
			jfrm.setVisible(true);
			if(ae.getActionCommand().equals("OK"))
			{
				jfrm.dispose();
				
			}
		}
		else
		{
			jfrm.dispose();
		}
		
	}
	public static void main(String args)
	  {
	        SwingUtilities.invokeLater(new Runnable()
	  {
	public void run()
	  {
	        new LogOut(args);
	  }
	 });
}
}
   