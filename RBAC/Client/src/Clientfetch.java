import java.io.BufferedOutputStream;
import java.io.DataOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;

import java.io.Serializable;
import java.net.Socket;

public class Clientfetch implements Serializable {

  public final static int SOCKET_PORT = 6080;      // you may change this
  public final static String SERVER = "127.0.0.1";  // localhost
  public final static String
       FILE_TO_RECEIVED = null;  // you may change this, I give a
                                                            // different name because i don't want to
                                                            // overwrite the one used by server...

  public final static int FILE_SIZE = 6022386; // file size temporary hard coded
                                               // should bigger than the file to be downloaded
  Clientfetch()
  {
	  
  }
  public static int  main (String text) throws IOException {
    int bytesRead;
    int current = 0;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    Socket sock = null;
    try {
      sock = new Socket(SERVER, SOCKET_PORT);
      System.out.println("Connecting...");
       
      String filename = text;
      OutputStream os = sock.getOutputStream(); 
      DataOutputStream dos = new DataOutputStream(os);   
      dos.writeUTF(text); 
     
     // Socket sock = new Socket();
      //ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
      //oos.writeChars(text);
      
      // receive file
      byte [] mybytearray  = new byte [FILE_SIZE];
      InputStream is = sock.getInputStream();
    
	fos = new FileOutputStream("d:/Cloud/Output/Output.txt"); // location where files will be stored after fetching from server.
      bos = new BufferedOutputStream(fos);
      bytesRead = is.read(mybytearray,0,mybytearray.length);
      current = bytesRead;

      do {
         bytesRead =
            is.read(mybytearray, current, (mybytearray.length-current));
         if(bytesRead >= 0) current += bytesRead;
      } while(bytesRead > -1);

      bos.write(mybytearray, 0 , current);
      bos.flush();
      System.out.println("File " + "d:/Cloud/Output/Output.txt"
          + " downloaded (" + current + " bytes read)");
    }
    finally {
      if (fos != null) fos.close();
      if (bos != null) bos.close();
      if (sock != null) sock.close();
    }
    return 1;
  }
public boolean getFile(String text) {
	
	
	// TODO Auto-generated method stub
	try {
		  main(text);
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   	   return true;
}
}