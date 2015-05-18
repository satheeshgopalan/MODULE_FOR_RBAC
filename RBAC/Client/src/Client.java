import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
public class Client {
  public final static int SOCKET_PORT = 6070;      // you may change this
  public final static String SERVER = "127.0.0.1";  // SERVER ADDRESS
  public final static String FILE_TO_RECEIVED = "d:/filereceived.txt";  // location of file received from server side.

  public final static int FILE_SIZE = 6022386; // file size temporary hard coded
  static String arg;
  public ShowFiles sf;
  Client() throws IOException
  {
	  int bytesRead;
	    int current = 0;
	    FileOutputStream fos = null;
	    BufferedOutputStream bos = null;
	    Socket sock = null;
	    try {
	      sock = new Socket(SERVER, SOCKET_PORT);
	      System.out.println("Connecting...");
	      
	      // receiving file
	      
	      byte [] mybytearray  = new byte [FILE_SIZE];
	      InputStream is = sock.getInputStream();
	      fos = new FileOutputStream(FILE_TO_RECEIVED); // file stored @ d:/filereceived.txt 
	      bos = new BufferedOutputStream(fos);
	      bytesRead = is.read(mybytearray,0,mybytearray.length);
	      current = bytesRead;
	      do 
	      {
	         bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
	         if(bytesRead >= 0) current += bytesRead;
	      } while(bytesRead > -1);

	      bos.write(mybytearray, 0 , current);
	      bos.flush();
	      System.out.println("File " + FILE_TO_RECEIVED + " downloaded (" + current + " bytes read)");
	      
	      // reading the download file
	      String fileName = "d:/filereceived.txt";
	      // This will reference one line at a time
	      String line = null;

	      try {
	          // FileReader reads text files in the default encoding.
	          FileReader fileReader = new FileReader(fileName);

	          // Always wrap FileReader in BufferedReader.
	          BufferedReader bufferedReader = new BufferedReader(fileReader); 
	          while((line = bufferedReader.readLine()) != null)	// printing download file content 
	          {
	              System.out.println(line);
	          }    

	          // Always close files.
	          bufferedReader.close();
	          sf=new ShowFiles();
	          sf.main(arg);
	      }
	      catch(FileNotFoundException ex) 
	      {
	          System.out.println( "Unable to open file '" +fileName + "'");                
	      }
	      catch(IOException ex) 
	      {
	          System.out.println("Error reading file '" + fileName + "'");                   
	          // Or we could just do this: 
	          // ex.printStackTrace();
	      }
	    }
	    finally 
	    {
	      if (fos != null) fos.close();
	      if (bos != null) bos.close();
	      if (sock != null) sock.close();
	    } 
  }
           
  public static Client main (String args)  
  {
	  arg=args;
    //Client c = new Client();
	return null;
  }

}