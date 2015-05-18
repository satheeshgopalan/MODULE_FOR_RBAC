import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
  public final static int SOCKET_PORT = 6070;  // you may change this
  public final static String FILE_TO_SEND = "C:/Users/ATUL BHASKAR/workspace/Downloadingstuff/temp2.txt";  // this has list of files and file from this location will be sent to client.
  Server() throws IOException, InterruptedException
  {
	  FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    OutputStream os = null;
	    ServerSocket servsock = null;
	    Socket sock = null;
	    try 
	    {
	      servsock = new ServerSocket(SOCKET_PORT);
	      while (true) 
	      {
	        System.out.println("Listing Server Waiting...");
	        
	        try 
	        {
	          sock = servsock.accept();
	          System.out.println("Accepted connection : " + sock);
	          
	          // LISTING CODE
	          
	          String fileName = "temp2.txt";
	          // Directory path here
	           String path = "d:/"; 
	           String files;
	           File folder = new File(path);
	           File[] listOfFiles = folder.listFiles();
	           try 
	           {
	         	    // Assume default encoding.
	         	    FileWriter fileWriter = new FileWriter(fileName);

	         	    // Always wrap FileWriter in BufferedWriter.
	         	    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	         	    for (int i = 0; i < listOfFiles.length; i++) 
	         	    {
	         	    	if (listOfFiles[i].isFile()) 
	         	    	{
	         	    		files = listOfFiles[i].getName();
	         	    		bufferedWriter.write(files);
	         	    		bufferedWriter.newLine();
	         	    		System.out.println(files);
	         	    	}
	         	    }
	         	    bufferedWriter.close();
	           }
	           catch(IOException ex) 
	           {
	         	    System.out.println("Error writing to file '" + fileName + "'");
	         	    // Or we could just do this:
	         	    // ex.printStackTrace();
	           }
	           
	          // send file
	           
	          File myFile = new File (FILE_TO_SEND);
	          byte [] mybytearray  = new byte [(int)myFile.length()];
	          fis = new FileInputStream(myFile);
	          bis = new BufferedInputStream(fis);
	          bis.read(mybytearray,0,mybytearray.length);
	          os = sock.getOutputStream();
	          System.out.println("Sending " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)");
	          Thread.sleep(100);
	          os.write(mybytearray,0,mybytearray.length);
	          os.flush();
	          System.out.println("Done. Closing Server");
	          bis.close();
	          os.close();
	          sock.close();
	          System.out.println("Server Closed");
	          
	          
	        }// try @ line 29
	        finally 
	        {
	          if (bis != null) bis.close();
	          if (os != null) os.close();
	          if (sock!=null) sock.close();
	        }
	        break;
	      }//while @ line 26
	    } // try @ line 22
	    finally 
	    {
	      if (servsock != null) servsock.close();
	      if (bis != null) bis.close();
	      if (os != null) os.close();
	      if (sock!=null) sock.close();
	    } 
  }
  public static void main (String [] args)  throws IOException, InterruptedException
  {
   Server sr = new Server();
  }
}