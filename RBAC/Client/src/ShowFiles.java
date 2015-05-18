import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class ShowFiles implements ActionListener 
{
	private JTextArea tf = new JTextArea(20,50);
    private JFrame f = new JFrame("List of Files");
    private JButton b = new JButton("View List of Files");
    private JButton b1 = new JButton("Back");
    static String arg;
	ShowFiles()
	{
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(5, 5));

        JScrollPane scroller = new JScrollPane();
        scroller.setBorder(BorderFactory.createLineBorder(Color.BLUE.darker(), 5));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(5, 5));
        centerPanel.add(new JLabel("List of Files", JLabel.CENTER), BorderLayout.PAGE_START);        
        tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tf.setEditable(true);
        centerPanel.add(tf, BorderLayout.CENTER);
        scroller.setViewportView(centerPanel);

        JPanel footerPanel = new JPanel();
        b.addActionListener(this);
        b1.addActionListener(this);
        footerPanel.add(b);
        footerPanel.add(b1);
        contentPane.add(scroller, BorderLayout.CENTER);
        contentPane.add(footerPanel, BorderLayout.PAGE_END);
        f.setContentPane(contentPane);
        f.pack();
        f.setLocationByPlatform(true);
        f.setVisible(true);
	}
	public void readfile() throws IOException
	{
		String fileName = "d:/filereceived.txt";
		String line = new String();
		String content = new String();
		try 
		{
        // FileReader reads text files in the default encoding.
				FileReader fileReader = new FileReader(fileName);

        // Always wrap FileReader in BufferedReader.
				BufferedReader bufferedReader = new BufferedReader(fileReader); 
				
				while((line = bufferedReader.readLine()) != null)	// printing download file content 
				{
					//System.out.println(line);
					content=content+line+"\n";
				}    
				System.out.println(content);
				tf.setText(content);
        // Always close files.
				bufferedReader.close();
    	}
		catch(FileNotFoundException ex) 
		{
			System.out.println( "Unable to open file '" +fileName + "'");                
		}
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("View List of Files"))
		{
			try 
			{
				readfile();
			} 
			catch (IOException e) 
			{
				System.out.print("Cannot read file");
				e.printStackTrace();
			}			
		}
		else if(ae.getActionCommand().equals("Back"))
		{
			ClientMainMenu cm = new ClientMainMenu(arg);
			cm.setVisible(false);
			f.dispose();
		}
		
	}
	 public static void main(String args) throws FileNotFoundException, IOException {
		 arg=args;
	        SwingUtilities.invokeLater(new Runnable()
	        {
	            public void run()
	            {
	               // new ShowFiles();
	            }
	        });
	 }
}
