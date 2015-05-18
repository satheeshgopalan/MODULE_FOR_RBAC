import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;
public class GUIFR implements ActionListener{
	JLabel L1;
	JButton B1;
	static String arg;
	//JTextField T1;
	public Client cl;
	JFrame jfrm = new JFrame("Requesting File From SERVER");
	GUIFR()
	{
		jfrm.setLayout(new FlowLayout());
		jfrm.setSize(200,150);
	    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    L1=new JLabel("Click on List Files");
		jfrm.add(L1);
	//	T1=new JTextField(10);
		//jfrm.add(T1);
		B1=new JButton("List files");
		jfrm.add(B1);
		//B2=new JButton("Fetch File");
		//jfrm.add(B2);
		B1.addActionListener(this);
		//B2.addActionListener(this);
		jfrm.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		 if(ae.getActionCommand().equals("List files"))
		 {
			 	try {
			 		//Server sr = new Server();
			 		
					cl = new Client();
					cl.main(arg);
				} catch (IOException e) 
			 	{
					// TODO Auto-generated catch block
					e.printStackTrace();
			 	}
			//	L1.setText("Listing");
		        jfrm.setVisible(true);
		        jfrm.dispose();
		 }
	}
		/* else if(ae.getActionCommand().equals("Fetch File"))
		 {
			 if(T1.getText().equals(""))
			 {
				 L1.setText("ERROR !!! Enter File Name");
				  jfrm.setVisible(true);
			 }
			 else
			 {
				 L1.setText("Fetching");
				 jfrm.setVisible(true);
			 }
		 }*/
	public static GUIFR main(String  args)
	  {
		arg=args;
	        SwingUtilities.invokeLater(new Runnable()
	  {
	public void run()
	  {
	       // new GUIFR();
	  }
	 });
			return null;

		
	}
	
}