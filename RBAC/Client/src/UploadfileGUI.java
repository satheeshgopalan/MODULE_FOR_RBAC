import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.*;
public class UploadfileGUI implements ActionListener 
{
		static JFrame jfrm = new JFrame("Uploading File to Server");
		JButton button = new JButton("Upload");
		JButton b1 = new JButton("Yes");
		JButton b2 = new JButton("No");
		JButton b3 = new JButton("Back");
		JLabel l1;
		File selection;
        private static JFileChooser fileChooser = new JFileChooser();    
        public UploadfileGUI() throws InterruptedException,IOException
        {
        		jfrm.setLayout(new FlowLayout());
        		jfrm.setSize(500,100);
                jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fileChooser.setMultiSelectionEnabled(true);
                fileChooser.setCurrentDirectory(new File("C:\\Documents and Settings"));
                jfrm.add(button);
                button.addActionListener(this);
                b1.addActionListener(this);
                b2.addActionListener(this);
                jfrm.setVisible(true);
                
                
        }
       
        public static void main(String[] args) 
        {
        	SwingUtilities.invokeLater(new Runnable()
        	{
        		public void run()
        		{
        			try {
						new UploadfileGUI();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        	});
        }
        public void actionPerformed(ActionEvent e) 
        {
			String command = e.getActionCommand();
            if (command.equals("Upload")) 
            {
                    fileChooser.showOpenDialog(fileChooser);
                    selection = fileChooser.getSelectedFile();
                    jfrm.remove(button);
                   	jfrm.revalidate();
       				jfrm.repaint();
       				l1=new JLabel("Do you want to upload "+ selection.getName()+" this file?");
       				jfrm.add(l1);
       				jfrm.add(b1);
       				jfrm.add(b2);
       				jfrm.setVisible(true);
                    System.out.println(selection.getName());
            }
            else if(command.equals("No"))
            {
            	jfrm.remove(l1);
            	jfrm.remove(b1);
            	jfrm.remove(b2);
               	jfrm.revalidate();
   				jfrm.repaint();
            	try 
            	{
            		UploadfileGUI up =new UploadfileGUI();
				}
            	catch (InterruptedException e1) 
            	{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) 
            	{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
           
            else if(command.equals("Yes")) // sending file to server
            {
       	     	try {
       	     	uploadfiles cl = new uploadfiles(selection.getAbsolutePath());
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			   	jfrm.remove(l1);
            	jfrm.remove(b1);
            	jfrm.remove(b2);
            	l1=new JLabel("File Uploaded");
            	jfrm.add(l1);
            	b1=new JButton("Back");
            	jfrm.add(b1);
            	b1.addActionListener(this);
            	
               	jfrm.revalidate();
   				jfrm.repaint();
            }
            else if(command.equals("Back")) // sending file to server
            {
            	jfrm.dispose();
            	
            }
            	// copy file sending code
        }
}