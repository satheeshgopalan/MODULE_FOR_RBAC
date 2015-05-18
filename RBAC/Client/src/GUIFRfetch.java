import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
public class GUIFRfetch implements ActionListener{
	JLabel L1;
	JButton B1;
	JTextField T1;
	public Clientfetch cl;
	JFrame jfrm = new JFrame("Fetching File From SERVER");
	GUIFRfetch()
	{
		jfrm.setLayout(new FlowLayout());
		jfrm.setSize(200,150);
	    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    L1=new JLabel("Enter File Name: ");
		jfrm.add(L1);
		T1=new JTextField(10);
		jfrm.add(T1);
		B1=new JButton("Fetch files");
		jfrm.add(B1);
		//B2=new JButton("Fetch File");
		//jfrm.add(B2);
		B1.addActionListener(this);
		//B2.addActionListener(this);
		jfrm.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		 if(ae.getActionCommand().equals("Fetch files"))
		 {
			 	try {
			 		
					cl.main(T1.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				L1.setText("Listing");
		        jfrm.setVisible(true);
		        jfrm.dispose();
		 }
		 else if(ae.getActionCommand().equals("Fetch File"))
		 {
			 if(T1.getText().equals(""))
			 {
				 L1.setText("ERROR !!! Enter File Name");
				  jfrm.setVisible(true);
			 }
			 else
			 {
				 L1.setText("Fetching");
				 String arg=new String();
				 arg=T1.getText();
				 Serverfetch sf = new Serverfetch();
				 Clientfetch cf = new Clientfetch();
				 jfrm.setVisible(true);
			 }
		 }
	}
	public static void main(String [] args)
	  {
	        SwingUtilities.invokeLater(new Runnable()
	  {
	public void run()
	  {
	        new GUIFRfetch();
	  }
	 });

		
	}
	
}