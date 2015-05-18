
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.Container;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.sql.*;


public class Permissions extends JPanel
                          implements ActionListener, ItemListener {
	
	
	Connection con=null;
	ResultSet rs=null;
	PreparedStatement pst=null;
	static String arg;
	
    JCheckBox readButton;
    JCheckBox createButton;
    JCheckBox editButton;
    JCheckBox appendButton;
    JCheckBox printButton;
    JCheckBox uploadButton; 
    JCheckBox downloadButton;
   
   
    JButton submitbutton;
   public int count=0;
	//EditorGUI gui = new EditorGUI();


    StringBuffer choices,allowedchoices;
    JLabel pictureLabel;
    JLabel jlab,jlab1;

    public Permissions() {
    	
        super(new BorderLayout());
//    	EditorGUI gui=new EditorGUI();

        //Create the check boxes.
        readButton = new JCheckBox("Read");
        readButton.setMnemonic(KeyEvent.VK_R);
        readButton.setSelected(false);

        createButton = new JCheckBox("Create");
        createButton.setMnemonic(KeyEvent.VK_W);
        createButton.setSelected(false);

        editButton = new JCheckBox("Edit");
        editButton.setMnemonic(KeyEvent.VK_E);
        editButton.setSelected(false);

        appendButton = new JCheckBox("Append");
        appendButton.setMnemonic(KeyEvent.VK_A);
        appendButton.setSelected(false);
        
        printButton = new JCheckBox("Print");
        printButton.setMnemonic(KeyEvent.VK_P);
        printButton.setSelected(false);
        
        uploadButton = new JCheckBox("Upload");
        uploadButton.setMnemonic(KeyEvent.VK_U);
        uploadButton.setSelected(false);
        
        downloadButton = new JCheckBox("Download");
        downloadButton.setMnemonic(KeyEvent.VK_D);
        downloadButton.setSelected(false);
        
       

        submitbutton = new JButton("Submit");
        submitbutton.setMnemonic(KeyEvent.VK_S);
        submitbutton.setSelected(true);
        
        
        //Register a listener for the check boxes.
        readButton.addItemListener(this);
        createButton.addItemListener(this);
        editButton.addItemListener(this);
        appendButton.addItemListener(this);
        printButton.addItemListener(this);
      
        uploadButton.addItemListener(this);
        downloadButton.addItemListener(this);
        
        submitbutton.addActionListener(this);

        //Indicates what's on the geek.
        choices = new StringBuffer("-------");
        allowedchoices=new StringBuffer("-------");
         StringBuffer temp = new StringBuffer("-------");
        //Set up the picture label
        pictureLabel = new JLabel();
        pictureLabel.setFont(pictureLabel.getFont().deriveFont(Font.ITALIC));
        updatePicture();

        /*Put the check boxes in a column in a panel*/
        
        JPanel checkPanel = new JPanel(new GridLayout(0, 1));
        checkPanel.add(readButton);
        checkPanel.add(createButton);
        checkPanel.add(editButton);
        checkPanel.add(appendButton);
        checkPanel.add(uploadButton);
        checkPanel.add(downloadButton);
        checkPanel.add(printButton);
      
    
        checkPanel.add(submitbutton);  
    
        
        add(checkPanel, BorderLayout.LINE_START);
        add(pictureLabel, BorderLayout.EAST);
        setBorder(BorderFactory.createEmptyBorder(200,150,200,150));
    }

    /** Listens to the check boxes. */
    public void itemStateChanged(ItemEvent e) {
        int index = 0;
        char c = '-';
        Object source = e.getItemSelectable();

        if (source == readButton) {
            index = 0;
            c = 'r';
            choices.setCharAt(index, c);
            System.out.println(""+choices);
                     if(readButton.isSelected())
        	{
        	count++;
    System.out.println(""+ count);
    } 
    
                    if(source!=readButton)
                    { count --;
                   // System.out.println("out");
                    }
                     }
        else 
        	if (source == createButton) {
            index = 1;
            c = 'c';
            choices.setCharAt(index, c); System.out.println(""+choices);
       
            if(createButton.isSelected())
        	{count++;
    System.out.println(""+ count);
    }
            if(source!=createButton)
            { count --;
           // System.out.println("out");
            }
            
            } else if (source == editButton) {
            index = 2;
            c = 'e';
            choices.setCharAt(index, c); 
            System.out.println(""+choices);
            //count++;
            //System.out.println(""+ count);
            
            if(editButton.isSelected())
        	{count++;
    System.out.println(""+ count);
    }
            if(source!=editButton)
            { count --;
           // System.out.println("out");
            }
            
            }
        else if (source == appendButton) {
            index = 3;
            c = 'a';
            choices.setCharAt(index, c); 
            System.out.println(""+choices);
            //count++;
           // System.out.println(""+ count); 
            if(appendButton.isSelected())
        	{count++;
    System.out.println(""+ count);
    }
            if(source!=appendButton)
            { count --;
           // System.out.println("out");
            }    
        }
        else if (source == uploadButton) {
            index = 4;
            c = 'u';
            choices.setCharAt(index, c); System.out.println(""+choices);
            //count++;
            //System.out.println(""+ count);
            if(uploadButton.isSelected())
        	{count++;
    System.out.println(""+ count);
    }
            if(source!=uploadButton)
            { count --;
           // System.out.println("out");
            }}
        else if (source == downloadButton) {
            index = 5;
            c = 'd';
            choices.setCharAt(index, c); System.out.println(""+choices);
          //  count++;
            //System.out.println(""+ count);
            if(downloadButton.isSelected())
        	{count++;
    System.out.println(""+ count);
    }
            if(source!=downloadButton)
            { count --;
           // System.out.println("out");
            }    
        
        }
        else if (source == printButton) {
            index = 6;
            c = 'p';
            choices.setCharAt(index, c); System.out.println(""+choices);
            //count++;
            //System.out.println(""+ count);
            if(printButton.isSelected())
        	{count++;
    System.out.println(""+ count);
    }
            if(source!=printButton)
            { count --;
           // System.out.println("out");
            }
        
        }
             else if ((source == submitbutton)) {
             

   
          
            } 


        //Now that we know which button was pushed, find out
        //whether it was selected or deselected.
        if ((e.getStateChange() == ItemEvent.DESELECTED) ) {
            c = '-';
            choices.setCharAt(index, c); 
            count--;
            System.out.println(""+ count);

            System.out.println(""+choices);
            
        }

        //Apply the change to the string.
        
        updatePicture();
    }

    public void actionPerformed(ActionEvent ae)
    {
          AbstractButton jlab = null;
          if((count>7))
      	{
              JOptionPane.showMessageDialog( jlab, "UnSuccessful Submit","Error", JOptionPane.ERROR_MESSAGE);

  } else
	  if((count==0))
    	{          AbstractButton jlab1 = null;

            JOptionPane.showMessageDialog( jlab1, "UnSuccessful Submit","Error", JOptionPane.ERROR_MESSAGE);

}
          else   	if((ae.getActionCommand().equals("Submit")))
          {          AbstractButton jlab2 = null;

        	  System.out.println("Submitted");
         // JOptionPane.showMessageDialog(jlab1, "Successfully Submitted.");
        	  
          JOptionPane.showMessageDialog( jlab2, "Submited Successfully!"+ "\n" +  "Choices are:  "+choices);
          String nameofuser;
          
          //change username with passed parameter here
         
          nameofuser=arg;
          System.out.println("Username:"+nameofuser);
          
          
          float threshold=0;
          
          con=ClientConnect.ConnectDB();
          String sql= "select * from user where username= '" + nameofuser   +"'";
          
          try
          {
              pst=con.prepareStatement(sql);
              rs= pst.executeQuery();
              if (rs.next()){
                 this.hide();
                 
              
              allowedchoices.insert(0,rs.getString(9));
                 threshold=rs.getFloat(7);
                }
                 
                 
                 
                 int i;
                 StringBuffer temp;
                 temp = new StringBuffer("-------");
                 
                 for(i=0;i<7;i++)
                 {
               	  if(allowedchoices.charAt(i)!=choices.charAt(i))
               	  {
               	
       				temp.setCharAt(i,choices.charAt(i));
               	  }
                 }
                 
                 double risk=0.0;
                 for(i=0;i<7;i++)
                 {
               	  switch(temp.charAt(i)){
               	  
               	  case 'r':   risk=risk+0.15;
               	              break;
               	  case 'c':   risk=risk+0.0;
                                 break;
               	  case 'a':   risk=risk+0.20;
                                 break;   
               	  case 'u':   risk=risk+0.0;
                                 break;
                     case 'd':   risk=risk+0.5;
                                 break;
                     case 'p':   risk=risk+0.3;
                                 break;   
                     case 'e':   risk=risk+0.25;
                                 break;               
               	  
                     	  
               	  }
              
               	  
               	  
                 }
                 System.out.println("Current Risk Value: "+risk);
                 System.out.println("PREMISSION ALLOWED(BY DEFAULT):"+allowedchoices+"THRESHOLD:"+threshold);
                 
                 if(threshold<risk){
                 con=ClientConnect.ConnectDB();
                 String sql1= "update user set accountstatus = '" + 0 + "' where username= '" + nameofuser+ "'";
                 JOptionPane.showMessageDialog( jlab1, "Account Has Been Disabled! \n \t Contact Administrator!","Error", JOptionPane.ERROR_MESSAGE);
                 Statement stmt = con.createStatement();
                 stmt.execute(sql1.toString());
                 stmt.close();
                 }
                 else{
                	 GUIFRfetch gf=new GUIFRfetch();
                 }
              
           
          }catch(SQLException | HeadlessException e){
             JOptionPane.showMessageDialog(null, e); 
              
        }                     
          
        
      	 
          
          

 
    }
    }
    
    protected void updatePicture() {
        //Get the icon corresponding to the image.
        ImageIcon icon = createImageIcon(
                                    "per.png");
        pictureLabel.setIcon(icon);
        pictureLabel.setToolTipText("Permissions");
        if (icon == null) {
            pictureLabel.setText("Missing Image");
        } else {
            pictureLabel.setText(null);
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Permissions.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("Permission's Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new Permissions();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static Permissions main(String args) {
    	arg=args;
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
		return null;
    }
}


           
        